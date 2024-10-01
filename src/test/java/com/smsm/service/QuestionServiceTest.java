package com.smsm.service;

import com.smsm.entity.Question;
import com.smsm.repository.QuestionRopository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private QuestionRopository questionRopository;

    @Test
    void saveTest() {
        Question q1 = new Question();
        q1.setTitle("제목");
        q1.setContent("내용");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRopository.save(q1);
        System.out.println(q1);
    }

}
