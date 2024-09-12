package com.reservation_billet_transport.models;

import java.util.Date;
import java.util.UUID;

import com.reservation_billet_transport.enums.StatutOffre;
import com.reservation_billet_transport.enums.TypeReduction;

public class Promos {

    private UUID id;
    private String nomOffre;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private TypeReduction typeReduction;
    private double valeurReduction;
    private String conditions;
    private StatutOffre statutOffre;
    private Contrat contrat;

    public Promos(UUID id,Contrat contrat, String nomOffre, String description, Date dateDebut, Date dateFin,
                  TypeReduction typeReduction, double valeurReduction, String conditions, StatutOffre statutOffre) {
        this.id = id;
        this.contrat = contrat;
        this.nomOffre = nomOffre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeReduction = typeReduction;
        this.valeurReduction = valeurReduction;
        this.conditions = conditions;
        this.statutOffre = statutOffre;
    }
    
    public Promos() {
		}


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomOffre() {
        return nomOffre;
    }

    public void setNomOffre(String nomOffre) {
        this.nomOffre = nomOffre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public TypeReduction getTypeReduction() {
        return typeReduction;
    }

    public void setTypeReduction(TypeReduction typeReduction) {
        this.typeReduction = typeReduction;
    }

    public double getValeurReduction() {
        return valeurReduction;
    }

    public void setValeurReduction(double valeurReduction) {
        this.valeurReduction = valeurReduction;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public StatutOffre getStatutOffre() {
        return statutOffre;
    }

    public void setStatutOffre(StatutOffre statutOffre) {
        this.statutOffre = statutOffre;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}
