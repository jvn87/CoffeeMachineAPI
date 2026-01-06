package com.coffeemachine.api.controllers;

import com.coffeemachine.api.config.CustomUserDetails;
import com.coffeemachine.api.models.Order;
import com.coffeemachine.api.models.User;
import com.coffeemachine.api.services.OrderService;
import com.coffeemachine.api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    // POST order to registered user
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Map<String, Object> payload,
                                        Authentication authentication) {
        Long coffeeId = ((Number) payload.get("coffeeId")).longValue();
        Integer sugarLevel = payload.get("sugarLevel") == null ? 0 : ((Number) payload.get("sugarLevel")).intValue();
        String cupSize = payload.get("cupSize") == null ? "Medium" : payload.get("cupSize").toString();

        try {
            String username = authentication.getName();
            Long userId = userService.findIdByUsername(username);

            Order saved = orderService.placeOrder(coffeeId, userId, sugarLevel, cupSize);
            return ResponseEntity.created(URI.create("/orders/" + saved.getId())).body(saved);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "internal error: " + e.getMessage()));
        }
    }

    @GetMapping
    public List<Order> getAllOrders(Authentication authentication) {
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        User authenticated = cud.getUser();
        if (authenticated.getRole() != User.Role.ADMIN) {
            throw new AccessDeniedException("Only admin can view all orders");
        }
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id, Authentication authentication) {
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        User authenticated = cud.getUser();
        return orderService.getOrderById(id)
                .map(order -> {
                    // USER só pode ver as próprias ordens
                    if (!order.getUserId().equals(authenticated.getId()) && authenticated.getRole() != User.Role.ADMIN) {
                        return ResponseEntity.status(403).body(Map.of("error", "You cannot view this order"));
                    }
                    return ResponseEntity.ok(order);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/my")
    public List<Order> getMyOrders(Authentication authentication) {
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        User authenticated = cud.getUser();
        return orderService.getAllOrders().stream()
                .filter(o -> o.getUserId().equals(authenticated.getId()))
                .toList();
    }
}