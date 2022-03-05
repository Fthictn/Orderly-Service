package com.orderly.response;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.UserLightDTO;
import com.orderly.entity.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponse extends CommonResponse{

    @ApiModelProperty(notes = "light body of the response", name = "response")
    private List<UserLightDTO> response;

    @ApiModelProperty(notes = "token of the response", name = "token")
    private String token;
}
