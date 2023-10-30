package dev.bracers.inditex.service;

import dev.bracers.inditex.entity.Price;
import dev.bracers.inditex.exception.PriceNotFoundException;
import dev.bracers.inditex.repository.PriceRepository;
import dev.bracers.inditex.response.PriceResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceResponse getPrice(LocalDateTime date, Long productId, Long brandId) throws PriceNotFoundException {
        Optional<Price> p = priceRepository.findPriceByDate(brandId, productId, date);
        if (p.isEmpty()) {
            throw new PriceNotFoundException("Price not found");
        }
        return mapPriceToResponse(p.get());
    }

    private PriceResponse mapPriceToResponse(Price p) {
        return new PriceResponse(
                p.getProductId(),
                p.getBrandId(),
                p.getPriceList(),
                p.getStartDate(),
                p.getEndDate(),
                p.getPrice()
        );
    }
}
