package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the statut_financement database table.
 * 
 */
@Entity
@NamedQuery(name="StatutFinancement.findAll", query="SELECT s FROM StatutFinancement s")
public class StatutFinancement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statutFinancementID;

	private String nomFinancement;

	private String montant;
	
	private String nomOrganisme;

	//bi-directional many-to-one association to Stagiaire
	@OneToMany(mappedBy="statutFinancement")
	private List<Stagiaire> stagiaires;

	public StatutFinancement() {
	}

	public Long getStatutFinancementID() {
		return this.statutFinancementID;
	}

	public void setStatutFinancementID(Long statutFinancementID) {
		this.statutFinancementID = statutFinancementID;
	}

	public String getNomFinancement() {
		return this.nomFinancement;
	}

	public void setNomFinancement(String nomFinancement) {
		this.nomFinancement = nomFinancement;
	}

	public List<Stagiaire> getStagiaires() {
		return this.stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Stagiaire addStagiaire(Stagiaire stagiaire) {
		getStagiaires().add(stagiaire);
		stagiaire.setStatutFinancement(this);

		return stagiaire;
	}

	public Stagiaire removeStagiaire(Stagiaire stagiaire) {
		getStagiaires().remove(stagiaire);
		stagiaire.setStatutFinancement(null);

		return stagiaire;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public String getNomOrganisme() {
		return nomOrganisme;
	}

	public void setNomOrganisme(String nomOrganisme) {
		this.nomOrganisme = nomOrganisme;
	}

}