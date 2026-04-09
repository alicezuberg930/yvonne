package server.rem.services;

import org.springframework.stereotype.Service;
import server.rem.entities.User;
import server.rem.repositories.UserRepository;
import server.rem.utils.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}