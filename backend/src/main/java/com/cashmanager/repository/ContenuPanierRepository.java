package com.cashmanager.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cashmanager.model.ContenuPanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContenuPanierRepository extends JpaRepository<ContenuPanier, Long> {
    Page<ContenuPanier> findByCartId(Long cartId, Pageable pageable);
    Optional<ContenuPanier> findByIdAndCartId(Long id, Long cartId);
    Page<ContenuPanier> findByProductId(Long productId, Pageable pageable);
    Optional<ContenuPanier> findByIdAndProductId(Long id, Long productId);
}
