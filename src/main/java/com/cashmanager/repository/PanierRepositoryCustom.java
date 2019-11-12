package com.cashmanager.repository;
import java.util.List;
import com.cashmanager.model.Panier;

public interface PanierRepositoryCustom {

    List<Panier> getbyidclient(Long id_client);
    void deletebyidclient(Long id_client);
}
