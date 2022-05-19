package com.orderly.LightDTO.orderly.response;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.orderly.LightDTO.PostLightDTO;
import com.orderly.entity.PostEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostResponse extends CommonResponse {

    @ApiModelProperty(notes = "light body of the response", name = "response")
    private List<PostLightDTO> response;

}
