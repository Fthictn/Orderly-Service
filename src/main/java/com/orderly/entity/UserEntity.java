package com.orderly.entity;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@ApiModel(description = "User Model")
public class UserEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "id of the User", name = "id")
    private int id;

    @Column(name = "user_name_surname")
    @ApiModelProperty(notes = "name of the User", name = "userNameSurname")
    private String userNameSurname;

    @Column(name = "user_title")
    @ApiModelProperty(notes = "job title of the User", name = "userTitle")
    private String userTitle;

    @OneToMany(mappedBy = "userId",cascade = {CascadeType.MERGE})
    private List<ProjectEntity> projects;

    @Column(name = "user_role")
    @ApiModelProperty(notes = "role of the User", name = "userRole")
    private String userRole;

    @NotNull
    @Column(name = "user_password")
    @ApiModelProperty(notes = "password of the User", name = "userPassword")
    private String userPassword;

    @NotNull
    @Column(name = "user_email")
    @ApiModelProperty(notes = "email of the User", name = "userEmail")
    private String userEmail;

    @Column(name = "is_banned")
    @ApiModelProperty(notes = "ban state of the User", name = "isBanned")
    private String isBanned;

}
