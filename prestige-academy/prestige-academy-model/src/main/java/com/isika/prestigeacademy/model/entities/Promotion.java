package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the promotion database table.
 * 
 */
@Entity
@NamedQuery(name="Promotion.findAll", query="SELECT p FROM Promotion p")
public class Promotion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long promotionID;

	private int anneePromotion;

	private String nomPromotion;

	//bi-directional many-to-one association to SpecialiteFormation
	@ManyToOne
	@JoinColumn(name = "specialiteFormationID")
	private SpecialiteFormation specialiteFormation;

	//bi-directional many-to-one association to Stagiaire
	@OneToMany(mappedBy = "promotion")
	private List<Stagiaire> stagiaires;

	public Promotion() {
	}

	public Long getPromotionID() {
		return this.promotionID;
	}

	public void setPromotionID(Long promotionID) {
		this.promotionID = promotionID;
	}

	public int getAnneePromotion() {
		return this.anneePromotion;
	}

	public void setAnneePromotion(int anneePromotion) {
		this.anneePromotion = anneePromotion;
	}

	public String getNomPromotion() {
		return this.nomPromotion;
	}

	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}

	public SpecialiteFormation getSpecialiteFormation() {
		return this.specialiteFormation;
	}

	public void setSpecialiteFormation(SpecialiteFormation specialiteFormation) {
		this.specialiteFormation = specialiteFormation;
	}

	public List<Stagiaire> getStagiaires() {
		return this.stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Stagiaire addStagiaire(Stagiaire stagiaire) {
		getStagiaires().add(stagiaire);
		stagiaire.setPromotion(this);

		return stagiaire;
	}

	public Stagiaire removeStagiaire(Stagiaire stagiaire) {
		getStagiaires().remove(stagiaire);
		stagiaire.setPromotion(null);

		return stagiaire;
	}

}