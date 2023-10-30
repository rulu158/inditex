package dev.bracers.inditex.repository;

import dev.bracers.inditex.entity.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    private Price price;

    @BeforeEach
    void setUp() {
        price = new Price(
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
                1L,
                35455L,
                1L,
                35.50,
                "EUR"
        );
    }

    // we try to search for a date covered in a range
    @Test
    void findPriceByDateFound() {
        //given
        priceRepository.save(price);

        //when
        Optional<Price> priceFound = priceRepository.findPriceByDate(
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
        );

        //then
        assertTrue(priceFound.isPresent());
        assertEquals(priceFound.get().getPrice(), 35.50);
    }

    // we try to search for a date not covered in any range
    @Test
    void findPriceByDateNotFound() {
        //given
        priceRepository.save(price);

        //when
        Optional<Price> priceFound = priceRepository.findPriceByDate(
                1L,
                35455L,
                LocalDateTime.of(
                        2020,
                        6,
                        13,
                        10,
                        0,
                        0
                )
        );

        //then
        assertTrue(priceFound.isEmpty());
    }
}