package com.orderly.LightDTO.orderly.LightDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLightDTO {

    private int id;

    private String userNameSurname;

    private String userTitle;

    private String userRole;

    private String userPassword;

    private String userEmail;

    private String isBanned;
}
