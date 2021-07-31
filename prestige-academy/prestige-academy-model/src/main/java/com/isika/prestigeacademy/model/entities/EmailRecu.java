package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;



/**
 * The persistent class for the emailRecu database table.
 * 
 */
@Entity
@NamedQueries({@NamedQuery(name = "EmailRecu.findAll", query = "SELECT e FROM EmailRecu e"),
		@NamedQuery(name = "EmailRecu.findEmails", query = "SELECT e FROM EmailRecu e WHERE e.stagiaire.stagiaireID= :stagiaireID")
})
public class EmailRecu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emailRecuID;
	private String contenu;
	private String dateEvenement;

	private String objet;
	private String pieceJointe;



	//bi-directional many-to-one association to Entreprise
	@ManyToOne
	@JoinColumn(name = "entrepriseID")
	private Entreprise entreprise;

	//bi-directional many-to-one association to Stagiaires
	@ManyToOne
	@JoinColumn(name = "stagiaireID")
	private Stagiaire stagiaire;



	public EmailRecu() {
	}


	public Long getEmailRecuID() {
		return emailRecuID;
	}


	public void setEmailRecuID(Long emailRecuID) {
		this.emailRecuID = emailRecuID;
	}


	public String getDateEvenement() {
		return dateEvenement;
	}


	public void setDateEvenement(String string) {
		this.dateEvenement = string;
	}


	public String getObjet() {
		return objet;
	}


	public void setObjet(String objet) {
		this.objet = objet;
	}


	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
	}


	public Entreprise getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}


	public Stagiaire getStagiaire() {
		return stagiaire;
	}


	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}


	public String getPieceJointe() {
		return pieceJointe;
	}


	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}







}