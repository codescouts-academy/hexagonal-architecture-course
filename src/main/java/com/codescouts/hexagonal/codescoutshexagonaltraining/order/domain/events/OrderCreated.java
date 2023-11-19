package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.events;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.DomainEvent;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.entities.Order;

import java.time.Instant;
import java.util.UUID;

public class OrderCreated implements DomainEvent {
    private final Order order;
    private final UUID eventId;
    private final Instant occurredOn;

    public OrderCreated(Order order) {
        this.order = order;
        this.eventId = UUID.randomUUID();
        this.occurredOn = Instant.now();
    }

    public UUID order() {
        return this.order.id();
    }

    @Override
    public UUID eventId() {
        return this.eventId;
    }

    @Override
    public Instant occurredOn() {
        return this.occurredOn;
    }
}
