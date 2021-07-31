package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.EmailRecu;
import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.repositories.EmailRecuRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Stateless
@Remote
public class EmailRecuService implements Serializable {

	@EJB
	private EmailRecuRepository emailRecuRepository;


	public EmailRecuRepository getEmailRecuRepository() {
		return emailRecuRepository;
	}

	public void setEmailRecuRepository(EmailRecuRepository emailRecuRepository) {
		this.emailRecuRepository = emailRecuRepository;
	}


	public long creation1(EmailRecu newEmail, Stagiaire stagiaire, String subject, String message,String attachement) {
		stagiaire.addEmailRecu(newEmail);
		newEmail.setContenu(message);
		newEmail.setObjet(subject);
		newEmail.setPieceJointe(attachement);
		newEmail.setDateEvenement(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		return emailRecuRepository.creer1(newEmail);
	}

	public long creation2(EmailRecu newEmail, Entreprise entreprise, String subject, String message, String attachement) {
		entreprise.addEmailsRecu(newEmail);
		newEmail.setContenu(message);
		newEmail.setObjet(subject);
		newEmail.setPieceJointe(attachement);
		newEmail.setDateEvenement(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		return emailRecuRepository.creer2(newEmail);
	}

	public List<EmailRecu> rechercheLesMailsParDestinataire(Long stagiaireID) {
		return emailRecuRepository.rechercheLesMailsParDestinataire(stagiaireID);
	}

}
