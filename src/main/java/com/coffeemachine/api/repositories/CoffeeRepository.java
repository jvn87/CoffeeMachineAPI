package com.coffeemachine.api.repositories;

import com.coffeemachine.api.models.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    List<Coffee> findByAvailableTrue();
}
