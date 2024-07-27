package com.example.QuoraApp.Repositories;


import com.example.QuoraApp.models.AnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerModel, UUID> {
}
