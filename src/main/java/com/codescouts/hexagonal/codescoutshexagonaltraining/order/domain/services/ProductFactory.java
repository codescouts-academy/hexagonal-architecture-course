package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.services;

import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.commands.OrderProductLine;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.entities.Product;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.Currency;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFactory {
    private final int FAKE_STOCK = 100;

    public List<Product> create(List<OrderProductLine> items, Currency currency) {
        //TODO: the currency is used to convert the price of the product.
        //TODO: find products in database.

        return items.stream()
                .map(line ->
                        new Product(line.id(),
                                "Product " + line.id(),
                                20,
                                currency.code,
                                line.quantity(),
                                this.FAKE_STOCK)
                )
                .toList();
    }
}
