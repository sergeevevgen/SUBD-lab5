package models.my;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contract", schema = "public", catalog = "newDb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contract {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "contract_id")
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "object_id")
    private Zdanie zdanie;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "date_of_conclusion", nullable = false)
    private Date date_of_conclusion;

    @Column(name = "date_of_end", nullable = false)
    private Date date_of_end;

    @Column(name = "volume", nullable = false)
    private float volume;

    @Column(name = "final_price", nullable = false)
    private float final_price;

    public Contract(Zdanie zdanie, Service service, Company company, Date date_of_conclusion, Date date_of_end, float volume, float final_price) {
        this.zdanie = zdanie;
        this.service = service;
        this.company = company;
        this.date_of_conclusion = date_of_conclusion;
        this.date_of_end = date_of_end;
        this.volume = volume;
        this.final_price = final_price;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || ObjectId: %d  || ServiceId: %d  || CompanyId: %d  || Date_of_conclusion: %s || Date_of_end: %s || Volume: %f || Final_price: %f",
                id, zdanie.getId(), service.getId(), company.getId(), date_of_conclusion, date_of_end, volume, final_price);
    }
}
