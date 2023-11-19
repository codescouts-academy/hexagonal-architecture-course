package com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain;

public interface EventPublisher {
    void raise(AggregateRoot root);
}
