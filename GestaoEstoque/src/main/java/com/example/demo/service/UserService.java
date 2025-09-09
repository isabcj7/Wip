package com.example.demo.service;

import com.example.demo.model.Stock;
import com.example.demo.model.User;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já registrado");
        }
        User newUser = userRepository.save(user);
        Stock stock = new Stock();
        stock.setUser(newUser);
        stockRepository.save(stock);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public User updateUser(Integer id, User user) {
        User existingUser = getUserById(id);
        existingUser.setNome(user.getNome());
        existingUser.setEmail(user.getEmail());
        existingUser.setPreferencias(user.getPreferencias());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}