package dev.bracers.inditex.config;

import dev.bracers.inditex.entity.Brand;
import dev.bracers.inditex.entity.Price;
import dev.bracers.inditex.repository.BrandRepository;
import dev.bracers.inditex.repository.PriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadData implements CommandLineRunner {

    private final BrandRepository brandRepository;

    private final PriceRepository priceRepository;

    public LoadData(BrandRepository brandRepository, PriceRepository priceRepository) {
        this.brandRepository = brandRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public void run(String... args) {
        List<Brand> brands = new ArrayList<>();

        brands.add(new Brand(1, "ZARA"));
        brands.add(new Brand(2, "BERSHKA"));

        brandRepository.saveAll(brands);

        List<Price> prices = new ArrayList<>();

        prices.add(
                new Price(
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
                        0L,
                        35.5,
                        "EUR"
                )
        );

        prices.add(
                new Price(
                        1L,
                        LocalDateTime.of(
                                2020,
                                6,
                                14,
                                15,
                                0,
                                0
                        ),
                        LocalDateTime.of(
                                2020,
                                6,
                                14,
                                18,
                                30,
                                0
                        ),
                        2L,
                        35455L,
                        1L,
                        25.45,
                        "EUR"
                )
        );

        prices.add(
                new Price(
                        1L,
                        LocalDateTime.of(
                                2020,
                                6,
                                15,
                                0,
                                0,
                                0
                        ),
                        LocalDateTime.of(
                                2020,
                                6,
                                15,
                                11,
                                0,
                                0
                        ),
                        3L,
                        35455L,
                        1L,
                        30.5,
                        "EUR"
                )
        );

        prices.add(
                new Price(
                        1L,
                        LocalDateTime.of(
                                2020,
                                6,
                                15,
                                16,
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
                        4L,
                        35455L,
                        1L,
                        38.95,
                        "EUR"
                )
        );

        priceRepository.saveAll(prices);
    }
}
