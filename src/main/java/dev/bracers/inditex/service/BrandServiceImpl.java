package dev.bracers.inditex.service;

import dev.bracers.inditex.entity.Brand;
import dev.bracers.inditex.exception.BrandNotFoundException;
import dev.bracers.inditex.repository.BrandRepository;
import dev.bracers.inditex.response.BrandResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public BrandResponse getBrand(Long brandId) throws BrandNotFoundException {
        Optional<Brand> b = brandRepository.findById(brandId);
        if (b.isEmpty()) {
            throw new BrandNotFoundException("Brand not found");
        }
        return mapBrandToResponse(b.get());
    }

    private BrandResponse mapBrandToResponse(Brand b) {
        return new BrandResponse(
                b.getId(),
                b.getName()
        );
    }
}
