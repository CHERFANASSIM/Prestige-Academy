package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.TypeProspect;
import com.isika.prestigeacademy.repositories.TypeProspectRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TypeProspectService {

    @Inject
    private TypeProspectRepository typeProspectRepository;

    @Inject

    public List<TypeProspect> RechercherTousLesProspect() {
        return typeProspectRepository.findALL();
    }

    public TypeProspect typePr(Long pr) {
        pr = 1L;
        return typeProspectRepository.findpr(pr);
    }

}
