package com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    UUID eventId();

    Instant occurredOn();
}