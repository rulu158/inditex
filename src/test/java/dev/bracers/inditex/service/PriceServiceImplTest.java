package dev.bracers.inditex.service;

import dev.bracers.inditex.entity.Price;
import dev.bracers.inditex.exception.PriceNotFoundException;
import dev.bracers.inditex.repository.PriceRepository;
import dev.bracers.inditex.response.PriceResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @SneakyThrows
    @Test
    void getPriceFound() {
        Price price = new Price(
                1,
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
                1L,
                35455L,
                1L,
                35.50,
                "EUR"
        );

        PriceResponse priceResponse = new PriceResponse(
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
        );

        when(priceRepository.findPriceByDate(
                1L,
                35455L,
                LocalDateTime.of(
                        2020,
                        6,
                        14,
                        10,
                        0,
                        0
                )
        )).thenReturn(Optional.of(price));

        PriceResponse priceFound = priceService.getPrice(
                LocalDateTime.of(
                        2020,
                        6,
                        14,
                        10,
                        0,
                        0
                ),
                35455L,
                1L
        );

        assertEquals(priceResponse, priceFound);
    }

    @Test
    public void getPriceNotFound() {
        when(priceRepository.findPriceByDate(
                1L,
                35455L,
                LocalDateTime.of(
                        2020,
                        6,
                        14,
                        10,
                        0,
                        0
                )
        )).thenReturn(Optional.empty());

        assertThrows(
                PriceNotFoundException.class,
                () -> priceService.getPrice(
                        LocalDateTime.of(
                                2020,
                                6,
                                14,
                                10,
                                0,
                                0
                        ),
                        35455L,
                        1L
                )
        );
    }
}