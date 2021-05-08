package com.orderly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "project")
@ApiModel(description = "Project Model")
public class ProjectEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "id of the Project", name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @ApiModelProperty(notes = "user id of the Project", name = "userId")
    private UserEntity userId;

    @OneToMany(mappedBy = "projectId",cascade = {CascadeType.ALL})
    private List<PostEntity> posts;

    @Column(name = "project_name")
    @ApiModelProperty(notes = "name of the Project", name = "projectName")
    private String projectName;

    @Column(name = "project_code")
    @ApiModelProperty(notes = "code of the Project", name = "projectCode")
    private String projectCode;

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", posts=" + posts +
                ", projectName='" + projectName + '\'' +
                ", projectCode='" + projectCode + '\'' +
                '}';
    }
}
