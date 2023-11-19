package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives;

public class Status {
    public static final Status CREATED = new Status(StatusValue.CREATED);
    public static final Status PAID = new Status(StatusValue.PAID);
    public static final Status DELIVERING = new Status(StatusValue.DELIVERING);

    private StatusValue value;

    private Status(StatusValue value) {
        this.value = value;
    }

    public void changeTo(Status status) {
        this.value = status.value;
    }

    public StatusValue value() {
        return this.value;
    }
}
