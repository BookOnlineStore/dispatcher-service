package com.bookstore.dispatcherservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public record LineItem(
        Long id,
        UUID orderId,
        BookDto bookDto,
        Integer quantity
) {}
