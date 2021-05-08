package com.orderly.response;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.PostLightDTO;
import com.orderly.entity.PostEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponse {

    @ApiModelProperty(notes = "error message of the response", name = "errorMessage")
    private String errorMessage;

    @ApiModelProperty(notes = "status code of the response", name = "statusCode")
    private HttpStatus statusCode;

    @ApiModelProperty(notes = "light body of the response", name = "response")
    private List<PostLightDTO> response;

    @ApiModelProperty(notes = "date of the response", name = "dateTime")
    private LocalDateTime dateTime = LocalDateTime.now();
}
