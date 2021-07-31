package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.EmailRecu;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Remote
public class EmailRecuRepository {

	
	private static final Logger LOGGER = Logger.getLogger(EmailRecuRepository.class.getSimpleName());

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Inject
	private Event<EmailRecu> emailRecuEventSource;



	public long creer1(EmailRecu email) {
		this.entityManager.persist(email);
		this.entityManager.flush();
		emailRecuEventSource.fire(email);
		LOGGER.info("Persisted evenement : " + email);
		return email.getEmailRecuID();

	}



	public long creer2(EmailRecu email) {
		this.entityManager.persist(email);
		this.entityManager.flush();
		emailRecuEventSource.fire(email);
		LOGGER.info("Persisted evenement : " + email);
		return email.getEmailRecuID();
	}



	public List<EmailRecu> rechercheLesMailsParDestinataire(Long stagiaireID) {
		TypedQuery<EmailRecu> query = this.entityManager.createNamedQuery("EmailRecu.findEmails", EmailRecu.class);
		query.setParameter("stagiaireID", stagiaireID);
		return query.getResultList();
	}
	
	
}
