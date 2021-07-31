package com.isika.prestigeacademy.model.entities;




import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class GererProspect implements Serializable {

    private static final long serialVersionUID = -8217373310887088836L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prospectId;

    private String commentaire;

    @Temporal(TemporalType.DATE)
    private Date dateDeContact;

    private String statut;

    @ManyToOne
    @JoinColumn(name = "entrepriseID")
    private Entreprise entreprise;

    public GererProspect() {
    }

    public Long getProspectId() {
        return prospectId;
    }

    public void setProspectId(Long prospectId) {
        this.prospectId = prospectId;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateDeContact() {
        return dateDeContact;
    }

    public void setDateDeContact(Date dateDeContact) {
        this.dateDeContact = dateDeContact;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Entreprise addEntrepriseGProspect(Entreprise entreprise) {
        entreprise.getEtapesProspection().add(this);
        this.entreprise = entreprise;
        return entreprise;
    }
}
