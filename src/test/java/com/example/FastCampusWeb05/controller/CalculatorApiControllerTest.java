package com.example.FastCampusWeb05.controller;

import com.example.FastCampusWeb05.component.Calculator;
import com.example.FastCampusWeb05.component.DollarCalculator;
import com.example.FastCampusWeb05.component.MarketApi;
import com.example.FastCampusWeb05.dto.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculatorApiController.class)//원하는 클래스만 테스트 한다.
//SpringBootTest는 모든 빈을 등록하지만, 해당 어노테이션은 원하는 클래스만 웹에 등록하기때문에 자원을 줄일수있다.
@AutoConfigureWebMvc//웹 테스트를 위한 Annotation
@Import({Calculator.class, DollarCalculator.class})
public class CalculatorApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void sumTest() throws Exception {
        //http://localhost:8080/api/sum
        mockMvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x","10")
                        .queryParam("y","10")
                )
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andExpect(
                        MockMvcResultMatchers.content().string("60000")
                ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void minusTest() throws Exception {

        Req req = new Req();
        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0")
                //내가 기대하는 값이 0이어야된다.
                //result는 해당 방법으로 접근
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
                //response안에 resultCode접근
        ).andDo(MockMvcResultHandlers.print());
        //Body = {"result":0,"response":null}
    }

}
