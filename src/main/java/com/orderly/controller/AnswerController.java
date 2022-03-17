package com.orderly.controller;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.response.AnswerResponse;
import com.orderly.service.AnswerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@ApiOperation(value = "/orderly/answer", tags = "Answer Controller", notes = "Answer API")
@RestController
public class AnswerController {

    private final AnswerService service;

    public AnswerController(@Qualifier("answerServiceImpl") AnswerService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get all answers ", response = AnswerResponse.class)
    @GetMapping("/getAllAnswers")
    public AnswerResponse getAllAnswers(){
        return service.getAllAnswers();
    }

    @ApiOperation(value = "Add answer", response = AnswerResponse.class)
    @PostMapping("/createAnswer/")
    public AnswerResponse createAnswer(@RequestBody AnswerLightDTO entity){
        return service.createAnswer(entity);
    }

    @ApiOperation(value = "Get answer by post id", response = AnswerResponse.class)
    @PostMapping("/posts/{postId}/answers")
    public AnswerResponse getAllAnswersByPostId(@PathVariable(value = "postId") int postId){
        return service.getAnswersByPostId(postId);
    }

}
