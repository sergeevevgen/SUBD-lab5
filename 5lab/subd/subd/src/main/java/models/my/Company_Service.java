package models.my;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_service_int", schema = "public", catalog = "newDb")
@NoArgsConstructor
@Getter
@Setter
public class Company_Service {

    @Id
    @Column(name = "companycompany_id")
    private long companyId;

    @Id
    @Column(name = "serviceservice_id")
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
