package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CatDto implements Serializable {

    private String name;

    private Date birthDate;

    private String breed;

    private String id;

    private CatColor color;

    private String ownerId;

    public CatDto(Cat cat) {
        id = cat.getId();
        name = cat.getName();
        birthDate = cat.getBirthDate();
        breed = cat.getBreed();
        color = cat.getColor();
        ownerId = cat.getOwnerId();
    }
}
