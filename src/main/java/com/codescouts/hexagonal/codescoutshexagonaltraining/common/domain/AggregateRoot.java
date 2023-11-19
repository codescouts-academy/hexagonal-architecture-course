package com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AggregateRoot {
    private ArrayList<DomainEvent> domainEvents = new ArrayList<>();

    public void registerEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }

    public Collection<DomainEvent> domainEvents() {
        return this.domainEvents.stream().toList();
    }
}