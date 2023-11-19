package com.codescouts.hexagonal.codescoutshexagonaltraining.shipping.domain.events;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class OrderShippedEvent implements DomainEvent {
    private final UUID orderId;
    private final UUID eventId;
    private final Instant occurredOn;

    public OrderShippedEvent(UUID orderId) {
        this.orderId = orderId;
        this.eventId = UUID.randomUUID();
        this.occurredOn = Instant.now();
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
