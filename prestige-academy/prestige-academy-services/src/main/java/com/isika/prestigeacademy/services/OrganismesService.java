package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.OrganismeFinancement;
import com.isika.prestigeacademy.model.entities.Statut;
import com.isika.prestigeacademy.model.entities.TypeFinancement;
import com.isika.prestigeacademy.model.entities.TypeProspect;
import com.isika.prestigeacademy.repositories.organismeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class OrganismesService {

	@Inject
	private organismeRepository organismeRepository;

	public List<OrganismeFinancement> rechercherTousLesOrganismes() {
		return organismeRepository.findAll();
	}
	
	public List<TypeFinancement> rechercherTousLesTypeFinancement() {
		return organismeRepository.findAllTypeFinancement();
	}

	public List<TypeProspect> rechercherTousLesTypeProspect() {
		return organismeRepository.findAllTypeProspect();
	}

	public List<Statut> rechercherTousLesStatut() {
		return organismeRepository.findAllStatut();
	}
	
	public long creerOrganismeFinancement(OrganismeFinancement organismeFinancementCree, TypeFinancement selectedtypeFin,
			TypeProspect selectedtypePros, Statut selectedtypeStat) {
		selectedtypeFin.addOrganismeFinancement(organismeFinancementCree);
		selectedtypePros.addOrganismeFinancement(organismeFinancementCree);
		selectedtypeStat.addOrganismeFinancement(organismeFinancementCree);
		return organismeRepository.creer(organismeFinancementCree);
	}

	public boolean supprimerOrganisme(Long organismeFinancementId) {
		OrganismeFinancement organismeFinancementById = organismeRepository.findById(organismeFinancementId);
		if (organismeFinancementById != null) {
			return organismeRepository.remove(organismeFinancementById);
		}
		return false;
	}

	public OrganismeFinancement modifier(OrganismeFinancement organismeFinancementSelected) {
		return organismeRepository.update(organismeFinancementSelected);
	}

}
