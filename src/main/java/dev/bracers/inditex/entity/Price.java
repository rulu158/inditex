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
    @Column(nullable = false)
    private Long brandId;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime startDate;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime endDate;

    @NonNull
    @Column(nullable = false)
    private Long priceList;

    @NonNull
    @Column(nullable = false)
    private Long productId;

    @NonNull
    @Column(nullable = false)
    private Long priority;

    @NonNull
    @Column(nullable = false)
    private Double price;

    @NonNull
    @Column(nullable = false)
    private String curr;
}
