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
@Table(name = "post")
@ApiModel(description = "Post Model")
public class PostEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "id of the Post", name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private ProjectEntity projectId;

    @Column(name = "post_title")
    @ApiModelProperty(notes = "title of the Post", name = "postTitle")
    private String postTitle;

    @OneToMany(mappedBy = "postId",cascade = {CascadeType.ALL})
    private List<AnswerEntity> answers;

    @Column(name = "post_content")
    @ApiModelProperty(notes = "content of the Post", name = "postContent")
    private String postContent;

    @Column(name = "created_time")
    @ApiModelProperty(notes = "Created time of the Post", name = "createdTime")
    private String createdTime;

    @Column(name = "is_solved")
    @ApiModelProperty(notes = "solving state of the Post", name = "isSolved")
    private String isSolved;

    //Technical Question, General Question, Installation Question
    @ApiModelProperty(notes = "type of the Post", name = "type")
    private String type;

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", postTitle='" + postTitle + '\'' +
                ", answers=" + answers +
                ", postContent='" + postContent + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", isSolved='" + isSolved + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public PostEntity() { }


}
