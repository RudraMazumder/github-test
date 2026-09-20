package com.example.demo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "The result of adding two whole numbers.")
public record SumResponse(long sum) {
}
