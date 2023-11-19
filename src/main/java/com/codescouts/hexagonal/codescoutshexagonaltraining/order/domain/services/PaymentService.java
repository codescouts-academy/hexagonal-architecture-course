package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.services;

import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.Money;

public interface PaymentService {
    boolean pay(Money money);
}

