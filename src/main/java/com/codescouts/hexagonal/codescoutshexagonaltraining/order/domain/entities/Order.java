package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.entities;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.AggregateRoot;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.events.OrderCreated;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.exceptions.PaymentErrorException;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.exceptions.StockIsNotEnoughException;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.Currency;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.Money;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.Status;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.services.PaymentService;
import com.codescouts.hexagonal.codescoutshexagonaltraining.shipping.domain.events.OrderShippedEvent;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Order extends AggregateRoot {
    private final PaymentService paymentService;
    private final Money total;
    private final Date date;
    private final Customer customer;
    private final List<Product> products;
    private final Currency currency;
    private Status status;
    private UUID id;

    public Order(PaymentService paymentService, Customer customer, List<Product> products, Currency currency) {
        this.paymentService = paymentService;

        this.products = products;
        this.currency = currency;
        this.status = Status.CREATED;
        this.date = new Date();
        this.customer = customer;
        this.total = products.stream()
                .map(product -> product.total())
                .reduce(Money.of(0, currency.code), (total, product) -> total.add(product));
    }

    public Status status() {
        return this.status;
    }

    public UUID id() {
        return this.id;
    }

    public void checkout() throws PaymentErrorException, StockIsNotEnoughException {
        this.validateStock();

        this.pay();

        this.updateStock();

        this.registerEvent(new OrderCreated(this));
    }

    private void updateStock() {
        this.products.forEach(Product::updateStock);
    }

    private void validateStock() throws StockIsNotEnoughException {
        if (this.products.stream().anyMatch(product -> !product.hasEnoughStock())) {
            throw new StockIsNotEnoughException();
        }
    }

    private void pay() throws PaymentErrorException {
        var payment = this.paymentService.pay(this.total);

        if (!payment) {
            throw new PaymentErrorException();
        }

        this.status.changeTo(Status.PAID);
    }

    public void ship() {
        // Just for simulation
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.status.changeTo(Status.DELIVERING);

        this.registerEvent(new OrderShippedEvent(this.id()));
    }
}
