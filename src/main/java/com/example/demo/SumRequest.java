package com.example.demo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Two whole numbers to add.")
public record SumRequest(Long a, Long b) {
}
