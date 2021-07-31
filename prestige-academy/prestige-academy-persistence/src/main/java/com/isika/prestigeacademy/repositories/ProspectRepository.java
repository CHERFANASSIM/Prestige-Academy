package com.isika.prestigeacademy.repositories;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.Statut;
import com.isika.prestigeacademy.model.entities.TypeProspect;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProspectRepository {

	private static final Logger LOGGER = Logger.getLogger(ProspectRepository.class.getSimpleName());

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private Event<Entreprise> entrepriseEventSource;

	public Long creer(Entreprise entreprise) {
		this.entityManager.persist(entreprise);
		this.entityManager.flush();
		entrepriseEventSource.fire(entreprise);
		LOGGER.info("Persisted entreprise prospect: " + entreprise);
		return entreprise.getEntrepriseID();
	}

	public List<Entreprise> findAll() {
		return this.entityManager.createNamedQuery("Entreprise.findAll", Entreprise.class).getResultList();
	}

	public List<Statut> findAllStatut() {
		return this.entityManager.createNamedQuery("Statut.findAll", Statut.class).getResultList();
	}

	public List<TypeProspect> findAllTypeEvenement() {
		return this.entityManager.createNamedQuery("TypeProspect.findAll", TypeProspect.class).getResultList();
	}

	public List<Entreprise> findProspect(Long statutID) {
		TypedQuery<Entreprise> query = this.entityManager.createNamedQuery("Entreprise.findProspect", Entreprise.class);
		query.setParameter("statutIDParam", statutID);
		return query.getResultList();
	}
	public List<Statut> findStatutName(String nomStatut) {
	TypedQuery<Statut> query = this.entityManager.createQuery("SELECT s FROM Statut s WHERE s.nomStatut=:nomStatutParam", Statut.class);
    query.setParameter("nomStatutParam", nomStatut);
	return query.getResultList();
	}
	
	public Entreprise findById(Long entrepriseID) {
		return this.entityManager.find(Entreprise.class, entrepriseID);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
