package com.bookstore.dispatcherservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.UUID;
import java.util.function.Function;

@Configuration
public class DispatchingFunctions {

    private static final Logger log = LoggerFactory.getLogger(DispatchingFunctions.class);

    @Bean
    public Function<OrderAcceptedMessage, UUID> pack() {
        return orderAcceptedMessage -> {
            log.info("The order with id {} is packed.", orderAcceptedMessage.orderId());
            System.out.println("orderAcceptedMessage detail: " + orderAcceptedMessage);
            return orderAcceptedMessage.orderId();
        };
    }

    @Bean
    public Function<Flux<UUID>, Flux<OrderDispatchedMessage>> label() {
        return orderFlux -> orderFlux.map(orderId -> {
            log.info("The order with id {} is labeled.", orderId);
            return new OrderDispatchedMessage(orderId);
        });
    }

}
