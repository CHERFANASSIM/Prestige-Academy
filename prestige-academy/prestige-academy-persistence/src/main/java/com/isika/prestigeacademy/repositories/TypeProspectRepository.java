package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.TypeProspect;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TypeProspectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TypeProspect> findALL() {
        return this.entityManager.createNamedQuery("TypeProspect.findAll", TypeProspect.class).getResultList();
    }

    public TypeProspect findpr(Long pr) {
        return this.entityManager.find(TypeProspect.class, pr);
    }

}
