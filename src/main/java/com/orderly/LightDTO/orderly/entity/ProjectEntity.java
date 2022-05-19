package com.orderly.LightDTO.orderly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orderly.entity.BaseEntity;
import com.orderly.entity.PostEntity;
import com.orderly.entity.UserEntity;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "project")
@ApiModel(description = "Project Model")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectEntity extends BaseEntity{

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

}
