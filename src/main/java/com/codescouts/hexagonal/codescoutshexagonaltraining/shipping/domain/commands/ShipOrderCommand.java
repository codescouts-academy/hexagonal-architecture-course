package com.codescouts.hexagonal.codescoutshexagonaltraining.shipping.domain.commands;

import java.util.UUID;

public class ShipOrderCommand {
    private final UUID orderId;

    private ShipOrderCommand(UUID orderId) {
        this.orderId = orderId;
    }

    public static ShipOrderCommand create(UUID orderId) {
        return new ShipOrderCommand(orderId);
    }

    public UUID order() {
        return orderId;
    }
}
