package com.smsm.repository;

import com.smsm.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRopository extends JpaRepository<Question, Long> {
}
