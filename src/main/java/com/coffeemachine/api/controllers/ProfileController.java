package com.coffeemachine.api.controllers;

import com.coffeemachine.api.models.Order;
import com.coffeemachine.api.models.User;
import com.coffeemachine.api.repositories.OrderRepository;
import com.coffeemachine.api.repositories.UserRepository;
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

    // Para já, recebemos o userId via query param (temporário)
    @GetMapping("/me")
    public User getProfile(@RequestParam Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @GetMapping("/orders")
    public List<Order> getMyOrders(@RequestParam Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
