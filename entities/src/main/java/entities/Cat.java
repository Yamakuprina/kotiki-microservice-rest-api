package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cats")
public class Cat {
    @Column(name = "name")
    private String name;
    @Id
    private String id;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "breed")
    private String breed;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private CatColor color;
//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    private Owner owner;
    @Column(name = "owner_id")
    private String ownerId;
    @OneToMany(mappedBy = "id")
    private List<Cat> friends;

    public Cat(String name, Date birthDate, String breed, CatColor color) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }

    public void addFriend(Cat cat){
        friends.add(cat);
    }

    public void deleteFriend(Cat cat){
        friends.remove(cat);
    }
}
