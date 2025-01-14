package com.barbershop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.dto.UserDTO;
import com.barbershop.entity.User;
import com.barbershop.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final Map<String, String> userTokens = new HashMap<>();

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return user != null ? new UserDTO(user) : null;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        return new UserDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO login(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUserName());

        if (user != null && user.getPassword().toString().equals(userDTO.getPassword())) {
            String token = generateToken();
            userTokens.put(user.getUserName(), token);

            UserDTO loggedInUser = new UserDTO(user);
            loggedInUser.setToken(token);
            return loggedInUser;
        }

        return null;
    }

    public boolean logout(String token) {
        String userName = userTokens.entrySet().stream()
                .filter(entry -> entry.getValue().equals(token))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (userName != null) {
            userTokens.remove(userName);
            return true;
        }

        return false;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
