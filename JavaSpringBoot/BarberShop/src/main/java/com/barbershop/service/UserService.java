package com.barbershop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.dto.UserDTO;
import com.barbershop.entity.RolePermission;
import com.barbershop.entity.User;
import com.barbershop.exception.Exception;
import com.barbershop.repository.RolePermissionRepository;
import com.barbershop.repository.UserRepository;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private LanguageService languageService;
    
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    
    private final Map<String, String> userTokens = new HashMap<>();

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getUserByUserName(String userName) {
        User user = userRepository.findFirstByUserName(userName);
        return user != null ? new UserDTO(user) : null;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        if(userDTO.getId() != 0) {
        	user.setId(userDTO.getId());
        }
        user.setUserName(userDTO.getUserName());
        if(getUserByUserName(userDTO.getUserName()) != null) {
        	throw new Exception("User with username " + userDTO.getUserName() + " already exists.");
        }
        user.setPassword(userDTO.getPassword());
        
        if(userDTO.getRoleName() != null) {
        	user.setRole(roleService.getRoleByName(userDTO.getRoleName()));
        }
        if(userDTO.getLanguageName() != null) {
        	user.setLanguage(languageService.getLanguageName(userDTO.getLanguageName()));
        }
        
        return new UserDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO login(UserDTO userDTO) {
        User user = userRepository.findFirstByUserName(userDTO.getUserName());

        if (user != null && user.getPassword().toString().equals(userDTO.getPassword())) {
            String token = generateToken();
            userTokens.put(user.getUserName(), token);

            UserDTO loggedInUser = new UserDTO(user);
            
            List<RolePermission> list = rolePermissionRepository.findPermissionsByRoleId(user.getRole().getId());
            
            loggedInUser.setToken(token);
            loggedInUser.setRolePermissions(list);
            
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
