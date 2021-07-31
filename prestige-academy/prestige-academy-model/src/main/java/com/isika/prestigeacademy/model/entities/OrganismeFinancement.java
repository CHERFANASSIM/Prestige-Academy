package com.isika.prestigeacademy.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the organisme_financement database table.
 * 
 */
@Entity
@NamedQuery(name="OrganismeFinancement.findAll", query="SELECT o FROM OrganismeFinancement o")
public class OrganismeFinancement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long organismeFinancementID;

	private String conditionsAcces;

	@Temporal(TemporalType.DATE)
	private Date dateDebutPartenariatOrganisme;

	private String descriptionOrganismeFinancement;

	private String interlocuteursOrganismeFinancement;

	private String logoUrlOrga;

	private String mailOrganismeFinancement;

	private String nomOrganismeFinancement;

	private String telephoneOrganismeFinancement;

	private String Adresse;

	private String ville;

	private String codePostale;

	private String pays;


	//bi-directional many-to-one association to Statut
	@ManyToOne
	@JoinColumn(name = "statutID")
	private Statut statut;

	//bi-directional many-to-one association to TypeFinancement
	@ManyToOne
	@JoinColumn(name="typeFinancementID")
	private TypeFinancement typeFinancement;

	//bi-directional many-to-one association to TypeProspect
	@ManyToOne
	@JoinColumn(name = "typeProspectID")
	private TypeProspect typeProspect;

	public OrganismeFinancement() {
	}

	public Long getOrganismeFinancementID() {
		return this.organismeFinancementID;
	}

	public void setOrganismeFinancementID(Long organismeFinancementID) {
		this.organismeFinancementID = organismeFinancementID;
	}

	public String getConditionsAcces() {
		return this.conditionsAcces;
	}

	public void setConditionsAcces(String conditionsAcces) {
		this.conditionsAcces = conditionsAcces;
	}

	public Date getDateDebutPartenariatOrganisme() {
		return this.dateDebutPartenariatOrganisme;
	}

	public void setDateDebutPartenariatOrganisme(Date dateDebutPartenariatOrganisme) {
		this.dateDebutPartenariatOrganisme = dateDebutPartenariatOrganisme;
	}

	public String getDescriptionOrganismeFinancement() {
		return this.descriptionOrganismeFinancement;
	}

	public void setDescriptionOrganismeFinancement(String descriptionOrganismeFinancement) {
		this.descriptionOrganismeFinancement = descriptionOrganismeFinancement;
	}

	public String getInterlocuteursOrganismeFinancement() {
		return this.interlocuteursOrganismeFinancement;
	}

	public void setInterlocuteursOrganismeFinancement(String interlocuteursOrganismeFinancement) {
		this.interlocuteursOrganismeFinancement = interlocuteursOrganismeFinancement;
	}

	public String getLogoUrlOrga() {
		return this.logoUrlOrga;
	}

	public void setLogoUrlOrga(String logoUrlOrga) {
		this.logoUrlOrga = logoUrlOrga;
	}

	public String getMailOrganismeFinancement() {
		return this.mailOrganismeFinancement;
	}

	public void setMailOrganismeFinancement(String mailOrganismeFinancement) {
		this.mailOrganismeFinancement = mailOrganismeFinancement;
	}

	public String getNomOrganismeFinancement() {
		return this.nomOrganismeFinancement;
	}

	public void setNomOrganismeFinancement(String nomOrganismeFinancement) {
		this.nomOrganismeFinancement = nomOrganismeFinancement;
	}

	public String getTelephoneOrganismeFinancement() {
		return telephoneOrganismeFinancement;
	}

	public void setTelephoneOrganismeFinancement(String telephoneOrganismeFinancement) {
		this.telephoneOrganismeFinancement = telephoneOrganismeFinancement;
	}

	public Statut getStatut() {
		return this.statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public TypeFinancement getTypeFinancement() {
		return this.typeFinancement;
	}

	public void setTypeFinancement(TypeFinancement typeFinancement) {
		this.typeFinancement = typeFinancement;
	}

	public TypeProspect getTypeProspect() {
		return this.typeProspect;
	}

	public void setTypeProspect(TypeProspect typeProspect) {
		this.typeProspect = typeProspect;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

}