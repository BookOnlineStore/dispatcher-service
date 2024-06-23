package com.bookstore.dispatcherservice;

import com.bookstore.dispatcherservice.dto.LineItem;
import com.bookstore.dispatcherservice.dto.UserInformation;

import java.util.List;
import java.util.UUID;

public record OrderAcceptedMessage(
        UUID orderId,
        List<LineItem> lineItems,
        UserInformation userInformation
) {
}
