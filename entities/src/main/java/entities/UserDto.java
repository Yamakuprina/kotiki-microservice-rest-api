package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private String id;

    private String username;

    private String password;

    private String role;

    private boolean enabled;

    private String ownerId;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.enabled = user.isEnabled();
        this.ownerId = user.getOwnerId();
    }
}
