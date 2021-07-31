package com.isika.prestigeacademy.services;


import com.isika.prestigeacademy.model.entities.*;
import com.isika.prestigeacademy.repositories.EntreprisesRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@Stateless
public class EntreprisesService implements Serializable {

    @Inject
    private EntreprisesRepository entreprisesRepository;


    public List<Entreprise> rechercheTousEntreprises() {
        return entreprisesRepository.findeAll();
    }

    public Entreprise seConnecter(String mail, String mdp) {
        return entreprisesRepository.seConnecter(mail, mdp);
    }

    public boolean supprimerEntreprise(Long entrepriseID) {
        Entreprise entrepriseById = entreprisesRepository.findById(entrepriseID);

        if (entrepriseById != null) {
            return entreprisesRepository.removeEntreprise(entrepriseById);
        }
        return false;
    }

    public List<TypeProspect> rechercheTousTypeProspect() {
        return entreprisesRepository.findTypeProspectAll();
    }

    public List<PreferenceTypeContrat> rechercheTousPrefeTypeContrat() {
        return entreprisesRepository.findPrefTypeContratAll();
    }

    public List<Statut> rechercheTousStatut() {
        return entreprisesRepository.findStatutsAll();
    }

    public List<NiveauAcces> rechercheTousNiveauAccess() {
        return entreprisesRepository.findNivAccessAll();
    }

    public Entreprise modifierEntreprise(Entreprise selectedEntreprise) {

        return entreprisesRepository.update(selectedEntreprise);
    }

    public Entreprise getbyID(Long entrepriseID) {
        return entreprisesRepository.findById(entrepriseID);
    }

    public Long creerEntreprise(Entreprise entreprise,
                                NiveauAcces niveauAcces, PreferenceTypeContrat preferenceTypeContrat,
                                Statut statut, TypeProspect typeProspect) {
        niveauAcces.addEntreprise(entreprise);
        preferenceTypeContrat.addEntreprise(entreprise);
        statut.addEntreprise(entreprise);
        typeProspect.addEntreprise(entreprise);
        return entreprisesRepository.ajouter(entreprise);
    }

    public List<Entreprise> rechercheParStatut(long id) {
        return entreprisesRepository.findByStatus(id);
    }

	public List<ProcessusRecrutement> rechercheLesRecrutements(Long entrepriseID) {
		return entreprisesRepository.findListProcessus(entrepriseID);
	}


}
