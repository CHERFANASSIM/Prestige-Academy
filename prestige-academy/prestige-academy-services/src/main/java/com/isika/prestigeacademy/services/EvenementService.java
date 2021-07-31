package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.Evenement;
import com.isika.prestigeacademy.model.entities.TypeEvenement;
import com.isika.prestigeacademy.model.entities.Utilisateur;
import com.isika.prestigeacademy.repositories.EvenementRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Stateless
public class EvenementService implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 7258590306434824047L;

	@Inject
	private EvenementRepository evenementRepository;

	public Long creerEvenement(Evenement evenement, TypeEvenement selectedTypeEvenement,
			Utilisateur selectedUtilisateur) {
		selectedTypeEvenement.addEvenement(evenement);
		selectedUtilisateur.addEvenement(evenement);

		return evenementRepository.creer(evenement);
	}

	public List<Evenement> rechercheToutEvenement() {
		return evenementRepository.findAll();
	}


	public List<TypeEvenement> rechercheToutTypesEvenements() {
		return evenementRepository.findAllTypeEvenement();
	}

	public List<Utilisateur> rechercheToutUtilisateurs() {
		return evenementRepository.findAllUtilisateur();
	}


	public boolean supprimerEvenement(Long id) {
		Evenement evenementById = evenementRepository.findById(id);
		if (evenementById != null) {
			return evenementRepository.remove(evenementById);
		}
		return false;
	}

	public List<Evenement> rechercherLieu() {
		return Arrays.asList(new Evenement());
	}

	public Evenement modifier(Evenement evenementSelected) {
		return evenementRepository.update(evenementSelected);
	}

//Planning

	public Evenement insererRDV(Evenement evenementRDV) {
		return evenementRepository.saveRDV(evenementRDV);

	}

}
