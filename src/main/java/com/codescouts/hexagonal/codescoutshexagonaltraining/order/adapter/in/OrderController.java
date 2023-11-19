package com.codescouts.hexagonal.codescoutshexagonaltraining.order.adapter.in;

import com.codescouts.hexagonal.codescoutshexagonaltraining.order.adapter.in.requests.CheckoutOrderRequest;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.adapter.in.responses.CheckoutResponse;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.adapter.in.responses.GetOrderResponse;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.usecase.CheckoutOrderUseCase;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.usecase.GetOrderUseCase;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.commands.CheckoutOrder;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.exceptions.OrderDoesNotExistException;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.exceptions.PaymentErrorException;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.exceptions.StockIsNotEnoughException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final CheckoutOrderUseCase checkoutOrderUseCase;

    private final GetOrderUseCase getOrderUseCase;

    @Autowired
    public OrderController(CheckoutOrderUseCase checkoutOrderUseCase,
                           GetOrderUseCase getOrderUseCase) {
        this.checkoutOrderUseCase = checkoutOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable UUID orderId) throws OrderDoesNotExistException {
        var order = this.getOrderUseCase.execute(orderId);

        return ResponseEntity.ok(new GetOrderResponse(order.id(), order.status()));
    }

    @PostMapping
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody CheckoutOrderRequest request) throws PaymentErrorException, StockIsNotEnoughException {
        var command = CheckoutOrder.Create(request.customer(), request.items(), request.currencyCode());

        var orderCreated = this.checkoutOrderUseCase.execute(command);

        return ResponseEntity.ok(new CheckoutResponse(orderCreated.id(), orderCreated.status()));

    }
}