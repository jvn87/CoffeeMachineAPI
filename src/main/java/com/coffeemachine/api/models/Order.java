package com.coffeemachine.api.models;

import jakarta.persistence.*;
import java.time.Instant;
import com.coffeemachine.api.models.OrderStatus;
import com.coffeemachine.api.models.CupSize;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long coffeeId;

    // nullable for casual users
    private Long userId;

    private Integer sugarLevel;

    @Enumerated(EnumType.STRING)
    private CupSize cupSize;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Instant createdAt;

    public Order() {}

    public Order(Long coffeeId, Long userId, Integer sugarLevel, CupSize cupSize, OrderStatus status, Instant createdAt) {
        this.coffeeId = coffeeId;
        this.userId = userId;
        this.sugarLevel = sugarLevel;
        this.cupSize = cupSize;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(Long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(Integer sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public CupSize getCupSize() {
        return cupSize;
    }

    public void setCupSize(CupSize cupSize) {
        this.cupSize = cupSize;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
