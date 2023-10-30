package dev.bracers.inditex.controller;

import dev.bracers.inditex.exception.PriceNotFoundException;
import dev.bracers.inditex.response.PriceResponse;
import dev.bracers.inditex.service.PriceService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "api/v1/price", produces = "application/json")
public class PricesController {

    private final PriceService priceService;

    public PricesController(PriceService priceService) {
        this.priceService = priceService;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Price found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PriceResponse.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Price not found",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<PriceResponse> getPrices(
            @Validated
            @RequestParam(name = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime date,
            @Validated
            @RequestParam(name = "product")
            Long productId,
            @Validated
            @RequestParam(name = "brand")
            Long brandId
    ) {
        PriceResponse response;
        try {
            response = priceService.getPrice(date, productId, brandId);
        } catch (PriceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

}
