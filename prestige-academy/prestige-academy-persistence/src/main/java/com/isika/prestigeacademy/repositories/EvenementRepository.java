package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.Evenement;
import com.isika.prestigeacademy.model.entities.TypeEvenement;
import com.isika.prestigeacademy.model.entities.Utilisateur;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class EvenementRepository {

	private static final Logger LOGGER = Logger.getLogger(EvenementRepository.class.getSimpleName());

	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	private Event<Evenement> evenementEventSource;

	public Long creer(Evenement evenement) {
		this.entityManager.persist(evenement);
		this.entityManager.flush();
		evenementEventSource.fire(evenement);
		LOGGER.info("Persisted evenement : " + evenement);
		return evenement.getEvenementID();
	}

	public List<Evenement> findAll() {
		return this.entityManager.createNamedQuery("Evenement.findAll", Evenement.class).getResultList();
	}
	public List<TypeEvenement> findAllTypeEvenement(){
		return this.entityManager.createNamedQuery("TypeEvenement.findAll", TypeEvenement.class).getResultList();
	}

	public List<Utilisateur> findAllUtilisateur() {
		return this.entityManager.createNamedQuery("Utilisateur.findAll", Utilisateur.class).getResultList();
	}

	public boolean remove(Evenement evenement) {
		this.entityManager.remove(evenement);
		return true;
	}

	public Evenement findById(Long s) {
		return this.entityManager.find(Evenement.class, s);
	}

	public TypeEvenement findTypeEvenementByName(String name) {
		TypedQuery<TypeEvenement> query = this.entityManager.createQuery("SELECT t FROM TypeEvenement t WHERE t.nomTypeEvenement=:nameParam", TypeEvenement.class);
		query.setParameter("nameParam", name);
		return query.getSingleResult();
	}
	public List<Evenement> search(String lieuEvenement) {
		TypedQuery<Evenement> query = this.entityManager.createNamedQuery("Evenement.findByLieu", Evenement.class);
		query.setParameter("lieuParam", lieuEvenement);
		return query.getResultList();
	}


	public Evenement update(Evenement evenementSelected) {
		return this.entityManager.merge(evenementSelected);
	}
	


public EntityManager getEntityManager() {
	return entityManager;
}

public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
}


	public Evenement saveRDV(Evenement evenementRDV) {

		   return (Evenement) this.entityManager.createNativeQuery("INSERT INTO evenement(evenementID, dateEvenement,heureEvenement, descriptionEvenement, heureEvenement, lieuEvenement,personneEvenement,typeEvenement)"
					+ "VALUES(?,?,?,?,?)")
		      .setParameter(1, evenementRDV.getEvenementID())
		      .setParameter(2, evenementRDV.getDateEvenement())
		      .setParameter(3, evenementRDV.getHeureEvenement())
		      .setParameter(4, evenementRDV.getLieuEvenement())
		      .setParameter(5, evenementRDV.getDescriptionEvenement())
		      .getSingleResult();

	
	}

	

	
}
