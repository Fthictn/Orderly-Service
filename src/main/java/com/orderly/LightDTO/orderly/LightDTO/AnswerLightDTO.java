package com.orderly.LightDTO.orderly.LightDTO;

import com.orderly.entity.PostEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerLightDTO {

    private int id;

    private String content;

    private String createdTime;

    private String isCorrect;

    private PostLightDTO postEntity;

}
