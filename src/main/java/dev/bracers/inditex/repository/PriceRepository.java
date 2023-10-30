package dev.bracers.inditex.repository;

import dev.bracers.inditex.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.startDate <= ?3 AND p.endDate >= ?3 AND p.brandId = ?1 AND p.productId = ?2 AND p.priority = (SELECT MAX(priority) FROM Price WHERE startDate <= ?3 AND endDate >= ?3 AND brandId = ?1 AND productId = ?2)")
    Optional<Price> findPriceByDate(long brandId, long productId, LocalDateTime date);
}
