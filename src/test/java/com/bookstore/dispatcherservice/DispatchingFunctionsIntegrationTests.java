package com.bookstore.dispatcherservice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@FunctionalSpringBootTest
@Disabled("These tests are only necessary when using the functions alone (no bindings)")
public class DispatchingFunctionsIntegrationTests {

    @Autowired
    FunctionCatalog catalog;

    @Test
    void packOrder() {
        Function<OrderAcceptedMessage, Long> pack =
                catalog.lookup(Function.class, "pack");
        var orderId = UUID.randomUUID();
        var orderAcceptedMessage = new OrderAcceptedMessage(orderId, null, null);
        assertThat(pack.apply(orderAcceptedMessage)).isEqualTo(orderId);
    }

    @Test
    void labelOrder() {
        Function<Flux<UUID>, Flux<OrderDispatchedMessage>> label =
                catalog.lookup(Function.class, "label");

        var orderId = UUID.randomUUID();
        Flux<UUID> orderIdFlux = Flux.just();
        StepVerifier.create(label.apply(orderIdFlux))
                .expectNextMatches(orderDispatchedMessage ->
                        orderDispatchedMessage.equals(new OrderDispatchedMessage(orderId)))
                .verifyComplete();
    }

    @Test
    void packAndLabelOrder() {
        Function<OrderAcceptedMessage, Flux<OrderDispatchedMessage>> packAndLabel =
                catalog.lookup(Function.class, "pack|label");

        var orderId = UUID.randomUUID();
        var orderAcceptedMessage = new OrderAcceptedMessage(orderId, null, null);
        StepVerifier.create(packAndLabel.apply(orderAcceptedMessage))
                .expectNextMatches(dispatchedOrder ->
                        dispatchedOrder.equals(new OrderDispatchedMessage(orderId)))
                .verifyComplete();
    }

}
