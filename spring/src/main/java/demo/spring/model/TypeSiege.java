package demo.spring.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_Siege")
public class TypeSiege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Type_Siege")
    private Long idTypeSiege;

    @Column(name = "types", nullable = false, length = 50)
    private String types;

    @OneToMany(mappedBy = "typeSiege")
    private List<Siege> sieges;

    // @OneToMany(mappedBy = "typeSiege")
    // private List<Promotion> promotions;



    public Long getIdTypeSiege() {
        return idTypeSiege;
    }

    public void setIdTypeSiege(Long idTypeSiege) {
        this.idTypeSiege = idTypeSiege;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public List<Siege> getSieges() {
        return sieges;
    }

    public void setSieges(List<Siege> sieges) {
        this.sieges = sieges;
    }

    // public List<Promotion> getPromotions() {
    //     return promotions;
    // }

    // public void setPromotions(List<Promotion> promotions) {
    //     this.promotions = promotions;
    // }
}
