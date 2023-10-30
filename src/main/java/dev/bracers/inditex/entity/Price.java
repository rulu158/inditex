package dev.bracers.inditex.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"brandId", "startDate", "endDate", "priceList", "productId", "priority"})
})
public class Price {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private Long brandId;

    @NonNull
    private LocalDateTime startDate;

    @NonNull
    private LocalDateTime endDate;

    @NonNull
    private Long priceList;

    @NonNull
    private Long productId;

    @NonNull
    private Long priority;

    @NonNull
    private Double price;

    @NonNull
    private String curr;
}
