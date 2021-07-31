package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.ProcessusRecrutement;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.model.entities.StatutFinancement;
import com.isika.prestigeacademy.model.entities.StatutRecrutement;
import com.isika.prestigeacademy.repositories.StagiairesRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@Stateless
public class StagiairesService implements Serializable {

	@Inject
	private StagiairesRepository stagiairesRepository;
	private short idPromo;


	public List<Stagiaire> rechercheTousLesSommeliers() {
		idPromo = 2;
		return stagiairesRepository.findPromo(idPromo);
	}

	public List<Stagiaire> rechercheTousLesGerantsHotel() {
		idPromo = 1;
		return stagiairesRepository.findPromo(idPromo);
	}
	
	public List<Stagiaire> rechercheTousLesMaitresHotel() {
		idPromo =4;
		return stagiairesRepository.findPromo(idPromo);
	}
	
	public List<Stagiaire> rechercheTousLesChefsRang() {
		idPromo =3;
		return stagiairesRepository.findPromo(idPromo);
	}

	public Stagiaire afficher(Stagiaire selected) {
		return stagiairesRepository.update(selected);
	}
	
	public Stagiaire modifier(Stagiaire stagiaireSelected) {
		return stagiairesRepository.update(stagiaireSelected);
	}
	public List<StatutRecrutement> rechercheStatutsRecrutement() {
		return stagiairesRepository.findStatutsRecrutement();
	}
	public List<StatutFinancement> rechercheStatutsFinancement() {
		return stagiairesRepository.findStatutsFinancement();
	}
	
	public Stagiaire findbyId(long id) {
		id=1;
		return stagiairesRepository.findById(id);
	
	}
	public Stagiaire seConnecter(String mail, String mdp) {
		return stagiairesRepository.seConnecter(mail,mdp);
	}
	public List<Stagiaire> rechercheTousLesStagiaires() {
		return stagiairesRepository.findAll();
	}

	public List<ProcessusRecrutement> rechercheLesRecrutements(Long stagiaireID) {
		return stagiairesRepository.findListeRecru(stagiaireID);
	}
}
