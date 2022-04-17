package com.orderly.response;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.entity.AnswerEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AnswerResponse extends CommonResponse{

    @ApiModelProperty(notes = "light body of the response", name = "response")
    private List<AnswerLightDTO> response;

    @ApiModelProperty(notes = "response answer entity")
    private AnswerEntity answerEntity;

}