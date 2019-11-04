package com.cashmanager.repository;
import java.util.List;
import com.cashmanager.model.Panier;

public interface PanierRepositoryCustom {

    List<Panier> getbyidpanier(Long id_panier);
    void deletebyidpanier(Long id_panier);
}
