package com.coffeemachine.api.services;

import com.coffeemachine.api.models.Coffee;
import com.coffeemachine.api.repositories.CoffeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<Coffee> listAll() {
        return coffeeRepository.findAll();
    }

    public List<Coffee> listAvailable() {
        return coffeeRepository.findByAvailableTrue();
    }

    public Optional<Coffee> findById(Long id) {
        return coffeeRepository.findById(id);
    }
}
