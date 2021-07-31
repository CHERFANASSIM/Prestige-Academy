package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the type_evenement database table.
 * 
 */
@Entity
@NamedQuery(name="TypeEvenement.findAll", query="SELECT t FROM TypeEvenement t")
public class TypeEvenement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeEvenementID;

	private String nomTypeEvenement;

	//bi-directional many-to-one association to Evenement

	@OneToMany(mappedBy="typeEvenement")
	private List<Evenement> evenements;

	public TypeEvenement() {
	}

	public Long getTypeEvenementID() {
		return this.typeEvenementID;
	}

	public void setTypeEvenementID(Long typeEvenementID) {
		this.typeEvenementID = typeEvenementID;
	}

	public String getNomTypeEvenement() {
		return this.nomTypeEvenement;
	}

	public void setNomTypeEvenement(String nomTypeEvenement) {
		this.nomTypeEvenement = nomTypeEvenement;
	}

	public List<Evenement> getEvenements() {
		return this.evenements;
	}

	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}

	public Evenement addEvenement(Evenement evenement) {
		getEvenements().add(evenement);
		evenement.setTypeEvenement(this);

		return evenement;
	}

	public Evenement removeEvenement(Evenement evenement) {
		getEvenements().remove(evenement);
		evenement.setTypeEvenement(this);

		return evenement;
	}

	public Evenement updateEvenement(Evenement evenement) {
		getEvenements().remove(evenement);
		getEvenements().add(evenement);
		evenement.setTypeEvenement(this);

		return evenement;
	}

}