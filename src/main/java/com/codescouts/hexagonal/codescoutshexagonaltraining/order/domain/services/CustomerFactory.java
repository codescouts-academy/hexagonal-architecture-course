package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.services;

import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerFactory {
    public Customer create(UUID customerId) {
        // TODO: Get customer by id from database and/or external service.
        return new Customer(customerId,
                "John Doe",
                "john.doe@unknown.com",
                "Street 123, springfield",
                "+1 234 567 890");
    }
}
