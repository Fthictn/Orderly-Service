package com.orderly.LightDTO.orderly.service;

import com.orderly.LightDTO.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.orderly.response.AnswerResponse;

public interface AnswerService {
    com.orderly.LightDTO.orderly.response.AnswerResponse getAnswersByPostId(int postId);
    com.orderly.LightDTO.orderly.response.AnswerResponse getAllAnswers();
    AnswerResponse createAnswer(AnswerLightDTO entity);
}
