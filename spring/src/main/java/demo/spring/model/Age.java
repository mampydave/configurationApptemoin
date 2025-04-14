package demo.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "ages")
public class Age {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_age")
    private Long idage;
    private int min_age;
    private int max_age;
    private String beneficitaire;
    private Double promo;
    
    public Long getIdage() {
        return idage;
    }
    public void setIdage(Long idage) {
        this.idage = idage;
    }
    public int getMin_age() {
        return min_age;
    }
    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }
    public int getMax_age() {
        return max_age;
    }
    public void setMax_age(int max_age) {
        this.max_age = max_age;
    }
    public String getBeneficitaire() {
        return beneficitaire;
    }
    public void setBeneficitaire(String beneficitaire) {
        this.beneficitaire = beneficitaire;
    }
    public Double getPromo() {
        return promo;
    }
    public void setPromo(Double promo) {
        this.promo = promo;
    }
    
}
