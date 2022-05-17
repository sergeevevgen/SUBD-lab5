package models.my;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "company_service_int", schema = "public", catalog = "newDb")
@NoArgsConstructor
@Getter
@Setter
public class Company_Service {

    @Id
    @Column(name = "company_id_company")
    private long companyId;

    @Basic
    @Column(name = "service_id_service")
    private long serviceId;

    @Column(name = "price_for_unit_measurement", nullable = false)
    private float price_for_unit_measurement;

    public Company_Service(long companyId, long serviceId, float price_for_unit_measurement) {
        this.companyId = companyId;
        this.serviceId = serviceId;
        this.price_for_unit_measurement = price_for_unit_measurement;
    }

    @Override
    public String toString() {
        return String.format("\nCompany: %d || Service: %d || Price_for_unit_measurement: %f", companyId, serviceId, price_for_unit_measurement);
    }
}
