package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the stagiaire database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name="Stagiaire.findAll", query="SELECT s FROM Stagiaire s"),
	@NamedQuery(name ="Stagiaire.findPromo", query ="SELECT s FROM Stagiaire s WHERE s.promotion.promotionID= :promoID")
})
public class Stagiaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stagiaireID;

	private String assiduiteStagiaire;

	private String cvUrl;

	@Temporal(TemporalType.DATE)
	private Date dateAnniversaireStagiaire;

	private String diplome;

	private String languesParlees;

	private String mailStagiaire;

	private String nomPrenom;

	private String nomStagiaire;

	private String photoUrl;

	private String prenomStagiaire;

	private String sexe;

	private String telephoneStagiaire;

	private String Adresse;

	private String ville;

	private String codePostale;

	private String password;

	//bi-directional many-to-one association to DossierPaiement
	@ManyToOne
	@JoinColumn(name = "dossierPaiementID")
	private DossierPaiement dossierPaiement;

	//bi-directional many-to-one association to NiveauAcces
	@ManyToOne
	@JoinColumn(name = "niveauAccesID")
	private NiveauAcces niveauAcces;

	//bi-directional many-to-one association to Promotion
	@ManyToOne
	@JoinColumn(name = "promotionID")
	private Promotion promotion;

	//bi-directional many-to-one association to StatutFinancement
	@ManyToOne
	@JoinColumn(name="statutFinancementID")
	private StatutFinancement statutFinancement;

	//bi-directional many-to-one association to StatutRecrutement
	@ManyToOne
	@JoinColumn(name = "statutRecrutementID")
	private StatutRecrutement statutRecrutement;

	//bi-directional many-to-one association to Entreprise
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "emailRecuID")
	private List<EmailRecu> emailsRecus;

	//bi-directional many-to-one association to Entreprise
	@OneToMany(mappedBy = "processusRecrutementID")
	private List<ProcessusRecrutement> processusRecrutements;

	public Stagiaire() {
	}

	public Long getStagiaireID() {
		return this.stagiaireID;
	}

	public void setStagiaireID(Long stagiaireID) {
		this.stagiaireID = stagiaireID;
	}

	public String getAssiduiteStagiaire() {
		return this.assiduiteStagiaire;
	}

	public void setAssiduiteStagiaire(String assiduiteStagiaire) {
		this.assiduiteStagiaire = assiduiteStagiaire;
	}

	public String getCvUrl() {
		return this.cvUrl;
	}

	public void setCvUrl(String cvUrl) {
		this.cvUrl = cvUrl;
	}

	public Date getDateAnniversaireStagiaire() {
		return this.dateAnniversaireStagiaire;
	}

	public void setDateAnniversaireStagiaire(Date dateAnniversaireStagiaire) {
		this.dateAnniversaireStagiaire = dateAnniversaireStagiaire;
	}

	public String getDiplome() {
		return this.diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public String getLanguesParlees() {
		return this.languesParlees;
	}

	public void setLanguesParlees(String languesParlees) {
		this.languesParlees = languesParlees;
	}

	public String getMailStagiaire() {
		return this.mailStagiaire;
	}

	public void setMailStagiaire(String mailStagiaire) {
		this.mailStagiaire = mailStagiaire;
	}

	public String getNomPrenom() {
		return this.nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public String getNomStagiaire() {
		return this.nomStagiaire;
	}

	public void setNomStagiaire(String nomStagiaire) {
		this.nomStagiaire = nomStagiaire;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPrenomStagiaire() {
		return this.prenomStagiaire;
	}

	public void setPrenomStagiaire(String prenomStagiaire) {
		this.prenomStagiaire = prenomStagiaire;
	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getTelephoneStagiaire() {
		return telephoneStagiaire;
	}

	public void setTelephoneStagiaire(String telephoneStagiaire) {
		this.telephoneStagiaire = telephoneStagiaire;
	}

	public DossierPaiement getDossierPaiement() {
		return this.dossierPaiement;
	}

	public void setDossierPaiement(DossierPaiement dossierPaiement) {
		this.dossierPaiement = dossierPaiement;
	}

	public NiveauAcces getNiveauAcces() {
		return this.niveauAcces;
	}

	public void setNiveauAcces(NiveauAcces niveauAcces) {
		this.niveauAcces = niveauAcces;
	}

	public Promotion getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public StatutFinancement getStatutFinancement() {
		return this.statutFinancement;
	}

	public void setStatutFinancement(StatutFinancement statutFinancement) {
		this.statutFinancement = statutFinancement;
	}


	public StatutRecrutement getStatutRecrutement() {
		return this.statutRecrutement;
	}

	public void setStatutRecrutement(StatutRecrutement statutRecrutement) {
		this.statutRecrutement = statutRecrutement;
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


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	public List<EmailRecu> getEmailRecus() {
		return emailsRecus;
	}


	public void setEmailRecus(List<EmailRecu> emailsRecus) {
		this.emailsRecus = emailsRecus;
	}

	public EmailRecu addEmailRecu(EmailRecu emailRecu) {
		getEmailRecus().add(emailRecu);
		emailRecu.setStagiaire(this);

		return emailRecu;
	}

	public EmailRecu removeEmailRecu(EmailRecu emailRecu) {
		getEmailRecus().remove(emailRecu);
		emailRecu.setStagiaire(null);

		return emailRecu;
	}
	
	

	public List<EmailRecu> getEmailsRecus() {
		return emailsRecus;
	}

	public void setEmailsRecus(List<EmailRecu> emailsRecus) {
		this.emailsRecus = emailsRecus;
	}

	public List<ProcessusRecrutement> getProcessusRecrutements() {
		return processusRecrutements;
	}

	public void setProcessusRecrutements(List<ProcessusRecrutement> processusRecrutements) {
		this.processusRecrutements = processusRecrutements;
	}

	public ProcessusRecrutement addProcessusRecrutement(ProcessusRecrutement processusRecrutement) {
		getProcessusRecrutements().add(processusRecrutement);
		processusRecrutement.setStagiaire(this);

		return processusRecrutement;
	}

	public ProcessusRecrutement removeProcessusRecrutement(ProcessusRecrutement processusRecrutement) {
		getProcessusRecrutements().remove(processusRecrutement);
		processusRecrutement.setStagiaire(null);

		return processusRecrutement;
	}




}