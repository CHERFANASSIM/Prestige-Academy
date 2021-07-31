package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.Evenement;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PlanningRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	private Event<Evenement> evenementEventSource;
	
	public Evenement save(Evenement evenementRDV) {
	 return (Evenement) this.entityManager.createNativeQuery(
					"INSERT INTO evenement(evenementID, dateEvenement,heureEvenement, descriptionEvenement, heureEvenement, lieuEvenement,personneEvenement,typeEvenement)"
							+ "VALUES(?,?,?,?,?,?,?,?)")
					.setParameter(1, evenementRDV.getEvenementID()).setParameter(2, evenementRDV.getDateEvenement())
					.setParameter(3, evenementRDV.getHeureEvenement()).setParameter(4, evenementRDV.getLieuEvenement())
					.setParameter(5, evenementRDV.getDescriptionEvenement()).setParameter(6, evenementRDV.getTypeEvenement().getNomTypeEvenement()).setParameter(6, evenementRDV.getUtilisateur().getNomUtilisateur()).getSingleResult();

		

		
	

}
}
