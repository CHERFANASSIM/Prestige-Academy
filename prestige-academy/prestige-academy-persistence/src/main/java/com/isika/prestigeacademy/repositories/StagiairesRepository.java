package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.EmailRecu;
import com.isika.prestigeacademy.model.entities.ProcessusRecrutement;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.model.entities.StatutFinancement;
import com.isika.prestigeacademy.model.entities.StatutRecrutement;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class StagiairesRepository {

	private static final Logger LOGGER = Logger.getLogger(StagiairesRepository.class.getSimpleName());

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private Event<Stagiaire> stagiaireEventSource;

	public Long creer(Stagiaire stagiaire) {
		this.entityManager.persist(stagiaire);
		this.entityManager.flush();

		// On notifie les composants qui écoutent des evts sur l'ajout de Personne
		// qu'une entité vient d'être persistée
		stagiaireEventSource.fire(stagiaire);
		
		// Message informatif pour les logs serveur
		LOGGER.info("Persisted stagiaire : " + stagiaire);
		return stagiaire.getStagiaireID();
	}

	public List<Stagiaire> findAll() {
		return this.entityManager.createNamedQuery("Stagiaire.findAll", Stagiaire.class).getResultList();
	}
	public List<Stagiaire> findPromo(long idPromoS) {
		
		TypedQuery<Stagiaire> query = this.entityManager.createNamedQuery("Stagiaire.findPromo", Stagiaire.class);
		query.setParameter("promoID", idPromoS);
		return query.getResultList();
	}

	public Stagiaire findById(long stagiaireID) {
		return this.entityManager.find(Stagiaire.class, stagiaireID);
	}

	public Stagiaire findByName(String nomStagiaire) {
		return this.entityManager.find(Stagiaire.class, nomStagiaire);
	}
	
	public Stagiaire findByPrenom(String prenomStagiaire) {
		return this.entityManager.find(Stagiaire.class, prenomStagiaire);
	}
	
	
	public Stagiaire update(Stagiaire stagiaireSelected) {
		return this.entityManager.merge(stagiaireSelected);
	}

	public List<StatutRecrutement> findStatutsRecrutement() {
		return this.entityManager.createNamedQuery("StatutRecrutement.findAll", StatutRecrutement.class).getResultList();
	}

	public List<StatutFinancement> findStatutsFinancement() {
		return this.entityManager.createNamedQuery("StatutFinancement.findAll", StatutFinancement.class).getResultList();
	}

	public Stagiaire seConnecter(String mail, String mdp) {
		Stagiaire stagiaire = null;

		Query query = entityManager.createQuery("select s FROM Stagiaire s WHERE s.mailStagiaire = :mailParam AND s.password = :passewordParam");
		query.setParameter("mailParam", mail);
		query.setParameter("passewordParam", mdp);

		List<Stagiaire> results = query.getResultList();
		if (results.size() == 1) {
			Stagiaire stagiaireS = results.get(0);
			stagiaire = stagiaireS;
		}
		return stagiaire;
	}

	public List<ProcessusRecrutement> findListeRecru(Long stagiaireID) {
		TypedQuery<ProcessusRecrutement> query = this.entityManager.createNamedQuery("ProcessusRecrutement.findListRecru", ProcessusRecrutement.class);
		query.setParameter("stagiaireID", stagiaireID);
		return query.getResultList();
	}

	

}
