package models.my;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_material_int", schema = "public", catalog = "newDb")
@NoArgsConstructor
@Getter
@Setter
public class Service_Material {
    @Id
    @Column(name = "serviceservice_id")
    private long serviceId;

    @Id
    @Column(name = "materialmaterial_id")
    private long materialId;

    @Column(name = "count", nullable = false)
    private float count;

    public Service_Material(long serviceId, long materialId, float count) {
        this.serviceId = serviceId;
        this.materialId = materialId;
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("\nService: %d || Material: %d || Count: %f", serviceId, materialId, count);
    }
}
