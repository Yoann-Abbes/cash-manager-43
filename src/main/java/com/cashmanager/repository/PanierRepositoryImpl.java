package com.cashmanager.repository;

import com.cashmanager.model.Panier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PanierRepositoryImpl implements PanierRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Panier> getbyidclient(Long idClient) {
        Query query = entityManager.createNativeQuery("SELECT * FROM  Panier p " +

                "WHERE p.idClient=?");


        query.setParameter(1, idClient);
        return query.getResultList();
    }


    public void deletebyidclient(Long idClient){
        Query query = entityManager.createNativeQuery("DELETE  FROM Panier WHERE idClient = ?");
        query.setParameter(1, idClient);
    };
}
