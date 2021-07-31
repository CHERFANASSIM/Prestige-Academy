package com.isika.prestigeacademy.repositories;


import com.isika.prestigeacademy.model.entities.EnquetesSatisfaction;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;




@Stateless
@Remote
public class EnqueteStatRepository {

	private static final Logger LOGGER = Logger.getLogger(EmailRecuRepository.class.getSimpleName());
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private Event<EnquetesSatisfaction> enquetStatEventSource;
	

	public long creation1(EnquetesSatisfaction enquete) {
		this.entityManager.persist(enquete);
		this.entityManager.flush();
		enquetStatEventSource.fire(enquete);
		LOGGER.info("Persisted evenement : " + enquete);
		return enquete.getEnquetesSatisfactionID();
	}



	
}
