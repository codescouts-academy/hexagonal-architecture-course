package com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.usecase;

import com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.services.OrderRepository;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.entities.Order;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.exceptions.OrderDoesNotExistException;

import java.util.UUID;

public class GetOrderUseCase {
    private final OrderRepository orderRepository;

    public GetOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(UUID orderId) throws OrderDoesNotExistException {
        var order = this.orderRepository.findById(orderId);

        if (order == null) {
            throw new OrderDoesNotExistException(orderId);
        }

        return order;
    }
}
