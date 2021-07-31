package com.isika.prestigeacademy.repositories;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.isika.prestigeacademy.model.entities.Entreprise;

import com.isika.prestigeacademy.model.entities.OffreEmploi;
import com.isika.prestigeacademy.model.entities.PreferenceTypeContrat;

@Stateless
public class OffreEmploiRepository {


	private static final Logger LOGGER = Logger.getLogger(OffreEmploiRepository.class.getSimpleName());

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private Event<OffreEmploi> offreEmploiEventSource;

	public Long addOffreEmploi(OffreEmploi job) {
		this.entityManager.persist(job);
		this.entityManager.flush();
		offreEmploiEventSource.fire(job);
		LOGGER.info("Persisted offreEmploi : " + job);
		return job.getOffreEmploiID();
	}
	public List<OffreEmploi> findAll() {
		return this.entityManager.createNamedQuery("OffreEmploi.findAll",OffreEmploi.class).getResultList();
	}

	public List<Entreprise> findAllEntreprises() {
		return this.entityManager.createNamedQuery("Entreprise.findAll", Entreprise.class).getResultList();
	}
	
	public List<PreferenceTypeContrat> findAllTypeContrat() {
		return this.entityManager.createNamedQuery("PreferenceTypeContrat.findAll", PreferenceTypeContrat.class).getResultList();
	}
	public boolean remove(OffreEmploi job) {
		this.entityManager.remove(job);
		return true;
	}

	public OffreEmploi update(OffreEmploi SelectedJob) {
		return this.entityManager.merge(SelectedJob);
	}

	public OffreEmploi findById(Long long1) {

		return this.entityManager.find(OffreEmploi.class, long1);
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public Event<OffreEmploi> getOffreEmploiEventSource() {
		return offreEmploiEventSource;
	}
	public void setOffreEmploiEventSource(Event<OffreEmploi> offreEmploiEventSource) {
		this.offreEmploiEventSource = offreEmploiEventSource;
	}

	}



