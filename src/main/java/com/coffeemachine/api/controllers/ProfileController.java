package com.coffeemachine.api.controllers;

import com.coffeemachine.api.config.CustomUserDetails;
import com.coffeemachine.api.models.Order;
import com.coffeemachine.api.models.User;
import com.coffeemachine.api.repositories.OrderRepository;
import com.coffeemachine.api.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public ProfileController(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/me")
    public User getMyProfile(Authentication authentication) {
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        return cud.getUser();
    }

    @GetMapping("/orders")
    public List<Order> getMyOrders(Authentication authentication) {
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        User authenticated = cud.getUser();
        return orderRepository.findByUserId(authenticated.getId());
    }
}
