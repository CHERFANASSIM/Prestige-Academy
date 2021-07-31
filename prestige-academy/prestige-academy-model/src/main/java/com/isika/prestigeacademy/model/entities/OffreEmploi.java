package com.isika.prestigeacademy.model.entities;


import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name="OffreEmploi.findAll", query= "SElECT o FROM OffreEmploi o")
public class OffreEmploi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long offreEmploiID;
	
	
	private String nomOffreEmploi;
	private String descriptionPoste ;
	private double salaires ;
	
	
	@ManyToOne
	@JoinColumn(name= "entrepriseID")
	private Entreprise entreprise ;
	
	private String preferenceTypeContrat;
	
	public Long getOffreEmploiID() {
		return offreEmploiID;
	}

	public void setOffreEmploiID(Long offreEmploiID) {
		this.offreEmploiID = offreEmploiID;
	}

	public String getNomOffreEmploi() {
		return nomOffreEmploi;
	}

	public void setNomOffreEmploi(String nomOffreEmploi) {
		this.nomOffreEmploi = nomOffreEmploi;
	}

	public String getDescriptionPoste() {
		return descriptionPoste;
	}

	public void setDescriptionPoste(String descriptionPoste) {
		this.descriptionPoste = descriptionPoste;
	}

	public double getSalaires() {
		return salaires;
	}

	public void setSalaires(double salaires) {
		this.salaires = salaires;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public String getPreferenceTypeContrat() {
		return preferenceTypeContrat;
	}

	public void setPreferenceTypeContrat(String preferenceTypeContrat) {
		this.preferenceTypeContrat = preferenceTypeContrat;
	}

	


	
}

