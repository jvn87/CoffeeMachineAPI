package com.coffeemachine.api.controllers;

import com.coffeemachine.api.repositories.CoffeeRepository;
import com.coffeemachine.api.repositories.MachineStatusRepository;
import com.coffeemachine.api.repositories.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final CoffeeRepository coffeeRepo;
    private final OrderRepository orderRepo;
    private final MachineStatusRepository statusRepo;

    public DashboardController(CoffeeRepository coffeeRepo, OrderRepository orderRepo, MachineStatusRepository statusRepo) {
        this.coffeeRepo = coffeeRepo;
        this.orderRepo = orderRepo;
        this.statusRepo = statusRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("coffees", coffeeRepo.findAll());
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("machineStatus", statusRepo.findAll());
        return "dashboard";
    }
}
