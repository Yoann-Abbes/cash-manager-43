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
    public List<Panier> getbyidpanier(Long id_panier) {
        Query query = entityManager.createNativeQuery("SELECT * FROM  Panier p " +

                "WHERE p.id_panier=?");


        query.setParameter(1, id_panier);
        return query.getResultList();
    }


    public void deletebyidpanier(Long id_panier){
        Query query = entityManager.createNativeQuery("DELETE  FROM Panier WHERE id_panier = ?");
        query.setParameter(1, id_panier);
    };
}
