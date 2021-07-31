package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the statut database table.
 * 
 */
@Entity
@NamedQuery(name="Statut.findAll", query="SELECT s FROM Statut s")
public class Statut implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long statutID;

	private String nomStatut;

	//bi-directional many-to-one association to Entreprise
	@OneToMany(mappedBy = "statut")
	private List<Entreprise> entreprises;

	//bi-directional many-to-one association to OrganismeFinancement
	@OneToMany(mappedBy = "statut")
	private List<OrganismeFinancement> organismeFinancements;

	public Statut() {
	}

	public Long getStatutID() {
		return this.statutID;
	}

	public void setStatutID(Long statutID) {
		this.statutID = statutID;
	}

	public String getNomStatut() {
		return this.nomStatut;
	}

	public void setNomStatut(String nomStatut) {
		this.nomStatut = nomStatut;
	}

	public List<Entreprise> getEntreprises() {
		return this.entreprises;
	}

	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public Entreprise addEntrepris(Entreprise entrepris) {
		getEntreprises().add(entrepris);
		entrepris.setStatut(this);

		return entrepris;
	}

	public Entreprise removeEntrepris(Entreprise entrepris) {
		getEntreprises().remove(entrepris);
		entrepris.setStatut(null);

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
		organismeFinancement.setStatut(this);

		return organismeFinancement;
	}

	public OrganismeFinancement removeOrganismeFinancement(OrganismeFinancement organismeFinancement) {
		getOrganismeFinancements().remove(organismeFinancement);
		organismeFinancement.setStatut(null);

		return organismeFinancement;
	}

	public Entreprise addEntreprise(Entreprise entreprise) {
		getEntreprises().add(entreprise);
		entreprise.setStatut(this);

		return entreprise;
	}

}