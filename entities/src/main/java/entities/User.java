package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

import javax.persistence.*;

@NoArgsConstructor
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
    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public User(String username, String password, String role, boolean enabled) {
        this.id =  UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}
