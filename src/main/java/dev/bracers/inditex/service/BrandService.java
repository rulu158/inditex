package dev.bracers.inditex.service;

import dev.bracers.inditex.exception.BrandNotFoundException;
import dev.bracers.inditex.response.BrandResponse;

public interface BrandService {
    BrandResponse getBrand(Long brandId) throws BrandNotFoundException;
}
