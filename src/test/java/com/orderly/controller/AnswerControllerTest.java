package com.orderly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.PostLightDTO;
import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import com.orderly.response.AnswerResponse;
import com.orderly.service.AnswerService;
import org.assertj.core.util.Arrays;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = AnswerController.class)
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("unit test for get all answer operation")
    @Test
    public void givenListOfAnswers_whenGetAllAnswers_thenReturnListOfAnswers() throws Exception {
        //given
        List<AnswerEntity> answerList = new ArrayList<>();
        answerList.add(AnswerEntity.builder()
                .content("Content")
                .createdTime("22/12/2022")
                .id(1)
                .isCorrect("0")
                .build());

        AnswerResponse response = new AnswerResponse();
        response.setResponse(answerList.stream().map(a -> AnswerLightDTO
                .builder()
                .content(a.getContent())
                .build()).collect(Collectors.toList()));
        given(answerService.getAllAnswers()).willReturn(response);

        ResultActions answerResponse = mockMvc.perform(get("/getAllAnswers"));

        answerResponse.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",
                        CoreMatchers.is(answerList.size())));
    }

    @DisplayName("unit test for create answer operation")
    @Test
    public void givenAnswerLightDto_whenCreateAnswer_thenReturnAnswerResponse() throws Exception {
        PostLightDTO postLightDTO = new PostLightDTO();
        postLightDTO.setId(1);
        postLightDTO.setCreatedTime("10/10/2022");
        postLightDTO.setPostContent("Post content");
        postLightDTO.setPostTitle("Post title");
        postLightDTO.setIsSolved("0");

//        AnswerLightDTO answerLightDTO = new AnswerLightDTO();
//        answerLightDTO.setPostEntity(postLightDTO);
//        answerLightDTO.setContent("Answer content");
//        answerLightDTO.setIsCorrect("0");
//        answerLightDTO.setId(1);
//        answerLightDTO.setCreatedTime("10/10/2021");
//
//        given(answerService.createAnswer(ArgumentMatchers.any(AnswerLightDTO.class)))
//                .willAnswer((invocation) -> invocation.getArgument(0));
//
//        ResultActions response = mockMvc.perform(post("/createAnswer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(answerLightDTO)));
//
//        response.andExpect(MockMvcResultMatchers.jsonPath("$.content",
//                CoreMatchers.is(answerLightDTO.getContent())));

    }

    @Test
    void getAllAnswersByPostId() {
    }
}