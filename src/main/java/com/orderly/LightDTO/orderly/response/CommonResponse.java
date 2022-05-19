package com.orderly.LightDTO.orderly.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class CommonResponse {

    @ApiModelProperty(notes = "error message of the response", name = "errorMessage")
    private String errorMessage;

    @ApiModelProperty(notes = "status code of the response", name = "statusCode")
    private HttpStatus statusCode;

    @ApiModelProperty(notes = "date of the response", name = "response")
    private LocalDateTime dateTime = LocalDateTime.now();
}
