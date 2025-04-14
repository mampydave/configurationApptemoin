package demo.spring.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Reservation")
    private Long idReservation;

    @ManyToOne
    @JoinColumn(name = "ID_Passager", nullable = false)
    private Users passager;

    @ManyToOne
    @JoinColumn(name = "ID_Vol", nullable = false)
    private Vol vol;

    @ManyToOne
    @JoinColumn(name = "ID_Siege", nullable = false)
    private Siege siege;

    @Column(name = "Date_Reservation")
    private LocalDateTime dateReservation;

    @Column(name = "Statut_Reservation", length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'Reserve'")
    private String statutReservation;

    @Column(name = "prixBillet", precision = 18, scale = 2)
    private BigDecimal prixBillet;

    @Column(name = "beneficiaire", length = 30)
    private String beneficiaire;

    // Getters and Setters

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Users getPassager() {
        return passager;
    }

    public void setPassager(Users passager) {
        this.passager = passager;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Siege getSiege() {
        return siege;
    }

    public void setSiege(Siege siege) {
        this.siege = siege;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
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

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }
}