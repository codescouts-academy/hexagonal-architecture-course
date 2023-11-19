package com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.usecase;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.EventPublisher;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.services.OrderRepository;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.commands.CheckoutOrder;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.entities.Order;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.exceptions.PaymentErrorException;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.exceptions.StockIsNotEnoughException;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.services.OrderFactory;

public class CheckoutOrderUseCase {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final EventPublisher eventPublisher;

    public CheckoutOrderUseCase(OrderRepository orderRepository,
                                OrderFactory orderFactory,
                                EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.eventPublisher = eventPublisher;
    }

    public Order execute(CheckoutOrder command) throws PaymentErrorException, StockIsNotEnoughException {
        var newOrder = this.orderFactory.create(command);

        newOrder.checkout();

        this.orderRepository.save(newOrder);

        this.eventPublisher.raise(newOrder);

        return newOrder;
    }
}
