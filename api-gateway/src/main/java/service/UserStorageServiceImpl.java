package service;

import entities.Owner;
import entities.User;
import entities.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import userRepository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStorageServiceImpl implements UserStorageService {
//    @Override
//    public void saveUser(UserDto userDto) {
//
//    }
//
//    @Override
//    public void deleteUser(String userId) {
//
//    }
//
//    @Override
//    public List<UserDto> getAllUsers() {
//        return null;
//    }
//
//    @Override
//    public void setOwnerToUser(String userId, String ownerId) {
//
//    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerServiceRabbitSender ownerService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void saveUser(UserDto userDto){
        User user = new User(userDto);
        String passwordEncoded = encoder.encode(userDto.getPassword());
        user.setPassword(passwordEncoded);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> allUsers =  userRepository.findAll();
        return usersToUserDtos(allUsers);
    }

//    @Override
//    public void setOwnerToUser(String userId, String ownerId) throws Exception {
//        User user = userRepository.findById(userId).orElseThrow();
//        Owner owner = ownerService.getOwnerById(ownerId);
//        if (owner==null) throw new Exception("Owner not found");
//        user.setOwner(owner);
//        userRepository.save(user);
//    }

    private List<UserDto> usersToUserDtos(List<User> users){
        List<UserDto> userDtos = new ArrayList<>();
        for (User user:users) {
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
