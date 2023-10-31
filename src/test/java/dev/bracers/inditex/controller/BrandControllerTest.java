package dev.bracers.inditex.controller;

import dev.bracers.inditex.exception.BrandNotFoundException;
import dev.bracers.inditex.response.BrandResponse;
import dev.bracers.inditex.service.BrandService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandController.class)
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @SneakyThrows
    @Test
    void testGetBrandFound() {
        //given
        given(brandService.getBrand(1L)).willReturn(new BrandResponse(
                1L,
                "Zara"
        ));

        //when
        mockMvc.perform(get("/api/v1/brand?brand=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Zara"));
    }

    @SneakyThrows
    @Test
    void testGetBrandNotFound() {
        //given
        given(brandService.getBrand(1L)).willThrow(new BrandNotFoundException("Brand not found"));

        //when
        //then
        mockMvc.perform(get("/api/v1/brand?brand=1"))
                .andExpect(status().isNotFound());
    }
}
