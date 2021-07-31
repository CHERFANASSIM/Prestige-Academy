package com.isika.prestigeacademy.repositories;


import com.isika.prestigeacademy.model.entities.Statut;
import com.isika.prestigeacademy.repositories.dao.GenericDao;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class StatutRepository extends GenericDao<Statut> {


	private static final Logger LOGGER = Logger.getLogger(StatutRepository.class.getSimpleName());

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private Event<Statut> statutEventSource;

	public List<Statut> findAll() {
		return this.entityManager.createNamedQuery("Statut.findAll", Statut.class).getResultList();
	}

	public Statut findSt(Long st) {
		return this.entityManager.find(Statut.class, st);
	}

	public Statut ajouter(Statut statut) {
		add(statut);
		statutEventSource.fire(statut);
		LOGGER.info("Persisted entreprise : " + statut);

		return statut;
	}
}
