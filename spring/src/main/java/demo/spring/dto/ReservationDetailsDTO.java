package demo.spring.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


public class ReservationDetailsDTO {
    private Long idReservation;
    private Long idPassager;
    private Long idVol;
    private Date dateDepart;
    private Time heureDepart;
    private String numeroVol;
    private String villeDepart;
    private String villeArrivee;
    private String numeroSiege;
    private Timestamp dateReservation;
    private String statutReservation;
    private BigDecimal prixBillet;
    private String typeSiege;
    private String beneficiaire;


    public ReservationDetailsDTO(Long idReservation, Long idPassager, Long idVol,
                               Date dateDepart, Time heureDepart,
                               String numeroVol, String villeDepart, String villeArrivee,
                               String numeroSiege, Timestamp dateReservation,
                               String statutReservation, BigDecimal prixBillet, String typeSiege,String beneficiaire) {
        this.idReservation = idReservation;
        this.idPassager = idPassager;
        this.idVol = idVol;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.numeroVol = numeroVol;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.numeroSiege = numeroSiege;
        this.dateReservation = dateReservation;
        this.statutReservation = statutReservation;
        this.prixBillet = prixBillet;
        this.typeSiege = typeSiege;
        this.beneficiaire = beneficiaire;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Long getIdPassager() {
        return idPassager;
    }

    public void setIdPassager(Long idPassager) {
        this.idPassager = idPassager;
    }

    public Long getIdVol() {
        return idVol;
    }

    public void setIdVol(Long idVol) {
        this.idVol = idVol;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Time getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Time heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public String getNumeroSiege() {
        return numeroSiege;
    }

    public void setNumeroSiege(String numeroSiege) {
        this.numeroSiege = numeroSiege;
    }

    public Timestamp getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Timestamp dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(String statutReservation) {
        this.statutReservation = statutReservation;
    }

    public BigDecimal getPrixBillet() {
        return prixBillet;
    }

    public void setPrixBillet(BigDecimal prixBillet) {
        this.prixBillet = prixBillet;
    }

    public String getTypeSiege() {
        return typeSiege;
    }

    public void setTypeSiege(String typeSiege) {
        this.typeSiege = typeSiege;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }


}