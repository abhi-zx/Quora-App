package com.example.QuoraApp.services;

import com.example.QuoraApp.Repositories.UserRepositories;
import com.example.QuoraApp.models.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private UserRepositories userRepositories;

    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

   public User rigisterUser(User user){
        userRepositories.save(user);
        return user;
    }

    public Optional<User>getUserById(Long id){
        return userRepositories.findById(id);

    }
    public User saveUser(User user) {
        return userRepositories.save(user);
    }
}
