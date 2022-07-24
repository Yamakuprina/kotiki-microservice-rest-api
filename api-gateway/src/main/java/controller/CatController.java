package controller;

import entities.CatColor;
import entities.CatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.CatService;
import service.UserService;
import userDetails.UserRole;

import java.util.Collection;

@RestController
@RequestMapping("/cats/")
public class CatController {

    @Autowired
    private CatService catService;

    @Autowired
    private UserService userService;

    @GetMapping("all")
    public ResponseEntity<Object> AllCats() {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
                return ResponseEntity.ok(catService.getAllCats());
            } else {
                return ResponseEntity.ok(userService.getAllCats());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("id")
    public ResponseEntity<Object> findById(@RequestParam String id) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
                return ResponseEntity.ok(catService.findById(id));
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("id/friends")
    public ResponseEntity<Object> getFriendsById(@RequestParam String id) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
                return ResponseEntity.ok(catService.getFriendsById(id));
            } else {
                return ResponseEntity.ok(userService.getFriendsByCatId(id));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("id/friends")
    public ResponseEntity<Object> addFriendById(@RequestParam String id, @RequestParam String friendId) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
                catService.addCatToFriends(id, friendId);
                return ResponseEntity.ok("Friend successfully saved.");
            } else {
                userService.addCatToFriends(id, friendId);
                return ResponseEntity.ok("Friend successfully saved.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("id/friends")
    public ResponseEntity<Object> deleteFriendById(@RequestParam String id, @RequestParam String friendId) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
                catService.deleteCatFromFriends(id, friendId);
                return ResponseEntity.ok("Friend successfully deleted.");
            } else {
                userService.deleteCatFromFriends(id, friendId);
                return ResponseEntity.ok("Friend successfully deleted.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("id/owner")
//    public ResponseEntity<Object> OwnerByCatId(@RequestParam String id) {
//        try {
//            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
//                return ResponseEntity.ok(catService.findOwnerByCatId(id));
//            } else {
//                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @GetMapping("color")
    public ResponseEntity<Object> CatsWithCatColor(@RequestParam CatColor color) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
                return ResponseEntity.ok(catService.getCatsWithCatColor(color));
            } else {
                return ResponseEntity.ok(userService.getCatsWithCatColor(color));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("save")
    public ResponseEntity<Object> save(@RequestBody CatDto catDto) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
                catService.save(catDto);
                return ResponseEntity.ok("Cat successfully saved.");
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PostMapping("id/owner")
//    public ResponseEntity<Object> setOwnerById(@RequestParam String catId, @RequestParam String ownerId) {
//        try {
//            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
//                catService.setOwnerById(catId, ownerId);
//                return ResponseEntity.ok("Successfully set new owner.");
//            } else {
//                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @DeleteMapping("id")
    public ResponseEntity<Object> delete(@RequestParam String id) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(UserRole.ADMIN.toString()))){
                catService.delete(id);
                return ResponseEntity.ok("Cat successfully deleted.");
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
