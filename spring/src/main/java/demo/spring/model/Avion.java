package demo.spring.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "avion")
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Avion")
    private Long idAvion;

    @Column(name = "Modele", length = 50)
    private String modele;

    @Column(name = "Date_Fabrication")
    private LocalDateTime dateFabrication;

    @OneToMany(mappedBy = "avion")
    private List<Siege> sieges;

    @OneToMany(mappedBy = "avion")
    private List<Vol> vols;


    public Long getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Long idAvion) {
        this.idAvion = idAvion;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public LocalDateTime getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(LocalDateTime dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public List<Siege> getSieges() {
        return sieges;
    }

    public void setSieges(List<Siege> sieges) {
        this.sieges = sieges;
    }

    public List<Vol> getVols() {
        return vols;
    }

    public void setVols(List<Vol> vols) {
        this.vols = vols;
    }
}
