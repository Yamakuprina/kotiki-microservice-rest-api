package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@AllArgsConstructor
@Data
public class CatDto {

    private final String name;

    private final Date birthDate;

    private final String breed;

    private String id;

    private final CatColor color;

    private String ownerId;

//    public CatDto(String name, Date birthDate, String breed, CatColor color) {
//        this.name = name;
//        this.birthDate = birthDate;
//        this.breed = breed;
//        this.color = color;
//    }

    public CatDto(Cat cat) {
        id = cat.getId();
        name = cat.getName();
        birthDate = cat.getBirthDate();
        breed = cat.getBreed();
        color = cat.getColor();
        ownerId = cat.getOwnerId();
    }
}
