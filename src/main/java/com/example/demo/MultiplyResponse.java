package com.example.demo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "The result of multiplying two whole numbers.")
public record MultiplyResponse(long product) {
}
