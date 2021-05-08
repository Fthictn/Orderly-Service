package com.orderly.entity;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "answer")
@ApiModel(description = "Answer Model")
public class AnswerEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "id of the Answer", name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="post_id")
    @ApiModelProperty(notes = "id of the Post", name = "postId")
    private PostEntity postId;

    @ApiModelProperty(notes = "content of the Answer", name = "content")
    private String content;

    @Column(name = "created_time")
    @ApiModelProperty(notes = "created time of the Answer", name = "createdTime")
    private String createdTime;

    @Column(name = "is_correct")
    @ApiModelProperty(notes = "correctless state of the Answer", name = "isCorrect")
    private String isCorrect;

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id=" + id +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", isCorrect='" + isCorrect + '\'' +
                '}';
    }

    public AnswerEntity() { }

}
