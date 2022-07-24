package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "owners")
public class Owner {
    @Column(name = "name")
    private String name;
    @Id
    private String id;
    @Column(name = "birth_date")
    private Date birthDate;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> cats;

    public Owner(String name, Date birthDate) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.birthDate = birthDate;
        this.cats = new ArrayList<Cat>();
    }

    public void addCat(Cat cat) {
        cats.add(cat);
    }
    public void removeCat(Cat cat) {
        cats.remove(cat);
    }
}
