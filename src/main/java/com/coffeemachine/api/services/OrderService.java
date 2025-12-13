package com.coffeemachine.api.services;

import com.coffeemachine.api.models.Coffee;
import com.coffeemachine.api.models.CupSize;
import com.coffeemachine.api.models.Order;
import com.coffeemachine.api.models.OrderStatus;
import com.coffeemachine.api.repositories.CoffeeRepository;
import com.coffeemachine.api.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CoffeeRepository coffeeRepository;
    private final MachineService machineService;

    public OrderService(OrderRepository orderRepository,
                        CoffeeRepository coffeeRepository,
                        MachineService machineService) {
        this.orderRepository = orderRepository;
        this.coffeeRepository = coffeeRepository;
        this.machineService = machineService;
    }

    @Transactional
    public Order placeOrder(Long coffeeId, Long userId, Integer sugarLevel, String cupSize) {
        Optional<Coffee> coffeeOpt = coffeeRepository.findById(coffeeId);
        if (coffeeOpt.isEmpty()) {
            throw new IllegalArgumentException("Coffee not found: " + coffeeId);
        }
        Coffee coffee = coffeeOpt.get();
        if (!coffee.isAvailable()) {
            throw new IllegalStateException("Coffee not available: " + coffee.getName());
        }

        // criar encomenda
        Order order = new Order();
        order.setCoffeeId(coffeeId);
        order.setUserId(userId);
        order.setSugarLevel(sugarLevel);
        try {
            order.setCupSize(CupSize.valueOf(cupSize.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid cup size: " + cupSize);
        }
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(Instant.now());

        Order saved = orderRepository.save(order);

        // atualizar estado da máquina (consumir água, incrementar número de cafés)
        machineService.consumeWaterForCup();

        return saved;
    }

    // listar todas as encomendas
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // obter uma encomenda específica
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}