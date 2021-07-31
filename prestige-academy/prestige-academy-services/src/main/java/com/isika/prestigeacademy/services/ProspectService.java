package com.isika.prestigeacademy.services;


import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.Statut;
import com.isika.prestigeacademy.model.entities.TypeProspect;
import com.isika.prestigeacademy.repositories.ProspectRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ProspectService implements Serializable {

	@Inject
	private ProspectRepository prospectRepository;
	private Long statutID;

	public List<Entreprise> rechercherLesProspects() {
		statutID = 2L;
		return prospectRepository.findProspect(statutID);
	}

	public Long creerProspect(Entreprise entrepriseProspect) {
		return prospectRepository.creer(entrepriseProspect);
	}

	public List<Statut> rechercherStatuts() {
		return prospectRepository.findAllStatut();
	}
 public List<Statut> rechercheAuto(String nomStatut) {
	 return prospectRepository.findStatutName(nomStatut);
	 
 }
 
	
	public List<TypeProspect> rechercheToutTypeDeProspect() {
		// TODO Auto-generated method stub
		return null;
	}



}

	
	





