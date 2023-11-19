package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.entities;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.Entity;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.Money;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.Quantity;

import java.util.UUID;

public class Product implements Entity<Product> {
    private final UUID id;
    private final String name;
    private final Money unitPrice;
    private final Quantity quantity;
    private Quantity stock;

    public Product(UUID id,
                   String name,
                   float unitPrice,
                   String currencyCode,
                   int quantity,
                   int stock) {
        this.id = id;
        this.name = name;
        this.unitPrice = Money.of(unitPrice, currencyCode);
        this.quantity = Quantity.of(quantity);
        this.stock = Quantity.of(stock);
    }

    public Money total() {
        return this.unitPrice.multiply(this.quantity);
    }

    public boolean hasEnoughStock() {
        return this.stock.isBiggerOrEqualThan(this.quantity);
    }

    public void updateStock() {
        this.stock = this.stock.subtract(this.quantity);
    }

    @Override
    public boolean sameIdentityAs(Product other) {
        return this.id.equals(other.id);
    }
}

