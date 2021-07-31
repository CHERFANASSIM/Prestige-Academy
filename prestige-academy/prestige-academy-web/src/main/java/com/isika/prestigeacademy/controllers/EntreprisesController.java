package com.isika.prestigeacademy.controllers;


import com.isika.prestigeacademy.model.entities.*;
import com.isika.prestigeacademy.repositories.EntreprisesRepository;
import com.isika.prestigeacademy.services.EmailRecuService;
import com.isika.prestigeacademy.services.EntreprisesService;
import com.isika.prestigeacademy.services.UtilisateurService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;



@Named
@SessionScoped
public class EntreprisesController  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(EntreprisesRepository.class.getSimpleName());

    @Inject
    private EntreprisesService entreprisesService;
    
    @Inject
	private EmailRecuService emailRecuService;
    
    @Inject
	private UtilisateurService utilisateurService;

    // Création
    private final Entreprise entrepriseCree = new Entreprise();

    // Modification
    private Entreprise entrepriseModifie;

    // Listes
    private List<TypeProspect> listTypeProspect;
    private List<PreferenceTypeContrat> listPreferenceTypeContratS;
    private List<Statut> statutList;
    private List<NiveauAcces> niveauAccesList;
    private List<Entreprise> listeEntreprises;
    private List<EmailRecu> listeMails;
    private List<ProcessusRecrutement> listeProcessRecru;

    private String selectedNiveauAcces;
    private String selectedPrefTypeContrat;
    private String selectedStatut;
    private String selectedTypeProspect;

    private Utilisateur utilisateurConnecte;


    @PostConstruct
    private void chargerListeEntreprises() {
        listeEntreprises = entreprisesService.rechercheTousEntreprises();
        listTypeProspect = entreprisesService.rechercheTousTypeProspect();
        listPreferenceTypeContratS = entreprisesService.rechercheTousPrefeTypeContrat();
        statutList = entreprisesService.rechercheTousStatut();
        niveauAccesList = entreprisesService.rechercheTousNiveauAccess();
    }

    public void chargerEntreprise(Entreprise selected) {
    	
        entrepriseModifie = selected;
        listeMails = emailRecuService.rechercheLesMailsParDestinataire(entrepriseModifie.getEntrepriseID());
        listeProcessRecru = entreprisesService.rechercheLesRecrutements(entrepriseModifie.getEntrepriseID());
        
    }

    public void eventAjoutEntrepriseDetecter(@Observes(notifyObserver = Reception.IF_EXISTS) final Entreprise entreprise) {
        chargerListeEntreprises();
    }

    public void createEntreprise() {
        NiveauAcces niveauAcces = niveauAccesList.stream()
                .filter(value -> selectedNiveauAcces.equals(value.getNomNiveauAcces()))
                .findFirst()
                .get();

        PreferenceTypeContrat preferenceTypeContrat = listPreferenceTypeContratS.stream()
                .filter(value -> selectedPrefTypeContrat.equals(value.getNomTypeContrat()))
                .findFirst()
                .get();

        Statut statut = statutList.stream()
                .filter(value -> selectedStatut.equals(value.getNomStatut()))
                .findFirst()
                .get();

        TypeProspect typeProspect = listTypeProspect.stream()
                .filter(value -> selectedStatut.equals(value.getNomProspect()))
                .findFirst()
                .get();

        Long entrepriseId = entreprisesService.creerEntreprise(entrepriseCree, niveauAcces, preferenceTypeContrat, statut, typeProspect);

        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Entreprise enregistrée",
                "Id [" + entrepriseId + "]");
        FacesContext.getCurrentInstance().addMessage(null, m);

    }


    public void upadteEntreprise() {
        NiveauAcces niveauAcces = niveauAccesList.stream()
                .filter(value -> selectedNiveauAcces.equals(value.getNomNiveauAcces()))
                .findFirst()
                .get();

        PreferenceTypeContrat preferenceTypeContrat = listPreferenceTypeContratS.stream()
                .filter(value -> selectedPrefTypeContrat.equals(value.getNomTypeContrat()))
                .findFirst()
                .get();

        Statut statut = statutList.stream()
                .filter(value -> selectedStatut.equals(value.getNomStatut()))
                .findFirst()
                .get();

        TypeProspect typeProspect = listTypeProspect.stream()
                .filter(value -> selectedTypeProspect.equals(value.getNomProspect()))
                .findFirst()
                .get();

        entrepriseModifie.setTypeProspect(typeProspect);
        entrepriseModifie.setStatut(statut);
        entrepriseModifie.setNiveauAcces(niveauAcces);
        entrepriseModifie.setPreferenceTypeContrat(preferenceTypeContrat);

        Entreprise entrepriseUpdate = entreprisesService.modifierEntreprise(entrepriseModifie);

        if (entrepriseUpdate != null) {
            showMessageAndReloadData("Entreprise modifiée");
        }

    }


    public void afficherFicheEntreprise() {
    }


    public List<Entreprise> getListeEntreprises() {
        return listeEntreprises;
    }

    public void setListeEntreprises(List<Entreprise> listeEntreprises) {
        this.listeEntreprises = listeEntreprises;
    }



    public List<TypeProspect> getListTypeProspect() {
        return listTypeProspect;
    }

    public void setListTypeProspect(List<TypeProspect> listTypeProspect) {
        this.listTypeProspect = listTypeProspect;
    }

    public List<PreferenceTypeContrat> getListPreferenceTypeContratS() {
        return listPreferenceTypeContratS;
    }

    public void setListPreferenceTypeContratS(List<PreferenceTypeContrat> listPreferenceTypeContratS) {
        this.listPreferenceTypeContratS = listPreferenceTypeContratS;
    }

    public List<Statut> getStatutList() {
        return statutList;
    }

    public void setStatutList(List<Statut> statutList) {
        this.statutList = statutList;
    }

    public List<NiveauAcces> getNiveauAccesList() {
        return niveauAccesList;
    }

    public void setNiveauAccesList(List<NiveauAcces> niveauAccesList) {
        this.niveauAccesList = niveauAccesList;
    }

    public Entreprise getEntrepriseCree() {
        return entrepriseCree;
    }

    public Entreprise getEntrepriseModifie() {
        return entrepriseModifie;
    }

    public void setEntrepriseModifie(Entreprise entrepriseModifie) {
        this.entrepriseModifie = entrepriseModifie;
    }

    public String getSelectedNiveauAcces() {
        return selectedNiveauAcces;
    }

    public void setSelectedNiveauAcces(String selectedNiveauAcces) {
        this.selectedNiveauAcces = selectedNiveauAcces;
    }

    public String getSelectedPrefTypeContrat() {
        return selectedPrefTypeContrat;
    }

    public void setSelectedPrefTypeContrat(String selectedPrefTypeContrat) {
        this.selectedPrefTypeContrat = selectedPrefTypeContrat;
    }

    public String getSelectedStatut() {
        return selectedStatut;
    }

    public void setSelectedStatut(String selectedStatut) {
        this.selectedStatut = selectedStatut;
    }

    public String getSelectedTypeProspect() {
        return selectedTypeProspect;
    }

    public void setSelectedTypeProspect(String selectedTypeProspect) {
        this.selectedTypeProspect = selectedTypeProspect;
    }

    public void supprimerEntreprise(Entreprise selectedEntreprise) {
        boolean delete = entreprisesService.supprimerEntreprise(selectedEntreprise.getEntrepriseID());
        if (delete) {
            showMessageAndReloadData("Entreprise suprimée");
        }
    }

    private void showMessageAndReloadData(String message) {
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
        FacesContext.getCurrentInstance().addMessage(null, m);
        chargerListeEntreprises();
    }

	public static Logger getLogger() {
		return LOGGER;
	}

	public List<EmailRecu> getListeMails() {
		return listeMails;
	}

	public void setListeMails(List<EmailRecu> listeMails) {
		this.listeMails = listeMails;
	}

	public List<ProcessusRecrutement> getListeProcessRecru() {
		return listeProcessRecru;
	}

	public void setListeProcessRecru(List<ProcessusRecrutement> listeProcessRecru) {
		this.listeProcessRecru = listeProcessRecru;
	}

	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}

	
}
