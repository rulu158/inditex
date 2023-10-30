package dev.bracers.inditex.entity;

import dev.bracers.inditex.entity.id.PriceId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(PriceId.class)
public class Price {
    @Id
    private long brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Id
    private long priceList;

    @Id
    private long productId;

    @Id
    private long priority;

    private double price;

    private String curr;
}
