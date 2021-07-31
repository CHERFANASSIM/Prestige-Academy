package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the promotion database table.
 * 
 */


@Entity
@NamedQueries({@NamedQuery(name = "ProcessusRecrutement.findAll", query = "SELECT p FROM ProcessusRecrutement p"),
		@NamedQuery(name = "ProcessusRecrutement.findListRecru", query = "SELECT p FROM ProcessusRecrutement p WHERE p.stagiaire.stagiaireID= :stagiaireID"),
		@NamedQuery(name = "ProcessusRecrutement.findListRecru2", query = "SELECT p FROM ProcessusRecrutement p WHERE p.entreprise.entrepriseID= :entrepriseID")
})


public class ProcessusRecrutement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long processusRecrutementID;

	// bi-directional many-to-one association to StatutProcessusRecrutement
	@ManyToOne
	@JoinColumn(name = "statutProcessusRecrutementID")
	private StatutProcessusRecrutement statutProcessusRecrutement;

	// bi-directional many-to-one association to Entreprise
	@ManyToOne
	@JoinColumn(name = "entrepriseID")
	private Entreprise entreprise;

	// bi-directional many-to-one association to Stagiaires
	@ManyToOne
	@JoinColumn(name = "stagiaireID")
	private Stagiaire stagiaire;

	public ProcessusRecrutement() {
	}

	public Long getProcessusRecrutementID() {
		return processusRecrutementID;
	}

	public void setProcessusRecrutementID(Long processusRecrutementID) {
		this.processusRecrutementID = processusRecrutementID;
	}

	public StatutProcessusRecrutement getStatutProcessusRecrutement() {
		return statutProcessusRecrutement;
	}

	public void setStatutProcessusRecrutement(StatutProcessusRecrutement statutProcessusRecrutement) {
		this.statutProcessusRecrutement = statutProcessusRecrutement;
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

}