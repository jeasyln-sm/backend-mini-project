package com.smsm.service;

import com.smsm.entity.Answer;
import com.smsm.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerServiceTest {

    @Autowired
    private AnswerRepository answerRepository;


    @Test
    void saveTest() {
        Answer a1 = new Answer();
        a1.setContent("test");
        a1.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a1);
        System.out.println(a1);
    }

}
