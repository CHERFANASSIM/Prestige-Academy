package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the niveau_acces database table.
 */
@Entity
@NamedQuery(name = "NiveauAcce.findAll", query = "SELECT n FROM NiveauAcces n")
public class NiveauAcces implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long niveauAccesID;

	private String nomNiveauAcces;

	//bi-directional many-to-one association to Entreprise
	@OneToMany(mappedBy = "niveauAcces")
	private List<Entreprise> entreprises;

	//bi-directional many-to-one association to Stagiaire
	@OneToMany(mappedBy = "niveauAcces")
	private List<Stagiaire> stagiaires;

	//bi-directional many-to-one association to Utilisateur
	@OneToMany(mappedBy = "niveauAcces")
	private List<Utilisateur> utilisateurs;

	public NiveauAcces() {
	}

	public Long getNiveauAccesID() {
		return this.niveauAccesID;
	}

	public void setNiveauAccesID(Long niveauAccesID) {
		this.niveauAccesID = niveauAccesID;
	}

	public String getNomNiveauAcces() {
		return this.nomNiveauAcces;
	}

	public void setNomNiveauAcces(String nomNiveauAcces) {
		this.nomNiveauAcces = nomNiveauAcces;
	}

	public List<Entreprise> getEntreprises() {
		return this.entreprises;
	}

	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public Entreprise addEntrepris(Entreprise entrepris) {
		getEntreprises().add(entrepris);
		entrepris.setNiveauAcces(this);

		return entrepris;
	}

	public Entreprise removeEntrepris(Entreprise entrepris) {
		getEntreprises().remove(entrepris);
		entrepris.setNiveauAcces(null);

		return entrepris;
	}

	public List<Stagiaire> getStagiaires() {
		return this.stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Stagiaire addStagiaire(Stagiaire stagiaire) {
		getStagiaires().add(stagiaire);
		stagiaire.setNiveauAcces(this);

		return stagiaire;
	}

	public Stagiaire removeStagiaire(Stagiaire stagiaire) {
		getStagiaires().remove(stagiaire);
		stagiaire.setNiveauAcces(null);

		return stagiaire;
	}

	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().add(utilisateur);
		utilisateur.setNiveauAcces(this);

		return utilisateur;
	}

	public Utilisateur removeUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().remove(utilisateur);
		utilisateur.setNiveauAcces(null);

		return utilisateur;
	}

	public Entreprise addEntreprise(Entreprise entreprise) {
		getEntreprises().add(entreprise);
		entreprise.setNiveauAcces(this);

		return entreprise;
	}
}