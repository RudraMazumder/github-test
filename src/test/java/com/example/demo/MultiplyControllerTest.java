package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MultiplyController.class)
class MultiplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsProductForTwoWholeNumbers() throws Exception {
        mockMvc.perform(post("/api/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"a": 2, "b": 3}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product").value(6));
    }

    @Test
    void supportsNegativeAndZeroValues() throws Exception {
        mockMvc.perform(post("/api/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"a": -7, "b": 0}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product").value(0));
    }

    @Test
    void rejectsMissingFields() throws Exception {
        mockMvc.perform(post("/api/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"a": 2}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void rejectsMalformedJson() throws Exception {
        mockMvc.perform(post("/api/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 2,"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void rejectsProductOverflow() throws Exception {
        mockMvc.perform(post("/api/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"a": 9223372036854775807, "b": 2}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(
                        "The product is outside the supported whole-number range."));
    }
}
