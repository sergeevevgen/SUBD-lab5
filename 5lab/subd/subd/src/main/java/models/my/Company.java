package models.my;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "company", schema = "public", catalog = "newDb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "company_id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "ceo", nullable = false)
    private String ceo;

    @Column(name = "date_of_creation", nullable = false)
    private Date date_of_creation;

    public Company(String name, String address, String ceo, Date date_of_creation)
    {
        this.name = name;
        this.address = address;
        this.ceo = ceo;
        this.date_of_creation = date_of_creation;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || name: %s || address: %s || ceo: %s || date_of_creation %s",
                id, name, address, ceo, date_of_creation);
    }
}
