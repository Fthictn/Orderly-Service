package com.orderly.LightDTO;

import com.orderly.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AnswerLightDTO {

    private int id;

    private String content;

    private String createdTime;

    private String isCorrect;

    private PostLightDTO postEntity;

}
