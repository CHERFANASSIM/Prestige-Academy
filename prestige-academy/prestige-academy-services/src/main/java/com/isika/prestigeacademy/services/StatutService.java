package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.Statut;
import com.isika.prestigeacademy.repositories.StatutRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class StatutService implements Serializable {

    @Inject
    private StatutRepository statutRepository;

    public List<Statut> RechercherTousStatut() {
        return statutRepository.findAll();
    }

    public Statut typeSt(Long st) {
        return statutRepository.findSt(st);
    }

    public void createStatus(Statut statut) {
        statutRepository.ajouter(statut);
    }

}
