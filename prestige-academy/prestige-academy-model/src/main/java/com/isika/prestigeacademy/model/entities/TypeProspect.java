package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the type_prospect database table.
 * 
 */
@Entity
@NamedQuery(name="TypeProspect.findAll", query="SELECT t FROM TypeProspect t")
public class TypeProspect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long typeProspectID;

	private String nomProspect;

	//bi-directional many-to-one association to Entreprise
	@OneToMany(mappedBy = "typeProspect")
	private List<Entreprise> entreprises;

	//bi-directional many-to-one association to OrganismeFinancement
	@OneToMany(mappedBy = "typeProspect")
	private List<OrganismeFinancement> organismeFinancements;

	public TypeProspect() {
	}

	public Long getTypeProspectID() {
		return this.typeProspectID;
	}

	public void setTypeProspectID(Long typeProspectID) {
		this.typeProspectID = typeProspectID;
	}

	public String getNomProspect() {
		return this.nomProspect;
	}

	public void setNomProspect(String nomProspect) {
		this.nomProspect = nomProspect;
	}

	public List<Entreprise> getEntreprises() {
		return this.entreprises;
	}

	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public Entreprise addEntrepris(Entreprise entrepris) {
		getEntreprises().add(entrepris);
		entrepris.setTypeProspect(this);

		return entrepris;
	}

	public Entreprise removeEntrepris(Entreprise entrepris) {
		getEntreprises().remove(entrepris);
		entrepris.setTypeProspect(null);

		return entrepris;
	}

	public List<OrganismeFinancement> getOrganismeFinancements() {
		return this.organismeFinancements;
	}

	public void setOrganismeFinancements(List<OrganismeFinancement> organismeFinancements) {
		this.organismeFinancements = organismeFinancements;
	}

	public OrganismeFinancement addOrganismeFinancement(OrganismeFinancement organismeFinancement) {
		getOrganismeFinancements().add(organismeFinancement);
		organismeFinancement.setTypeProspect(this);

		return organismeFinancement;
	}

	public OrganismeFinancement removeOrganismeFinancement(OrganismeFinancement organismeFinancement) {
		getOrganismeFinancements().remove(organismeFinancement);
		organismeFinancement.setTypeProspect(null);

		return organismeFinancement;
	}

	public Entreprise addEntreprise(Entreprise entreprise) {
		getEntreprises().add(entreprise);
		entreprise.setTypeProspect(this);

		return entreprise;
	}

}