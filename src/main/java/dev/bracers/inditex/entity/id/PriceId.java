package dev.bracers.inditex.entity.id;

import dev.bracers.inditex.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class PriceId implements Serializable {
    private long brandId;

    private long productId;

    private long priceList;

    private long priority;
}
