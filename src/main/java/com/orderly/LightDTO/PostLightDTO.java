package com.orderly.LightDTO;

import com.orderly.entity.ProjectEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostLightDTO {

    private int id;

    private String postTitle;

    private String postContent;

    private String createdTime;

    private String isSolved;

    private String type;

    private ProjectLightDTO projectEntity;
}
