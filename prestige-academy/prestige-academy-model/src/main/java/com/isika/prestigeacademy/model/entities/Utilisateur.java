package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the utilisateurs database table.
 * 
 */
@Entity
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long utilisateursID;

	private String fonctionUtilisateur;

	private String identifiantUtilisateur;

	private String motdepasseUtilisateur;

	private String nomPrenomUtilisateurs;

	private String nomUtilisateur;

	private String photoUrlUtilisateur;

	private String prenomUtilisateur;

	//bi-directional many-to-one association to Evenement
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "utilisateur")
	private List<Evenement> evenements;

	//bi-directional many-to-one association to NiveauAcces
	@ManyToOne
	@JoinColumn(name = "niveauAccesID")
	private NiveauAcces niveauAcces;


	public Utilisateur() {
	}

	public Long getUtilisateursID() {
		return this.utilisateursID;
	}

	public void setUtilisateursID(Long utilisateursID) {
		this.utilisateursID = utilisateursID;
	}

	public String getFonctionUtilisateur() {
		return this.fonctionUtilisateur;
	}

	public void setFonctionUtilisateur(String fonctionUtilisateur) {
		this.fonctionUtilisateur = fonctionUtilisateur;
	}

	public String getIdentifiantUtilisateur() {
		return this.identifiantUtilisateur;
	}

	public void setIdentifiantUtilisateur(String identifiantUtilisateur) {
		this.identifiantUtilisateur = identifiantUtilisateur;
	}

	public String getMotdepasseUtilisateur() {
		return this.motdepasseUtilisateur;
	}

	public void setMotdepasseUtilisateur(String motdepasseUtilisateur) {
		this.motdepasseUtilisateur = motdepasseUtilisateur;
	}

	public String getNomPrenomUtilisateurs() {
		return this.nomPrenomUtilisateurs;
	}

	public void setNomPrenomUtilisateurs(String nomPrenomUtilisateurs) {
		this.nomPrenomUtilisateurs = nomPrenomUtilisateurs;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getPhotoUrlUtilisateur() {
		return this.photoUrlUtilisateur;
	}

	public void setPhotoUrlUtilisateur(String photoUrlUtilisateur) {
		this.photoUrlUtilisateur = photoUrlUtilisateur;
	}

	public String getPrenomUtilisateur() {
		return this.prenomUtilisateur;
	}

	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}

	public List<Evenement> getEvenements() {
		return this.evenements;
	}

	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}

	public Evenement addEvenement(Evenement evenement) {
		getEvenements().add(evenement);
		evenement.setUtilisateur(this);

		return evenement;
	}

	public Evenement removeEvenement(Evenement evenement) {
		getEvenements().remove(evenement);
		evenement.setUtilisateur(null);

		return evenement;
	}

	/*public Evenement updateEvenement(Evenement evenement) {
		getEvenements().remove(evenement);
		evenement.setUtilisateur(this);
		return evenement;
	}*/

	public NiveauAcces getNiveauAcces() {
		return this.niveauAcces;
	}

	public void setNiveauAcces(NiveauAcces niveauAcces) {
		this.niveauAcces = niveauAcces;
	}

	public Evenement updateEvenement(Evenement evenement) {

		getEvenements().remove(evenement);
		getEvenements().add(evenement);
		evenement.setUtilisateur(this);

		return evenement;

	}

}