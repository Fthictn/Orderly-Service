package com.orderly.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

        @ApiModelProperty(notes = "email of the request", name = "email")
        private String email;

        @ApiModelProperty(notes = "password of the request", name = "password")
        private String password;

        @ApiModelProperty(notes = "userName of the request", name = "userName")
        private String userName;

}
