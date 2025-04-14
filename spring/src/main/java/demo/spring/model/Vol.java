package demo.spring.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "vol")
public class Vol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Vol")
    private Long idVol;

    @Column(name = "Numero_Vol", nullable = false, length = 20)
    private String numeroVol;

    @ManyToOne
    @JoinColumn(name = "ID_Avion", nullable = false)
    private Avion avion;

    @Column(name = "Date_Heure_Depart")
    private LocalDateTime dateHeureDepart;

    @Column(name = "Date_Heure_Arrivee")
    private LocalDateTime dateHeureArrivee;

    @ManyToOne
    @JoinColumn(name = "Aeroport_Depart", nullable = false)
    private Ville aeroportDepart;

    @ManyToOne
    @JoinColumn(name = "Aeroport_Arrivee", nullable = false)
    private Ville aeroportArrivee;

    @Column(name = "PrixEco", precision = 18, scale = 2)
    private BigDecimal prixEco;

    @Column(name = "PrixBusi", precision = 18, scale = 2)
    private BigDecimal prixBusi;

    @Column(name = "parametrageReservation")
    private LocalTime parametrageReservation;

    @Column(name = "annulationReservation")
    private LocalTime annulationReservation;

    @OneToMany(mappedBy = "vol")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "vol")
    private List<Promotion> promotions;

    public Long getIdVol() {
        return idVol;
    }

    public void setIdVol(Long idVol) {
        this.idVol = idVol;
    }

    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public LocalDateTime getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(LocalDateTime dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public LocalDateTime getDateHeureArrivee() {
        return dateHeureArrivee;
    }

    public void setDateHeureArrivee(LocalDateTime dateHeureArrivee) {
        this.dateHeureArrivee = dateHeureArrivee;
    }

    public Ville getAeroportDepart() {
        return aeroportDepart;
    }

    public void setAeroportDepart(Ville aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }

    public Ville getAeroportArrivee() {
        return aeroportArrivee;
    }

    public void setAeroportArrivee(Ville aeroportArrivee) {
        this.aeroportArrivee = aeroportArrivee;
    }

    public BigDecimal getPrixEco() {
        return prixEco;
    }

    public void setPrixEco(BigDecimal prixEco) {
        this.prixEco = prixEco;
    }

    public BigDecimal getPrixBusi() {
        return prixBusi;
    }

    public void setPrixBusi(BigDecimal prixBusi) {
        this.prixBusi = prixBusi;
    }

    public LocalTime getParametrageReservation() {
        return parametrageReservation;
    }

    public void setParametrageReservation(LocalTime parametrageReservation) {
        this.parametrageReservation = parametrageReservation;
    }

    public LocalTime getAnnulationReservation() {
        return annulationReservation;
    }

    public void setAnnulationReservation(LocalTime annulationReservation) {
        this.annulationReservation = annulationReservation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}