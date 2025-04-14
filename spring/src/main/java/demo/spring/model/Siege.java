package demo.spring.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "sieges")
public class Siege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Siege")
    private Long idSiege;

    @ManyToOne
    @JoinColumn(name = "ID_Avion", nullable = false)
    private Avion avion;

    @ManyToOne
    @JoinColumn(name = "ID_Type_Siege", nullable = false)
    private TypeSiege typeSiege;

    @Column(name = "Numero_Siege", nullable = false, length = 10)
    private String numeroSiege;

    @Column(name = "Statut", length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'Disponible'")
    private String statut;

    @OneToMany(mappedBy = "siege")
    private List<Reservation> reservations;



    public Long getIdSiege() {
        return idSiege;
    }

    public void setIdSiege(Long idSiege) {
        this.idSiege = idSiege;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public TypeSiege getTypeSiege() {
        return typeSiege;
    }

    public void setTypeSiege(TypeSiege typeSiege) {
        this.typeSiege = typeSiege;
    }

    public String getNumeroSiege() {
        return numeroSiege;
    }

    public void setNumeroSiege(String numeroSiege) {
        this.numeroSiege = numeroSiege;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}