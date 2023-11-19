package com.codescouts.hexagonal.codescoutshexagonaltraining.shipping.application;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.EventPublisher;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.services.OrderRepository;
import com.codescouts.hexagonal.codescoutshexagonaltraining.shipping.domain.commands.ShipOrderCommand;

public class ShipOrderUseCase {
    private final OrderRepository orderRepository;
    private final EventPublisher eventPublisher;

    public ShipOrderUseCase(OrderRepository orderRepository,
                            EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    public void shipOrder(ShipOrderCommand command) {
        var order = this.orderRepository.findById(command.order());

        order.ship();

        this.orderRepository.save(order);

        this.eventPublisher.raise(order);
    }
}
