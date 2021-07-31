package com.isika.prestigeacademy.controllers;


import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.GererProspect;
import com.isika.prestigeacademy.model.entities.Notifications;
import com.isika.prestigeacademy.model.entities.Statut;
import com.isika.prestigeacademy.model.entities.Utilisateur;
import com.isika.prestigeacademy.services.EntreprisesService;
import com.isika.prestigeacademy.services.ProspectGererService;
import com.isika.prestigeacademy.services.StatutService;
import com.isika.prestigeacademy.services.UtilisateurService;

import org.joda.time.DateTime;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;


@Named
@SessionScoped
public class ProspectGererController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ProspectGererController.class.getSimpleName());

    @Inject
    private ProspectGererService prospectGererService;
    @Inject
    private EntreprisesService entreprisesService;
    @Inject
    private NotificationsController notificationsController;
    @Inject
    private UtilisateurService utilisateurService;

    //Entreprise non partenaire
    Entreprise entrepriseProspect;

    //Création
    @Inject
    private StatutService statutService;

    //Modification
    private GererProspect gererProspectModifie;

    //Listes
    private List<GererProspect> gererProspectList;
    private List<Entreprise> entrepriseList;
    private List<Statut> statutList;

    //Buttons
    private boolean premierContact;
    private boolean enNegociation;
    private boolean validation;
    private boolean archive;
    private boolean rdv;
    private boolean partenaire;
    
    private int bar= 0;
    private int etape= -1;

    // Args Prospection
    private String commentaireFiche;
    
    //Notifications
    private Notifications notifications;
    private Utilisateur sender;
    private Long receiver;



    @PostConstruct
    private void chargerListeEtapesProspections() {
        // 1 statut partenaire
        entrepriseList = entreprisesService.rechercheParStatut(1L);
        gererProspectList = prospectGererService.chercherTous();
        statutList = statutService.RechercherTousStatut();
        //commentaireFiche= new String();
        
        
        

    }


    public void chergerEntreprise(Entreprise selected) {
    	//entrepriseProspect.setEtapesProspection(null);
    	
        entrepriseProspect = selected;
        etape= -1;

        //buttons
        if (entrepriseProspect.getStatut().getStatutID() == 2L) {
            premierContact = true;
            enNegociation = false;
            validation = false;
            archive = false;
            rdv = false;
            partenaire = false;
            setBar(0);
        } else if (entrepriseProspect.getStatut().getStatutID() == 3L) {
            premierContact = false;
            enNegociation = true;
            validation = false;
            archive = true;
            rdv = false;
            partenaire = false;
            setBar(20);
        } else if (entrepriseProspect.getStatut().getStatutID() == 4L) {
            premierContact = false;
            enNegociation = false;
            validation = true;
            archive = true;
            rdv = false;
            partenaire = false;
            setBar(40);
        } else if (entrepriseProspect.getStatut().getStatutID() == 5L) {
            premierContact = false;
            enNegociation = false;
            validation = false;
            archive = false;
            rdv = true;
            partenaire = false;
            setBar(60);
        
        } else if (entrepriseProspect.getStatut().getStatutID() == 6L) {
            premierContact = false;
            enNegociation = false;
            validation = false;
            archive = false;
            rdv = false;
            partenaire = true;
            setBar(80);
        }
    }

    public void gererProspectionPremierContact() {
        etape = 2;
        entrepriseProspect.setStatut(statutList.get(etape));
        GererProspect gererProspect = new GererProspect();
        gererProspect.setDateDeContact(DateTime.now().toDate());
        gererProspect.setCommentaire(commentaireFiche);
        gererProspect.setStatut(statutList.get(etape).getNomStatut());

        gererProspect.addEntrepriseGProspect(entrepriseProspect);

        prospectGererService.createStatus(gererProspect);

        entrepriseProspect = entreprisesService.modifierEntreprise(entrepriseProspect);

        premierContact = false;
        enNegociation = true;
        validation = false;
        archive = true;
        rdv = false;
        partenaire = false;
        setBar(20);
    }

    public void gererProspectionNegoTele() {
        etape = 3;
        entrepriseProspect.setStatut(statutList.get(etape));
        GererProspect gererProspect = new GererProspect();
        gererProspect.setDateDeContact(DateTime.now().toDate());
        gererProspect.setCommentaire(commentaireFiche);
        gererProspect.setStatut(statutList.get(etape).getNomStatut());

        gererProspect.addEntrepriseGProspect(entrepriseProspect);

        prospectGererService.createStatus(gererProspect);

        entrepriseProspect = entreprisesService.modifierEntreprise(entrepriseProspect);

        premierContact = false;
        enNegociation = false;
        validation = true;
        archive = true;
        rdv = false;
        partenaire = false;
        setBar(40);

    }

    public void gererProspectionConfirmationDirecteur() {
        etape = 4;
        entrepriseProspect.setStatut(statutList.get(etape));
        GererProspect gererProspect = new GererProspect();
        gererProspect.setDateDeContact(DateTime.now().toDate());
        gererProspect.setCommentaire(commentaireFiche);
        gererProspect.setStatut(statutList.get(etape).getNomStatut());

        gererProspect.addEntrepriseGProspect(entrepriseProspect);

        prospectGererService.createStatus(gererProspect);

        entrepriseProspect = entreprisesService.modifierEntreprise(entrepriseProspect);

        premierContact = false;
        enNegociation = false;
        validation = false;
        archive = false;
        rdv = true;
        partenaire = false;
        setBar(60);
        
        String message = "L'Entreprise "+entrepriseProspect.getNomEntreprise()+" est en attente de validation";
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Long idSender = (Long) httpSession.getAttribute("userID");
        sender = utilisateurService.findById(idSender);
        receiver=1L;
        notificationsController.ajouterUneNotification(notifications,message,idSender,receiver);
        
    }

    public void gererProspectionRDV() {
        etape = 5;
        entrepriseProspect.setStatut(statutList.get(etape));
        GererProspect gererProspect = new GererProspect();
        gererProspect.setDateDeContact(DateTime.now().toDate());
        gererProspect.setCommentaire(commentaireFiche);
        gererProspect.setStatut(statutList.get(etape).getNomStatut());

        gererProspect.addEntrepriseGProspect(entrepriseProspect);

        prospectGererService.createStatus(gererProspect);

        entrepriseProspect = entreprisesService.modifierEntreprise(entrepriseProspect);

        premierContact = false;
        enNegociation = false;
        validation = false;
        archive = false;
        rdv = false;
        partenaire = true;
        chergerEntreprise(entrepriseProspect);
        setBar(80);
        
        


    }

    public void gererProspectionEtapeArchive() {
        etape = 6;
        entrepriseProspect.setStatut(statutList.get(etape));
        GererProspect gererProspect = new GererProspect();
        gererProspect.setDateDeContact(DateTime.now().toDate());
        gererProspect.setCommentaire(commentaireFiche);
        gererProspect.setStatut(statutList.get(etape).getNomStatut());

        gererProspect.addEntrepriseGProspect(entrepriseProspect);

        prospectGererService.createStatus(gererProspect);

        entrepriseProspect = entreprisesService.modifierEntreprise(entrepriseProspect);

        premierContact = false;
        enNegociation = false;
        validation = false;
        archive = false;
        rdv = false;
        partenaire = false;
        chergerEntreprise(entrepriseProspect);

    }

    public void gererProspectionEtapePartenaire() {
        etape = 0;
        entrepriseProspect.setStatut(statutList.get(etape));
        GererProspect gererProspect = new GererProspect();
        gererProspect.setDateDeContact(DateTime.now().toDate());
        gererProspect.setCommentaire(commentaireFiche);
        gererProspect.setStatut(statutList.get(etape).getNomStatut());

        gererProspect.addEntrepriseGProspect(entrepriseProspect);

        prospectGererService.createStatus(gererProspect);

        entrepriseProspect = entreprisesService.modifierEntreprise(entrepriseProspect);

        premierContact = false;
        enNegociation = false;
        validation = false;
        archive = false;
        rdv = false;
        partenaire = false;
        chergerEntreprise(entrepriseProspect);
        setBar(100);

    }

    public List<GererProspect> getGererProspectList() {
        return gererProspectList;
    }

    public void setGererProspectList(List<GererProspect> gererProspectList) {
        this.gererProspectList = gererProspectList;
    }

    public boolean isPremierContact() {
        return premierContact;
    }

    public void setPremierContact(boolean premierContact) {
        this.premierContact = premierContact;
    }

    public boolean isEnNegociation() {
        return enNegociation;
    }

    public void setEnNegociation(boolean enNegociation) {
        this.enNegociation = enNegociation;
    }


    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public boolean isRdv() {
        return rdv;
    }

    public void setRdv(boolean rdv) {
        this.rdv = rdv;
    }

    public List<Entreprise> getEntrepriseList() {
        return entrepriseList;
    }

    public void setEntrepriseList(List<Entreprise> entrepriseList) {
        this.entrepriseList = entrepriseList;
    }

    public Entreprise getEntrepriseProspect() {
        return entrepriseProspect;
    }

    public void setEntrepriseProspect(Entreprise entrepriseProspect) {
        this.entrepriseProspect = entrepriseProspect;
    }

    public String getCommentaireFiche() {
        return commentaireFiche;
    }

    public void setCommentaireFiche(String commentaireFiche) {
        this.commentaireFiche = commentaireFiche;
    }

    public boolean isPartenaire() {
        return partenaire;
    }

    public void setPartenaire(boolean partenaire) {
        this.partenaire = partenaire;
    }


	public GererProspect getGererProspectModifie() {
		return gererProspectModifie;
	}


	public void setGererProspectModifie(GererProspect gererProspectModifie) {
		this.gererProspectModifie = gererProspectModifie;
	}


	public int getBar() {
		return bar;
	}


	public void setBar(int bar) {
		this.bar = bar;
	}


	public int getEtape() {
		return etape;
	}


	public void setEtape(int etape) {
		this.etape = etape;
	}
	
	
}
