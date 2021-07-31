package com.isika.prestigeacademy.repositories;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.isika.prestigeacademy.model.entities.DossierPaiement;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.model.entities.StatutFinancement;
import com.isika.prestigeacademy.model.entities.TypeFinancement;


public class FinancementRepository {
	private static final Logger LOGGER = Logger.getLogger(FinancementRepository.class.getSimpleName());
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private Event<Stagiaire> stagiaireEventSource;
	
	
	public List<Stagiaire> findAll() {
		return this.entityManager.createNamedQuery("Stagiaire.findAll", Stagiaire.class).getResultList();
	}
	public List<DossierPaiement> findAllDossierPaiement(){
		return this.entityManager.createNamedQuery("DossierPaiement.findAll", DossierPaiement.class).getResultList();
	}

	public List<StatutFinancement> findAllStatutFinancement() {
		return this.entityManager.createNamedQuery("StatutFinancement.findAll", StatutFinancement.class).getResultList();
	}
	
	public List<TypeFinancement> findAllTypeFinancement() {
		return this.entityManager.createNamedQuery("TypeFinancement.findAll", TypeFinancement.class).getResultList();
	}
	public Stagiaire update(Stagiaire stagiaireFinSelected) {
		// TODO Auto-generated method stub
		return this.entityManager.merge(stagiaireFinSelected);
	}

}
