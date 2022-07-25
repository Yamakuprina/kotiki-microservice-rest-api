package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto implements Serializable {
    private String name;
    private Date birthDate;
    private String id;

    public OwnerDto(Owner owner) {
        name = owner.getName();
        birthDate = owner.getBirthDate();
        id = owner.getId();
    }
}
