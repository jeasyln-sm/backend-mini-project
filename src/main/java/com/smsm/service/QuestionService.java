package com.smsm.service;

import com.smsm.repository.QuestionRopository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRopository questionRopository;
}
