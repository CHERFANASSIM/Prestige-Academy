package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.*;
import com.isika.prestigeacademy.repositories.dao.GenericDao;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Stateless
@Remote
public class EntreprisesRepository extends GenericDao<Entreprise> {

    private static final Logger LOGGER = Logger.getLogger(EntreprisesRepository.class.getSimpleName());

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Event<Entreprise> entrepriseEventSource;


    public Entreprise seConnecter(String mail, String mdp) {
        Entreprise entreprise = null;

        Query query = entityManager.createQuery("select e FROM Entreprise e WHERE e.mailEntreprise = :mailParam AND e.password = :passwordParam");
        query.setParameter("mailParam", mail);
        query.setParameter("passwordParam", mdp);

        List<Entreprise> results = query.getResultList();
        if (results.size() == 1) {
            Entreprise entrepriseE = results.get(0);
            entreprise = entrepriseE;
        }
        return entreprise;
    }

    public List<Entreprise> findeAll() {
        List<Entreprise> entrepriseS = new ArrayList<>();
        Entreprise entreprise = new Entreprise();
        Query query = entityManager.createNamedQuery("Entreprise.findAll", Entreprise.class);
        List<Entreprise> resultList = query.getResultList();

        return resultList;
    }

    public Entreprise findById(Long entrepriseID) {
        Entreprise entreprise = null;
        entreprise = this.entityManager.find(Entreprise.class, entrepriseID);
        return entreprise;
    }


    public boolean removeEntreprise(Entreprise entreprise) {

        this.entityManager.remove(entreprise);
        return findById(entreprise.getEntrepriseID()) != null;
    }


    public Long ajouter(Entreprise entreprise) {
        add(entreprise);
        entrepriseEventSource.fire(entreprise);
        LOGGER.info("Persisted entreprise : " + entreprise);

        return entreprise.getEntrepriseID();
    }

    public List<TypeProspect> findTypeProspectAll() {
        List<TypeProspect> typeProspectS = new ArrayList<>();
        TypeProspect typeProspect = new TypeProspect();
        Query query = entityManager.createNamedQuery("TypeProspect.findAll", TypeProspect.class);
        List<TypeProspect> resultList = query.getResultList();


        return resultList;
    }


    public List<PreferenceTypeContrat> findPrefTypeContratAll() {
        List<PreferenceTypeContrat> preferenceTypeContratS = new ArrayList<>();
        PreferenceTypeContrat preferenceTypeContrat = new PreferenceTypeContrat();
        Query query = entityManager.createNamedQuery("PreferenceTypeContrat.findAll", PreferenceTypeContrat.class);
        List<PreferenceTypeContrat> resultList = query.getResultList();

        return resultList;
    }

    public List<Statut> findStatutsAll() {
        List<Statut> statutS = new ArrayList<>();
        Statut statut = new Statut();
        Query query = entityManager.createNamedQuery("Statut.findAll", Statut.class);
        List<Statut> resultList = query.getResultList();

        return resultList;
    }

    public List<NiveauAcces> findNivAccessAll() {
        List<NiveauAcces> niveauAccesS = new ArrayList<>();
        NiveauAcces niveauAcces = new NiveauAcces();
        Query query = entityManager.createNamedQuery("NiveauAcce.findAll", NiveauAcces.class);
        List<NiveauAcces> resultList = query.getResultList();


        return resultList;
    }


    public List<Entreprise> findByStatus(long iDStatut) {
        List<Entreprise> entreprises = entityManager
                .createQuery("SELECT e FROM Entreprise e LEFT JOIN e.statut s WHERE s.statutID <> :param AND s.statutID IS NOT NULL ")
                .setParameter("param", iDStatut)
                .getResultList();
        return entreprises;
    }

	public List<ProcessusRecrutement> findListProcessus(Long entrepriseID) {
		TypedQuery<ProcessusRecrutement> query = this.entityManager.createNamedQuery("ProcessusRecrutement.findListRecru2", ProcessusRecrutement.class);
		query.setParameter("entrepriseID", entrepriseID);
		return query.getResultList();
	}
}
