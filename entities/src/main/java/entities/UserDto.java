package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private final String id;

    private final String username;

    private final String password;

    private final String role;

    private final boolean enabled;

    private String ownerId;

//    public UserDto(String id, String username, String password, String role, boolean enabled) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//        this.enabled = enabled;
//    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.enabled = user.isEnabled();
        this.ownerId = user.getOwnerId();
    }
}