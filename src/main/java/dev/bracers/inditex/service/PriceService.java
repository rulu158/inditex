package dev.bracers.inditex.service;

import dev.bracers.inditex.exception.PriceNotFoundException;
import dev.bracers.inditex.response.PriceResponse;

import java.time.LocalDateTime;

public interface PriceService {
    PriceResponse getPrice(LocalDateTime date, Long productId, Long brandId) throws PriceNotFoundException;
}
