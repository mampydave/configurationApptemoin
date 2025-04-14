package demo.spring.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "villes")
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Ville")
    private Long idVille;

    @Column(name = "nom_ville", nullable = false, length = 50)
    private String nomVille;

    @OneToMany(mappedBy = "aeroportDepart")
    private List<Vol> volsDepart;

    @OneToMany(mappedBy = "aeroportArrivee")
    private List<Vol> volsArrivee;


    public Long getIdVille() {
        return idVille;
    }

    public void setIdVille(Long idVille) {
        this.idVille = idVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public List<Vol> getVolsDepart() {
        return volsDepart;
    }

    public void setVolsDepart(List<Vol> volsDepart) {
        this.volsDepart = volsDepart;
    }

    public List<Vol> getVolsArrivee() {
        return volsArrivee;
    }

    public void setVolsArrivee(List<Vol> volsArrivee) {
        this.volsArrivee = volsArrivee;
    }
}
