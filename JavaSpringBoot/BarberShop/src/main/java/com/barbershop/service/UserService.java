package com.barbershop.service;

import com.barbershop.dto.UserDTO;
import com.barbershop.entity.User;
import com.barbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
        // Set other fields
        return new UserDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO login(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUserName());
        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            return new UserDTO(user);
        }
        return null;
    }
}
