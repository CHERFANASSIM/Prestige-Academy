package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.DossierPaiement;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.model.entities.StatutFinancement;
import com.isika.prestigeacademy.model.entities.TypeFinancement;
import com.isika.prestigeacademy.repositories.EvenementRepository;
import com.isika.prestigeacademy.repositories.FinancementRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class FinancementService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4444444444L;
	@Inject
	private FinancementRepository financementRepository;
	
	public List<Stagiaire> rechercheToutStagiaire() {
		return financementRepository.findAll();
	}

	public List<TypeFinancement> rechercheToutTypesEvenements() {
		return financementRepository.findAllTypeFinancement();
	}

	public List<StatutFinancement> rechercheStatutsFinancements() {
		return financementRepository.findAllStatutFinancement();
	}
	
	public List<DossierPaiement> rechercheDossiersFinancements() {
		return financementRepository.findAllDossierPaiement();
	}
	
	public Stagiaire modifier( Stagiaire stagiaireFinSelected) {

		return financementRepository.update(stagiaireFinSelected);
	}

	

}
