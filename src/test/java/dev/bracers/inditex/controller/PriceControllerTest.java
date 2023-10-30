package dev.bracers.inditex.controller;

import dev.bracers.inditex.exception.PriceNotFoundException;
import dev.bracers.inditex.response.PriceResponse;
import dev.bracers.inditex.service.PriceService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @SneakyThrows
    @Test
    void testGetPricesFound() {
        //given
        given(priceService.getPrice(
                LocalDateTime.of(
                        2020,
                        6,
                        14,
                        10,
                        0,
                        0
                ),
                35455L,
                1L)
        ).willReturn(new PriceResponse(
                35455L,
                1L,
                1L,
                LocalDateTime.of(
                        2020,
                        6,
                        14,
                        0,
                        0,
                        0
                ),
                LocalDateTime.of(
                        2020,
                        12,
                        31,
                        23,
                        59,
                        59
                ),
                35.5
        ));

        //when
        //then
        mockMvc.perform(get("/api/v1/price")
                        .param("date", "2020-06-14T10:00:00")
                        .param("product", "35455")
                        .param("brand", "1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.price", is(35.5)));

    }

    @SneakyThrows
    @Test
    void testGetPricesNotFound() {
        //given
        given(priceService.getPrice(
                LocalDateTime.of(
                        2020,
                        6,
                        13,
                        0,
                        0,
                        0
                ),
                35455L,
                1L)
        ).willThrow(new PriceNotFoundException("Price not found"));

        //when
        //then
        mockMvc.perform(get("/api/v1/price")
                .param("date", "2020-06-13T00:00:00")
                .param("product", "35455")
                .param("brand", "1"))
                .andExpect(status().isNotFound());
    }
}