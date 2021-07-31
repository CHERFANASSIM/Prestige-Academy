package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the statut_recrutement database table.
 * 
 */
@Entity
@NamedQuery(name="StatutRecrutement.findAll", query="SELECT s FROM StatutRecrutement s")
public class StatutRecrutement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statutRecrutementID;

	private String nomStatutRecrutement;

	//bi-directional many-to-one association to Stagiaire
	@OneToMany(mappedBy = "statutRecrutement")
	private List<Stagiaire> stagiaires;

	public StatutRecrutement() {
	}

	public Long getStatutRecrutementID() {
		return this.statutRecrutementID;
	}

	public void setStatutRecrutementID(Long statutRecrutementID) {
		this.statutRecrutementID = statutRecrutementID;
	}

	public String getNomStatutRecrutement() {
		return this.nomStatutRecrutement;
	}

	public void setNomStatutRecrutement(String nomStatutRecrutement) {
		this.nomStatutRecrutement = nomStatutRecrutement;
	}

	public List<Stagiaire> getStagiaires() {
		return this.stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Stagiaire addStagiaire(Stagiaire stagiaire) {
		getStagiaires().add(stagiaire);
		stagiaire.setStatutRecrutement(this);

		return stagiaire;
	}

	public Stagiaire removeStagiaire(Stagiaire stagiaire) {
		getStagiaires().remove(stagiaire);
		stagiaire.setStatutRecrutement(null);

		return stagiaire;
	}

}