package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.GererProspect;
import com.isika.prestigeacademy.repositories.ProspectGererRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ProspectGererService implements Serializable {

    @Inject
    private ProspectGererRepository prospectGererRepository;

    public GererProspect findStep1() {
        // return prospectGererRepository.findByStatut();
        return null;
    }

    public void createStatus(GererProspect gererProspect) {
        prospectGererRepository.add(gererProspect);
    }

    public List<GererProspect> chercherTous() {
        return prospectGererRepository.findeAll();
    }
}
