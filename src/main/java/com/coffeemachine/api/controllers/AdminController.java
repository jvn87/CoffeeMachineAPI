package com.coffeemachine.api.controllers;

import com.coffeemachine.api.models.*;
import com.coffeemachine.api.repositories.CoffeeRepository;
import com.coffeemachine.api.repositories.OrderRepository;
import com.coffeemachine.api.repositories.UserRepository;
import com.coffeemachine.api.services.MachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CoffeeRepository coffeeRepository;
    private final MachineService machineService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public AdminController(CoffeeRepository coffeeRepository,
                           MachineService machineService,
                           OrderRepository orderRepository,
                           UserRepository userRepository) {
        this.coffeeRepository = coffeeRepository;
        this.machineService = machineService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    // ----------------- Coffee CRUD -----------------
    @PostMapping("/coffees")
    public ResponseEntity<Coffee> createCoffee(@RequestBody Coffee coffee) {
        Coffee saved = coffeeRepository.save(coffee);
        return ResponseEntity.created(URI.create("/coffees/" + saved.getId())).body(saved);
    }

    @GetMapping("/coffees")
    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    @PutMapping("/coffees/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Long id, @RequestBody Coffee coffee) {
        Optional<Coffee> existing = coffeeRepository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Coffee c = existing.get();
        c.setName(coffee.getName());
        c.setPrice(coffee.getPrice());
        c.setAvailable(coffee.isAvailable());
        c.setIntensity(coffee.getIntensity());
        c.setBody(coffee.getBody());
        c.setSweetness(coffee.getSweetness());
        c.setAcidity(coffee.getAcidity());
        c.setBitterness(coffee.getBitterness());
        c.setDescription(coffee.getDescription());
        c.setOrigin(coffee.getOrigin());
        c.setAroma(coffee.getAroma());
        c.setCaffeineLevel(coffee.getCaffeineLevel());
        c.setType(coffee.getType());
        c.setBeanVariety(coffee.getBeanVariety());
        coffeeRepository.save(c);
        return ResponseEntity.ok(c);
    }

    @DeleteMapping("/coffees/{id}")
    public ResponseEntity<?> deleteCoffee(@PathVariable Long id) {
        if (!coffeeRepository.existsById(id)) return ResponseEntity.notFound().build();
        coffeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------- Machine -----------------
    @PostMapping("/machine/refill")
    public ResponseEntity<MachineStatus> refillMachine(@RequestParam int ml) {
        MachineStatus status = machineService.refillWater(ml);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/machine/reset")
    public ResponseEntity<MachineStatus> resetMaintenance() {
        MachineStatus status = machineService.resetMaintenance();
        return ResponseEntity.ok(status);
    }

    @GetMapping("/machine/status")
    public ResponseEntity<MachineStatus> getMachineStatus() {
        return ResponseEntity.ok(machineService.getStatus());
    }

    // ----------------- Orders -----------------
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<Order> opt = orderRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Order order = opt.get();
        try {
            order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) return ResponseEntity.notFound().build();
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------- Users -----------------
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/count")
    public long countUsers() {
        return userRepository.count();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<User> changeUserRole(@PathVariable Long id, @RequestParam String role) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        User user = opt.get();
        try {
            user.setRole(Enum.valueOf(User.Role.class, role.toUpperCase()));
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}