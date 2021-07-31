package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the type_financement database table.
 * 
 */
@Entity
@NamedQuery(name="TypeFinancement.findAll", query="SELECT t FROM TypeFinancement t")
public class TypeFinancement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeFinancementID;

	private String nomTypeFinancement;
	

	//bi-directional many-to-one association to OrganismeFinancement
	@OneToMany(mappedBy = "typeFinancement")
	private List<OrganismeFinancement> organismeFinancements;

	public TypeFinancement() {
	}

	public Long getTypeFinancementID() {
		return this.typeFinancementID;
	}

	public void setTypeFinancementID(Long typeFinancementID) {
		this.typeFinancementID = typeFinancementID;
	}

	public String getNomTypeFinancement() {
		return this.nomTypeFinancement;
	}

	public void setNomTypeFinancement(String nomTypeFinancement) {
		this.nomTypeFinancement = nomTypeFinancement;
	}

	public List<OrganismeFinancement> getOrganismeFinancements() {
		return this.organismeFinancements;
	}

	public void setOrganismeFinancements(List<OrganismeFinancement> organismeFinancements) {
		this.organismeFinancements = organismeFinancements;
	}

	public OrganismeFinancement addOrganismeFinancement(OrganismeFinancement organismeFinancement) {
		getOrganismeFinancements().add(organismeFinancement);
		organismeFinancement.setTypeFinancement(this);

		return organismeFinancement;
	}

	public OrganismeFinancement removeOrganismeFinancement(OrganismeFinancement organismeFinancement) {
		getOrganismeFinancements().remove(organismeFinancement);
		organismeFinancement.setTypeFinancement(null);

		return organismeFinancement;
	}

	

}