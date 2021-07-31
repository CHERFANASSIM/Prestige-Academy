package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the specialite_formation database table.
 * 
 */
@Entity
@NamedQuery(name="SpecialiteFormation.findAll", query="SELECT s FROM SpecialiteFormation s")
public class SpecialiteFormation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long specialitFformationID;

	private String dureeFormation;

	private String niveauEtudeAcces;

	private String nomPromotion;

	//bi-directional many-to-one association to Promotion
	@OneToMany(mappedBy = "specialiteFormation")
	private List<Promotion> promotions;

	public SpecialiteFormation() {
	}

	public Long getSpecialitFformationID() {
		return this.specialitFformationID;
	}

	public void setSpecialitFformationID(Long specialitFformationID) {
		this.specialitFformationID = specialitFformationID;
	}

	public String getDureeFormation() {
		return this.dureeFormation;
	}

	public void setDureeFormation(String dureeFormation) {
		this.dureeFormation = dureeFormation;
	}

	public String getNiveauEtudeAcces() {
		return this.niveauEtudeAcces;
	}

	public void setNiveauEtudeAcces(String niveauEtudeAcces) {
		this.niveauEtudeAcces = niveauEtudeAcces;
	}

	public String getNomPromotion() {
		return this.nomPromotion;
	}

	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}

	public List<Promotion> getPromotions() {
		return this.promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Promotion addPromotion(Promotion promotion) {
		getPromotions().add(promotion);
		promotion.setSpecialiteFormation(this);

		return promotion;
	}

	public Promotion removePromotion(Promotion promotion) {
		getPromotions().remove(promotion);
		promotion.setSpecialiteFormation(null);

		return promotion;
	}

}