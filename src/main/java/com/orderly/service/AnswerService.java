package com.orderly.service;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.response.AnswerResponse;

public interface AnswerService {
    AnswerResponse getAnswersByPostId(int postId);
    AnswerResponse getAllAnswers();
    AnswerResponse createAnswer(AnswerLightDTO entity);
}
