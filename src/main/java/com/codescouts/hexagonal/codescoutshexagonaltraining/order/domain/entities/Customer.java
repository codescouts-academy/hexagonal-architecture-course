package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.entities;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.Entity;

import java.util.UUID;

public class Customer implements Entity<Customer> {
    private final UUID id;
    private final String name;
    private final String email;
    private final String address;
    private final String phone;

    public Customer(UUID id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public UUID id() {
        return this.id;
    }

    @Override
    public boolean sameIdentityAs(Customer other) {
        return this.id.equals(other.id);
    }
}
