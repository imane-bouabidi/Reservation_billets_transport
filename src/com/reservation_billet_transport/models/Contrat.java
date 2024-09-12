package com.reservation_billet_transport.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.reservation_billet_transport.enums.StatutContrat;

public class Contrat {
    private UUID id;
    private Date dateDebut;
    private Date dateFin;
    private double tarifSpecial;
    private String conditionsAccord;
    private boolean renouvlable;
    private StatutContrat statutContrat;
    private Partenaire partenaire;
    private ArrayList<Billet> billets;
    private ArrayList<Promos> promos;


    public Contrat() {

    }
    
    public Contrat(UUID id,Partenaire partenaire, Date dateDebut, Date dateFin, double tarifSpecial, String conditionsAccord, boolean renouvlable, StatutContrat statutContrat) {
        this.id = id;
        this.partenaire = partenaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tarifSpecial = tarifSpecial;
        this.conditionsAccord = conditionsAccord;
        this.renouvlable = renouvlable;
        this.statutContrat = statutContrat;
    }

    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getTarifSpecial() {
        return tarifSpecial;
    }

    public void setTarifSpecial(double tarifSpecial) {
        this.tarifSpecial = tarifSpecial;
    }

    public String getConditionsAccord() {
        return conditionsAccord;
    }

    public void setConditionsAccord(String conditionsAccord) {
        this.conditionsAccord = conditionsAccord;
    }

    public boolean isRenouvlable() {
        return renouvlable;
    }

    public void setRenouvlable(boolean renouvlable) {
        this.renouvlable = renouvlable;
    }

    public StatutContrat getStatutContrat() {
        return statutContrat;
    }

    public void setStatutContrat(StatutContrat statutContrat) {
        this.statutContrat = statutContrat;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public ArrayList<Billet> getBillets() {
        return billets;
    }

    public void setBillets(ArrayList<Billet> billets) {
        this.billets = billets;
    }

    public ArrayList<Promos> getPromos() {
        return promos;
    }

    public void setPromos(ArrayList<Promos> promos) {
        this.promos = promos;
    }
}
