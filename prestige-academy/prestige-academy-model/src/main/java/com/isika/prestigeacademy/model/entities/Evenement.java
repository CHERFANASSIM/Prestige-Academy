package com.isika.prestigeacademy.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the evenement database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e"),
		@NamedQuery(name = "Evenement.findByLieu", query = "SELECT e FROM Evenement e WHERE e.lieuEvenement = :lieuParam"),
		@NamedQuery(name = "Evenement.findByID", query = "SELECT e FROM Evenement e WHERE e.evenementID = :evIdParam") })

public class Evenement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long evenementID;

	@Temporal(TemporalType.DATE)
	private Date dateEvenement;

	private String descriptionEvenement;

	private String heureEvenement;

	private String lieuEvenement;

	private String personneEvenement;

	//bi-directional many-to-one association to TypeEvenement
	@ManyToOne
	@JoinColumn(name="typeEvenementID")
	private TypeEvenement typeEvenement;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="utilisateurID")
	private Utilisateur utilisateur;

	@OneToOne(cascade = CascadeType.MERGE)
	private CompteRendu compteRendu;

	public Evenement() {
	}

	public Long getEvenementID() {
		return this.evenementID;
	}

	public void setEvenementID(Long evenementID) {
		this.evenementID = evenementID;
	}

	public Date getDateEvenement() {
		return this.dateEvenement;
	}

	public void setDateEvenement(Date dateEvenement) {
		this.dateEvenement = dateEvenement;
	}

	public String getDescriptionEvenement() {
		return this.descriptionEvenement;
	}

	public void setDescriptionEvenement(String descriptionEvenement) {
		this.descriptionEvenement = descriptionEvenement;
	}

	public String getHeureEvenement() {
		return this.heureEvenement;
	}

	public void setHeureEvenement(String heureEvenement) {
		this.heureEvenement = heureEvenement;
	}

	public String getLieuEvenement() {
		return this.lieuEvenement;
	}

	public void setLieuEvenement(String lieuEvenement) {
		this.lieuEvenement = lieuEvenement;
	}

	public String getPersonneEvenement() {
		return this.personneEvenement;
	}

	public void setPersonneEvenement(String personneEvenement) {
		this.personneEvenement = personneEvenement;
	}

	public TypeEvenement getTypeEvenement() {
		return this.typeEvenement;
	}

	public void setTypeEvenement(TypeEvenement typeEvenement) {
		this.typeEvenement = typeEvenement;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public CompteRendu getCompteRendu() {
		return compteRendu;
	}

	public void setCompteRendu(CompteRendu compteRendu) {
		this.compteRendu = compteRendu;
	}

}