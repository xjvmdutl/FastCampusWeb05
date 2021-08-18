package com.example.FastCampusWeb05.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest//해당 어노테이션을 붙여야한다//통합테스트, 모든 빈이 다 올라간다.
public class DollarCalculatorTest {

    //MarketApi를 주입 받아야된다

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private Calculator calculator;

    //DollarCalculator도 스프링에서 Bean으로 관리한다.
    @Test
    public void dollarCalculatorTest(){
        //MarketApi의 connect가 발생할때 3000을 리턴한다
        Mockito.when(marketApi.connect()).thenReturn(3000);
        int sum = calculator.sum(10,10);
        int minus = calculator.minus(10,10);
        Assertions.assertEquals(60000,sum);
        Assertions.assertEquals(0,minus);
    }
}
