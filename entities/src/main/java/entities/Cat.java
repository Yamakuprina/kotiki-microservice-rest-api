package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Cat(CatDto catDto) {
        this.name = catDto.getName();
        this.id = catDto.getId()==null? UUID.randomUUID().toString() : catDto.getId();
        this.birthDate = catDto.getBirthDate();
        this.breed = catDto.getBreed();
        this.color = catDto.getColor();
        this.ownerId = catDto.getOwnerId();
    }

    public void addFriend(Cat cat){
        friends.add(cat);
    }

    public void deleteFriend(Cat cat){
        friends.remove(cat);
    }
}
