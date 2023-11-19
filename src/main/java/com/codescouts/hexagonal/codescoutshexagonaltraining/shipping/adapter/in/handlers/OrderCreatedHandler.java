package com.codescouts.hexagonal.codescoutshexagonaltraining.shipping.adapter.in.handlers;

import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.events.OrderCreated;
import com.codescouts.hexagonal.codescoutshexagonaltraining.shipping.application.ShipOrderUseCase;
import com.codescouts.hexagonal.codescoutshexagonaltraining.shipping.domain.commands.ShipOrderCommand;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedHandler {

    private final ShipOrderUseCase shipOrderUseCase;

    public OrderCreatedHandler(ShipOrderUseCase shipOrderUseCase) {
        this.shipOrderUseCase = shipOrderUseCase;
    }

    @EventListener
    public void handle(OrderCreated orderCreated) {
        shipOrderUseCase.shipOrder(ShipOrderCommand.create(orderCreated.order()));
    }
}
