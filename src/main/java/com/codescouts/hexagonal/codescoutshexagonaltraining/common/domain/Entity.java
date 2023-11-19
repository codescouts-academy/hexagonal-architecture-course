package com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain;

public interface Entity<T> {
    boolean sameIdentityAs(T other);
}
