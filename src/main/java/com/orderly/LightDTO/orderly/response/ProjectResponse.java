package com.orderly.LightDTO.orderly.response;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.orderly.LightDTO.ProjectLightDTO;
import com.orderly.entity.ProjectEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectResponse extends CommonResponse {

    @ApiModelProperty(notes = "light body of the response", name = "response")
    private List<ProjectLightDTO> response;

}
