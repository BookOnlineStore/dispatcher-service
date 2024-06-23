package com.bookstore.dispatcherservice.dto;

public record UserInformation(
        String fullName,
        String email,
        String phoneNumber,
        String city,
        String zipCode,
        String address
) {}
