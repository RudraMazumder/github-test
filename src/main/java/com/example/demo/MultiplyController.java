package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Math.multiplyExact;

@RestController
@RequestMapping("/api/multiply")
public class MultiplyController {

    @PostMapping
    @Operation(
            summary = "Multiply two whole numbers",
            description = "Returns the product of two whole-number values."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Numbers multiplied successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or product overflow",
                    content = @Content(schema = @Schema(implementation = ApiExceptionHandler.ApiError.class))
            )
    })
    public ResponseEntity<MultiplyResponse> multiply(@RequestBody SumRequest request) {
        if (request == null || request.a() == null || request.b() == null) {
            throw new InvalidSumRequestException("Both 'a' and 'b' are required whole numbers.");
        }

        try {
            return ResponseEntity.ok(new MultiplyResponse(multiplyExact(request.a(), request.b())));
        } catch (ArithmeticException exception) {
            throw new InvalidSumRequestException(
                    "The product is outside the supported whole-number range.");
        }
    }
}
