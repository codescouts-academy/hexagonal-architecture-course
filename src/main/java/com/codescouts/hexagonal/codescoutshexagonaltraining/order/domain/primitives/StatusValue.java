package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives;

public enum StatusValue {
    CREATED("CREATED"),
    PAID("PAID"),
    DELIVERING("DELIVER"),
    DELIVERED("DELIVERED");

    private String status;

    private StatusValue(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
