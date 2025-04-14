package demo.spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_promotion")
    private Long idPromotion;

    @ManyToOne
    @JoinColumn(name = "ID_Vol", nullable = false)
    private Vol vol;

    @Column(name = "promotion")
    private Double promotion;

    @Column(name = "nbSiegepromotion")
    private Integer nbSiegePromotion;

    @ManyToOne
    @JoinColumn(name = "type_Siege", nullable = false)
    private TypeSiege typeSiege;

    // Getters and Setters

    public Long getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(Long idPromotion) {
        this.idPromotion = idPromotion;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Double getPromotion() {
        return promotion;
    }

    public void setPromotion(Double promotion) {
        this.promotion = promotion;
    }

    public Integer getNbSiegePromotion() {
        return nbSiegePromotion;
    }

    public void setNbSiegePromotion(Integer nbSiegePromotion) {
        this.nbSiegePromotion = nbSiegePromotion;
    }

    public TypeSiege getTypeSiege() {
        return typeSiege;
    }

    public void setTypeSiege(TypeSiege typeSiege) {
        this.typeSiege = typeSiege;
    }
}
