package models.my;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service", schema = "public", catalog = "newDb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "service_id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Service(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || name: %s", id, name);
    }
}
