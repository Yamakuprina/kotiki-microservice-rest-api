package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto implements Serializable {
    private String name;

    private Date birthDate;

    //private List<Cat> cats;

    private String id;

//    public OwnerDto(String name, Date birthDate) {
//        this.name = name;
//        this.birthDate = birthDate;
//    }

    public OwnerDto(Owner owner) {
        name = owner.getName();
        birthDate = owner.getBirthDate();
        //cats = owner.getCats();
        id = owner.getId();
    }
}
