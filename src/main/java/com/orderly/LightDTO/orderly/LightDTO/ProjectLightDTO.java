package com.orderly.LightDTO.orderly.LightDTO;

import com.orderly.LightDTO.UserLightDTO;
import com.orderly.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectLightDTO {

    private int id;

    private String projectName;

    private String projectCode;

    private UserLightDTO userEntity;
}
