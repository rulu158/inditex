package dev.bracers.inditex.controller;

import dev.bracers.inditex.exception.BrandNotFoundException;
import dev.bracers.inditex.response.BrandResponse;
import dev.bracers.inditex.service.BrandService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/brand", produces = "application/json")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Brand found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = BrandResponse.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Brand not found",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<BrandResponse> getBrand(
            @Validated
            @RequestParam(name = "brand")
            Long brandId
    ) {
        BrandResponse response;
        try {
            response = brandService.getBrand(brandId);
        } catch (BrandNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
