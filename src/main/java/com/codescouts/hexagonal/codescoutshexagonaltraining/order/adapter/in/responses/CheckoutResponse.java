package com.codescouts.hexagonal.codescoutshexagonaltraining.order.adapter.in.responses;

import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.Status;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.StatusValue;

import java.util.UUID;

public class CheckoutResponse {
    public final UUID id;
    public final StatusValue status;

    public CheckoutResponse(UUID id, Status status) {
        this.id = id;
        this.status = status.value();
    }
}
