package com.bookstore.dispatcherservice;

import java.util.UUID;

public record OrderDispatchedMessage (
        UUID orderId
) {
}
