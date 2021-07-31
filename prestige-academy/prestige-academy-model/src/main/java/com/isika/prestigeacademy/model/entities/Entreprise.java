package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({ @NamedQuery(name="Entreprise.findAll", query="SELECT e FROM Entreprise e"),
	@NamedQuery(name ="Entreprise.findProspect", query ="SELECT e FROM Entreprise e WHERE e.statut.statutID= :statutIDParam")})
public class Entreprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long entrepriseID;

	private String criteresEntreprise;

	@Temporal(TemporalType.DATE)
	private Date dateDebutPartenariat;

	private String descriptionEntreprise;

	private String interlocuteurs;

	private String logoUrl;

	private String mailEntreprise;

	private String nomEntreprise;

	private int tauxParticipationEvenements;

	private int tauxSatisfaction;

	private String telephoneEntreprise;

	private String adresse;

	private String codePostale;

	private String ville;

	private String pays;

	private String password;

	//bi-directional many-to-one association to NiveauAcces
	@ManyToOne
	@JoinColumn(name = "niveauAccesID")
	private NiveauAcces niveauAcces;

	//bi-directional many-to-one association to PreferenceTypeContrat
	@ManyToOne
	@JoinColumn(name = "preferenceTypeContratID")
	private PreferenceTypeContrat preferenceTypeContrat;

	//bi-directional many-to-one association to Statut
	@ManyToOne
	@JoinColumn(name="statutID")
	private Statut statut;

	//bi-directional many-to-one association to TypeProspect
	@ManyToOne
	@JoinColumn(name = "typeProspectID")
	private TypeProspect typeProspect;

	//bi-directional many-to-one association to Entreprise
	@OneToMany(mappedBy = "emailRecuID")
	private List<EmailRecu> emailsRecus;

	//bi-directional many-to-one association to GererProspect
	@OneToMany(mappedBy = "prospectId")
	private List<GererProspect> etapesProspection;

	//bi-directional many-to-one association to OffreEmploi
	@OneToMany(mappedBy = "offreEmploiID")
	private List<OffreEmploi> LesOffresEmploi;

	//bi-directional many-to-one association to Entreprise
	@OneToMany(mappedBy = "enquetesSatisfactionID")
	private List<EnquetesSatisfaction> enquetesSatisfaction;

	//bi-directional many-to-one association to Entreprise
	@OneToMany(mappedBy = "processusRecrutementID")
	private List<ProcessusRecrutement> processusRecrutements;


	public Entreprise() {
	}

	public Long getEntrepriseID() {
		return this.entrepriseID;
	}

	public void setEntrepriseID(Long entrepriseID) {
		this.entrepriseID = entrepriseID;
	}

	public String getCriteresEntreprise() {
		return this.criteresEntreprise;
	}

	public void setCriteresEntreprise(String criteresEntreprise) {
		this.criteresEntreprise = criteresEntreprise;
	}

	public Date getDateDebutPartenariat() {
		return this.dateDebutPartenariat;
	}

	public void setDateDebutPartenariat(Date dateDebutPartenariat) {
		this.dateDebutPartenariat = dateDebutPartenariat;
	}

	public String getDescriptionEntreprise() {
		return this.descriptionEntreprise;
	}

	public void setDescriptionEntreprise(String descriptionEntreprise) {
		this.descriptionEntreprise = descriptionEntreprise;
	}

	public String getInterlocuteurs() {
		return this.interlocuteurs;
	}

	public void setInterlocuteurs(String interlocuteurs) {
		this.interlocuteurs = interlocuteurs;
	}

	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getMailEntreprise() {
		return this.mailEntreprise;
	}

	public void setMailEntreprise(String mailEntreprise) {
		this.mailEntreprise = mailEntreprise;
	}

	public String getNomEntreprise() {
		return this.nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public int getTauxParticipationEvenements() {
		return this.tauxParticipationEvenements;
	}

	public void setTauxParticipationEvenements(int tauxParticipationEvenements) {
		this.tauxParticipationEvenements = tauxParticipationEvenements;
	}

	public int getTauxSatisfaction() {
		return this.tauxSatisfaction;
	}

	public void setTauxSatisfaction(int tauxSatisfaction) {
		this.tauxSatisfaction = tauxSatisfaction;
	}

	public String getTelephoneEntreprise() {
		return telephoneEntreprise;
	}

	public void setTelephoneEntreprise(String telephoneEntreprise) {
		this.telephoneEntreprise = telephoneEntreprise;
	}

	public NiveauAcces getNiveauAcces() {
		return this.niveauAcces;
	}

	public void setNiveauAcces(NiveauAcces niveauAcces) {
		this.niveauAcces = niveauAcces;
	}

	public PreferenceTypeContrat getPreferenceTypeContrat() {
		return this.preferenceTypeContrat;
	}

	public void setPreferenceTypeContrat(PreferenceTypeContrat preferenceTypeContrat) {
		this.preferenceTypeContrat = preferenceTypeContrat;
	}

	public Statut getStatut() {
		return this.statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public TypeProspect getTypeProspect() {
		return this.typeProspect;
	}

	public void setTypeProspect(TypeProspect typeProspect) {
		this.typeProspect = typeProspect;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<EmailRecu> getEmailRecus() {
		return emailsRecus;
	}

	public List<GererProspect> getEtapesProspection() {
		return etapesProspection;
	}

	public void setEtapesProspection(List<GererProspect> etapesProspection) {
		this.etapesProspection = etapesProspection;
	}

	public List<EmailRecu> getEmailsRecus() {
		return emailsRecus;
	}

	public void setEmailsRecus(List<EmailRecu> emailsRecus) {
		this.emailsRecus = emailsRecus;
	}


	public EmailRecu addEmailsRecu(EmailRecu emailRecu) {
		getEmailRecus().add(emailRecu);
		emailRecu.setEntreprise(this);

		return emailRecu;
	}

	public EmailRecu removeEmailsRecu(EmailRecu emailRecu) {
		getEmailRecus().remove(emailRecu);
		emailRecu.setEntreprise(null);

		return emailRecu;
	}

	public List<OffreEmploi> getLesOffresEmploi() {
		return LesOffresEmploi;
	}

	public void setLesOffresEmploi(List<OffreEmploi> lesOffresEmploi) {
		LesOffresEmploi = lesOffresEmploi;
	}

	public OffreEmploi addOffreEmploi(OffreEmploi job) {
		getLesOffresEmploi().add(job);
		job.setEntreprise(this) ;

		return job;
	}

	public OffreEmploi removeOffreEmploi(OffreEmploi job) {
		getLesOffresEmploi().remove(job);
		job.setEntreprise(null) ;

		return job;
	}



	public List<EnquetesSatisfaction> getEnquetesSatisfaction() {
		return enquetesSatisfaction;
	}

	public void setEnquetesSatisfaction(List<EnquetesSatisfaction> enquetesSatisfaction) {
		this.enquetesSatisfaction = enquetesSatisfaction;
	}


	public EnquetesSatisfaction addEnquetesSatisfaction(EnquetesSatisfaction enquete) {
		getEnquetesSatisfaction().add(enquete);
		enquete.setEntreprise(this);

		return enquete;
	}

	public EnquetesSatisfaction removeEnquetesSatisfaction(EnquetesSatisfaction enquete) {
		getEnquetesSatisfaction().remove(enquete);
		enquete.setEntreprise(null);

		return enquete;
	}

	public List<ProcessusRecrutement> getProcessusRecrutements() {
		return processusRecrutements;
	}

	public void setProcessusRecrutements(List<ProcessusRecrutement> processusRecrutements) {
		this.processusRecrutements = processusRecrutements;
	}

	public ProcessusRecrutement addProcessusRecrutement(ProcessusRecrutement processusRecrutement) {
		getProcessusRecrutements().add(processusRecrutement);
		processusRecrutement.setEntreprise(this);

		return processusRecrutement;
	}

	public ProcessusRecrutement removeProcessusRecrutement(ProcessusRecrutement processusRecrutement) {
		getProcessusRecrutements().remove(processusRecrutement);
		processusRecrutement.setEntreprise(null);

		return processusRecrutement;
	}


}