package com.example.QuoraApp.Repositories;
import com.example.QuoraApp.models.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepositorie extends JpaRepository<QuestionModel, UUID> {
    List<QuestionModel> findByTopicTagsContaining(String tag);

    List<QuestionModel> findByTitleContainingOrBodyContaining(String text, String text1);

    List<QuestionModel> findByTitleContainingOrBodyContainingAndTopicTagsContaining(String text, String text1, String tag);
}
