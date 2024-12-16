package com.example.eksamensprojekt2024.service;

import com.example.eksamensprojekt2024.model.User;
import com.example.eksamensprojekt2024.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String username, String password) {
        return userRepository.getUser(username, password);
    }

    public void createUser(String username, String password, String role) {
        userRepository.createUser(username, password, role);
    }

    public void deleteUser(int employeeID){
        userRepository.deleteUser(employeeID);
    }

}
