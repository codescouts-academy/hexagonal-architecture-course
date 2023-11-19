package com.codescouts.hexagonal.codescoutshexagonaltraining;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.EventPublisher;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.services.OrderRepository;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.usecase.CheckoutOrderUseCase;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.application.usecase.GetOrderUseCase;
import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.services.OrderFactory;
import com.codescouts.hexagonal.codescoutshexagonaltraining.shipping.application.ShipOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.codescouts.hexagonal.codescoutshexagonaltraining")
public class AppConfiguration {
    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected OrderFactory orderFactory;

    @Autowired
    protected EventPublisher eventPublisher;

    @Bean
    public CheckoutOrderUseCase getCheckoutOrderUseCase() {
        return new CheckoutOrderUseCase(orderRepository, orderFactory, eventPublisher);
    }

    @Bean
    public GetOrderUseCase getGetOrderUseCase() {
        return new GetOrderUseCase(orderRepository);
    }

    @Bean
    public ShipOrderUseCase getShipOrderUseCase() {
        return new ShipOrderUseCase(orderRepository, eventPublisher);
    }
}
