package com.example.QuoraApp.Repositories;

import com.example.QuoraApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User,Long> {

}
