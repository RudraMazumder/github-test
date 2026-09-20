package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SumController.class)
class SumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsSumForTwoWholeNumbers() throws Exception {
        mockMvc.perform(post("/api/sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"a": 1, "b": 2}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sum").value(3));
    }

    @Test
    void supportsNegativeNumbers() throws Exception {
        mockMvc.perform(post("/api/sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"a": -10, "b": 3}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sum").value(-7));
    }

    @Test
    void rejectsMissingFields() throws Exception {
        mockMvc.perform(post("/api/sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"a": 1}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void rejectsMalformedJson() throws Exception {
        mockMvc.perform(post("/api/sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 1,"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void rejectsSumOverflow() throws Exception {
        mockMvc.perform(post("/api/sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"a": 9223372036854775807, "b": 1}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(
                        "The sum is outside the supported whole-number range."));
    }
}
