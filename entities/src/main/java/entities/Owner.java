package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
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

    public Owner(String name, Date birthDate) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.birthDate = birthDate;
    }

    public Owner(OwnerDto ownerDto) {
        this.name = ownerDto.getName();
        this.id = ownerDto.getId() == null ? UUID.randomUUID().toString() : ownerDto.getId();
        this.birthDate = ownerDto.getBirthDate();
    }
}
