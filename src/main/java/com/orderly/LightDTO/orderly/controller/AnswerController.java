package com.orderly.LightDTO.orderly.controller;

import com.orderly.LightDTO.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.orderly.response.AnswerResponse;
import com.orderly.LightDTO.orderly.service.AnswerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@ApiOperation(value = "/orderly/answer", tags = "Answer Controller", notes = "Answer API")
@RestController
public class AnswerController {

    private final com.orderly.LightDTO.orderly.service.AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get all answers ", response = com.orderly.LightDTO.orderly.response.AnswerResponse.class)
    @GetMapping("/getAllAnswers")
    public com.orderly.LightDTO.orderly.response.AnswerResponse getAllAnswers(){
        return service.getAllAnswers();
    }

    @ApiOperation(value = "Add answer", response = com.orderly.LightDTO.orderly.response.AnswerResponse.class)
    @PostMapping("/createAnswer")
    public com.orderly.LightDTO.orderly.response.AnswerResponse createAnswer(@RequestBody AnswerLightDTO entity){
        return service.createAnswer(entity);
    }

    @ApiOperation(value = "Get answer by post id", response = com.orderly.LightDTO.orderly.response.AnswerResponse.class)
    @PostMapping("/posts/{postId}/answers")
    public AnswerResponse getAllAnswersByPostId(@PathVariable(value = "postId") int postId){
        return service.getAnswersByPostId(postId);
    }
}