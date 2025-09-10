package org.example.repository;

import java.util.List;
import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "select * from products p where p.user_id = :userId", nativeQuery = true)
  List<Product> findAllByUserId(Long userId);
}
