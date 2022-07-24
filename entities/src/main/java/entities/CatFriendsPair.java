package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cat_friends")
public class CatFriendsPair {
    @Id
    private String id;
    @Column(name = "cat1_id")
    private String cat1Id;
    @Column(name = "cat2_id")
    private String cat2Id;

    public CatFriendsPair(String cat1_id, String cat2_id) {
        this.cat1Id = cat1_id;
        this.cat2Id = cat2_id;
        this.id = UUID.randomUUID().toString();
    }

}
