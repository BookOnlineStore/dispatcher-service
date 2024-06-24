package com.bookstore.dispatcherservice.dto;

import java.util.List;

public record BookDto(
    String isbn,
    String title,
    String author,
    String publisher,
    String supplier,
    Long price,
    List<String> photos,
    Integer inventory
) {
}
