package models.my;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "object", schema = "public", catalog = "newDb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Zdanie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "object_id")
    private long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "object_type")
    private String object_type;

    @Column(name = "build_date", nullable = false)
    private Date build_date;

    public Zdanie(String address, String object_type, Date build_date)
    {
        this.address = address;
        this.object_type = object_type;
        this.build_date = build_date;
    }

    @Override
    public String toString()  {
        return String.format("\nid: %d || address: %s || object_type: %s || build_date: %s", id, address, object_type, build_date);
    }
}
