package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.OrganismeFinancement;
import com.isika.prestigeacademy.model.entities.Statut;
import com.isika.prestigeacademy.model.entities.TypeFinancement;
import com.isika.prestigeacademy.model.entities.TypeProspect;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class organismeRepository {

	private static final Logger LOGGER = Logger.getLogger(OrganismeFinancement.class.getSimpleName());

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private Event<OrganismeFinancement> organismesEventDource;

	public List<OrganismeFinancement> findAll() {
		return this.entityManager.createNamedQuery("OrganismeFinancement.findAll", OrganismeFinancement.class)
				.getResultList();
	}

	public OrganismeFinancement findById(long organismeFinancementId) {
		return this.entityManager.find(OrganismeFinancement.class, organismeFinancementId);
	}

	public boolean remove(OrganismeFinancement organismeFinancement) {
		this.entityManager.remove(organismeFinancement);
		return true;
	}

	public List<TypeFinancement> findAllTypeFinancement() {
		return this.entityManager.createNamedQuery("TypeFinancement.findAll", TypeFinancement.class).getResultList();
	}

	public List<TypeProspect> findAllTypeProspect() {
		return this.entityManager.createNamedQuery("TypeProspect.findAll", TypeProspect.class).getResultList();
	}

	public List<Statut> findAllStatut() {
		return this.entityManager.createNamedQuery("Statut.findAll", Statut.class).getResultList();
	}

	public Event<OrganismeFinancement> getOrganismesEventDource() {
		return organismesEventDource;
	}

	public void setOrganismesEventDource(Event<OrganismeFinancement> organismesEventDource) {
		this.organismesEventDource = organismesEventDource;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public long creer(OrganismeFinancement organismeFinancementCree) {
		this.entityManager.persist(organismeFinancementCree);
		this.entityManager.flush();
		organismesEventDource.fire(organismeFinancementCree);
		LOGGER.info("Persisted organisme financement : " + organismeFinancementCree);
		return organismeFinancementCree.getOrganismeFinancementID();
	}

	public OrganismeFinancement update(OrganismeFinancement organismeFinancementSelected) {
		return this.entityManager.merge(organismeFinancementSelected);
	}
}
