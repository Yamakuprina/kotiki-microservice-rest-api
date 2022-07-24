package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "enabled")
    private boolean enabled;
//    @OneToOne
//    @JoinColumn(name = "owner_id")
//    private Owner owner;
    private String ownerId;

    public User(UserDto userDto) {
        this.id =  UUID.randomUUID().toString();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.role = userDto.getRole();
        this.enabled = userDto.isEnabled();
        this.ownerId=userDto.getOwnerId();
    }
}
