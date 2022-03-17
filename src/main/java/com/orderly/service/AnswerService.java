package com.orderly.service;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.response.AnswerResponse;
import org.springframework.stereotype.Service;

public interface AnswerService {
    AnswerResponse getAnswersByPostId(int postId);
    AnswerResponse getAllAnswers();
    AnswerResponse createAnswer(AnswerLightDTO entity);
}
