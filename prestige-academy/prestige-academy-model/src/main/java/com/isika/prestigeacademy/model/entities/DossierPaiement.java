package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the dossier_paiement database table.
 * 
 */
@Entity
@NamedQuery(name="DossierPaiement.findAll", query="SELECT d FROM DossierPaiement d")
public class DossierPaiement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dossierPaiementID;

	private String commentaire;
	
	
	//bi-directional many-to-one association to Stagiaire
	@OneToMany(mappedBy = "dossierPaiement")
	private List<Stagiaire> stagiaires;

	public DossierPaiement() {
	}

	public Long getDossierPaiementID() {
		return this.dossierPaiementID;
	}

	public void setDossierPaiementID(Long dossierPaiementID) {
		this.dossierPaiementID = dossierPaiementID;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public List<Stagiaire> getStagiaires() {
		return this.stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Stagiaire addStagiaire(Stagiaire stagiaire) {
		getStagiaires().add(stagiaire);
		stagiaire.setDossierPaiement(this);

		return stagiaire;
	}

	public Stagiaire removeStagiaire(Stagiaire stagiaire) {
		getStagiaires().remove(stagiaire);
		stagiaire.setDossierPaiement(null);

		return stagiaire;
	}


}