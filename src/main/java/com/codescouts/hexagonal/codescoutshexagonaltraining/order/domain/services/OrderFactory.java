package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.services;

import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.commands.CheckoutOrder;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    private final CustomerFactory customerFactory;
    private final ProductFactory productFactory;
    private final PaymentService paymentService;

    OrderFactory(
            CustomerFactory customerFactory,
            ProductFactory productFactory,
            PaymentService paymentService) {
        this.customerFactory = customerFactory;
        this.productFactory = productFactory;
        this.paymentService = paymentService;
    }

    public Order create(CheckoutOrder command) {
        var customer = this.customerFactory.create(command.customer);
        var products = this.productFactory.create(command.items, command.currency);

        return new Order(this.paymentService, customer, products, command.currency);
    }
}
