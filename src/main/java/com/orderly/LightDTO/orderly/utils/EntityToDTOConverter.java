package com.orderly.LightDTO.orderly.utils;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.PostLightDTO;
import com.orderly.LightDTO.ProjectLightDTO;
import com.orderly.LightDTO.UserLightDTO;
import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import com.orderly.entity.ProjectEntity;
import com.orderly.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EntityToDTOConverter {

    public UserLightDTO userConverter(UserEntity entity){
        UserLightDTO lightDTO = new UserLightDTO();
        if(entity != null){
            lightDTO.setId(entity.getId());
            lightDTO.setIsBanned(entity.getIsBanned());
            lightDTO.setUserEmail(entity.getUserEmail());
            lightDTO.setUserNameSurname(entity.getUserNameSurname());
            lightDTO.setUserPassword(entity.getUserPassword());
            lightDTO.setUserRole(entity.getUserRole());
            lightDTO.setUserTitle(entity.getUserTitle());
        }
        return lightDTO;
    }

    public ProjectLightDTO projectConverter(ProjectEntity entity){
        ProjectLightDTO lightDTO = new ProjectLightDTO();
        if(entity != null){
            lightDTO.setId(entity.getId());
            lightDTO.setProjectCode(entity.getProjectCode());
            lightDTO.setProjectName(entity.getProjectName());
            lightDTO.setUserEntity(this.userConverter(entity.getUserId()));
        }
        return lightDTO;
    }

    public PostLightDTO postConverter(PostEntity entity){
        PostLightDTO lightDTO = new PostLightDTO();
        if(entity != null) {
            lightDTO.setId(entity.getId());
            lightDTO.setCreatedTime(entity.getCreatedTime());
            lightDTO.setIsSolved(entity.getIsSolved());
            lightDTO.setPostContent(entity.getPostContent());
            lightDTO.setPostTitle(entity.getPostTitle());
            lightDTO.setType(entity.getType());
            lightDTO.setProjectEntity(this.projectConverter(entity.getProjectId()));
        }
        return lightDTO;
    }

    public AnswerLightDTO answerConverter(AnswerEntity entity){
        AnswerLightDTO lightDTO = new AnswerLightDTO();
        if (entity != null) {
            lightDTO.setId(entity.getId());
            lightDTO.setContent(entity.getContent());
            lightDTO.setCreatedTime(entity.getCreatedTime());
            lightDTO.setIsCorrect(entity.getIsCorrect());
            lightDTO.setPostEntity(this.postConverter(entity.getPostId()));
        }
        return lightDTO;
    }
}
