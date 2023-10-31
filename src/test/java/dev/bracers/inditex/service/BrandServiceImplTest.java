package dev.bracers.inditex.service;

import dev.bracers.inditex.entity.Brand;
import dev.bracers.inditex.exception.BrandNotFoundException;
import dev.bracers.inditex.repository.BrandRepository;
import dev.bracers.inditex.response.BrandResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @SneakyThrows
    @Test
    void getBrandFound() {
        //given
        Brand brand = new Brand(
                1L,
                "Zara"
        );
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

        //when
        BrandResponse brandResponse = brandService.getBrand(1L);

        //then
        assertEquals(brandResponse.id(), 1L);
        assertEquals(brandResponse.name(), "Zara");
    }

    @SneakyThrows
    @Test
    void getBrandNotFound() {
        //given
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        //then
        assertThrows(BrandNotFoundException.class, () -> brandService.getBrand(1L));
    }
}