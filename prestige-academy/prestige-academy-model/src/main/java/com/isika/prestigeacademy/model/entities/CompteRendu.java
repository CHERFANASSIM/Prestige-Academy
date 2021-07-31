package com.isika.prestigeacademy.model.entities;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


/**
 * The persistent class for the compte_rendu database table.
 * 
 */
@Entity
@NamedQuery(name="CompteRendu.findAll", query="SELECT c FROM CompteRendu c")
public class CompteRendu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long compteRenduID;

	@Lob
	private String contenuCompteRendu;


	private String nomCompteRendu;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compteRendu", orphanRemoval = true)
	private Evenement evenement;
	
	public CompteRendu() {
	}

	public Long getCompteRenduID() {
		return this.compteRenduID;
	}

	public void setCompteRenduID(Long compteRenduID) {
		this.compteRenduID = compteRenduID;
	}

	public String getContenuCompteRendu() {
		return this.contenuCompteRendu;
	}

	public void setContenuCompteRendu(String contenuCompteRendu) {
		this.contenuCompteRendu = contenuCompteRendu;
	}


	public String getNomCompteRendu() {
		return this.nomCompteRendu;
	}

	public void setNomCompteRendu(String nomCompteRendu) {
		this.nomCompteRendu = nomCompteRendu;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}


}