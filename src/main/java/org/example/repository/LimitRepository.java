package org.example.repository;

import java.math.BigDecimal;
import java.util.Optional;
import org.example.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {

  Optional<Limit> findByClientId(Long clientId);

  @Modifying
  @Query(value = "update limits set amount = :defaultAmount"
      + "  where amount <> :defaultAmount", nativeQuery = true)
  void resetLimits(BigDecimal defaultAmount);
}
