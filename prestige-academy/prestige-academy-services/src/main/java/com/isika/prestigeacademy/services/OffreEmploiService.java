package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.OffreEmploi;
import com.isika.prestigeacademy.model.entities.PreferenceTypeContrat;
import com.isika.prestigeacademy.repositories.OffreEmploiRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class OffreEmploiService implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4602810791391943475L;

	@Inject
	private OffreEmploiRepository offreEmploiRepo;


	public Long addOffreEmploi (OffreEmploi job, Entreprise selectedEntreprise) {

		selectedEntreprise.addOffreEmploi(job);
		return offreEmploiRepo.addOffreEmploi(job);
	}

	public List<OffreEmploi> rechercheAllOffreEmploi(){
		return	offreEmploiRepo.findAll();

	}

	public List <Entreprise> rechercheEntreprise(){

		return offreEmploiRepo.findAllEntreprises();
	}

	public List <PreferenceTypeContrat> rechercheTypeContract(){
		return offreEmploiRepo.findAllTypeContrat();
	}

	public boolean supprimerOffreEmploi(Long long1) {
		OffreEmploi offreEmploiById = offreEmploiRepo.findById(long1);
		if (offreEmploiById != null) {
			return offreEmploiRepo.remove(offreEmploiById);
		}
		return false;
	}

	public OffreEmploi modifier(OffreEmploi SelectedJob) {
		return offreEmploiRepo.update(SelectedJob);
	}

	public OffreEmploiRepository getOffreEmploiRepo() {
		return offreEmploiRepo;
	}

	public void setOffreEmploiRepo(OffreEmploiRepository offreEmploiRepo) {
		this.offreEmploiRepo = offreEmploiRepo;
	}







}
