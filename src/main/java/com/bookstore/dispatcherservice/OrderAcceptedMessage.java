package com.bookstore.dispatcherservice;

public record OrderAcceptedMessage (
        Long orderId
) {
}
