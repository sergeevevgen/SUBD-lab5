package models.my;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "material", schema = "public", catalog = "newDb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Material {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "material_id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "unit_measurement", nullable = false)
    private String unit_measurement;

    public Material(String name, float price, String unit_measurement) {
        this.name = name;
        this.price = price;
        this.unit_measurement = unit_measurement;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || name: %s || price %f || unit_measurement %s", id, name, price, unit_measurement);
    }
}
