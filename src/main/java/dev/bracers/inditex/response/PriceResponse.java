package dev.bracers.inditex.response;

import java.time.LocalDateTime;

public record PriceResponse(
        long productId,
        long brandId,
        long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        double price) {
}
