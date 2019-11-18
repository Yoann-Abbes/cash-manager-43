package com.cashmanager.repository;
import com.cashmanager.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProduitRepository extends JpaRepository<Produit, Long>{
    Page<Produit> findByPanierId(Long idPanier, Pageable pageable);
    Optional<Produit> findByIdAndPanierId(Long idProduit, Long idPanier);
}