package com.codescouts.hexagonal.codescoutshexagonaltraining.common.adapter;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.AggregateRoot;
import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("AppEventPublisher")
public class AppEventPublisher implements EventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void raise(AggregateRoot root) {
        var events = root.domainEvents();
        root.clearDomainEvents();
        new Thread(() -> {
            events.forEach(this.applicationEventPublisher::publishEvent);
        }).start();
    }
}
