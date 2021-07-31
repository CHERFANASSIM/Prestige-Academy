package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the preference_type_contrat database table.
 * 
 */
@Entity
@NamedQuery(name="PreferenceTypeContrat.findAll", query="SELECT p FROM PreferenceTypeContrat p")
public class PreferenceTypeContrat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long preferenceTypeContratID;

	private String nomTypeContrat;

	//bi-directional many-to-one association to Entreprise
	@OneToMany( mappedBy = "preferenceTypeContrat")
	private List<Entreprise> entreprises;
	

	public PreferenceTypeContrat() {
	}

	public Long getPreferenceTypeContratID() {
		return this.preferenceTypeContratID;
	}

	public void setPreferenceTypeContratID(Long preferenceTypeContratID) {
		this.preferenceTypeContratID = preferenceTypeContratID;
	}

	public String getNomTypeContrat() {
		return this.nomTypeContrat;
	}

	public void setNomTypeContrat(String nomTypeContrat) {
		this.nomTypeContrat = nomTypeContrat;
	}

	public List<Entreprise> getEntreprises() {
		return this.entreprises;
	}

	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public Entreprise addEntrepris(Entreprise entreprise) {
		getEntreprises().add(entreprise);
		entreprise.setPreferenceTypeContrat(this);

		return entreprise;
	}

	public Entreprise removeEntrepris(Entreprise entreprise) {
		getEntreprises().remove(entreprise);
		entreprise.setPreferenceTypeContrat(null);

		return entreprise;
	}

	public Entreprise addEntreprise(Entreprise entreprise) {
		getEntreprises().add(entreprise);
		entreprise.setPreferenceTypeContrat(this);
		return entreprise;
	}
	



}