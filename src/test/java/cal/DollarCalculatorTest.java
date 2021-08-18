package cal;

import Cal.Calculator;
import Cal.DollarCalculator;
import Cal.MarketApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//Mocking준비 완료
@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {
    @Mock
    public MarketApi marketApi;//Mock객체 생성

    @BeforeEach//테스트가 실행되기 이전에
    public void init(){

        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
        //marketApi.connect()가 호출이 될때 내가 원하는 3000원을 리턴한다.
    }

    //메인을 만들어서 하는것이 아니라 테스트 클래스를 만들어야된다
    @Test
    public void testHello(){
        System.out.println("hello");
    }
    @Test
    public void dollarTest(){
        System.out.println("hello JUnit");

        //테스트
        //Cal.Calculator calculator = new Cal.Calculator(new Cal.KrwCalculator());
        //System.out.println(calculator.sum(10,10));

        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();
        Calculator calculator = new Calculator(dollarCalculator);

        Assertions.assertEquals(22000 , calculator.sum(10,10));
        //원하는 기대값 20 , 테스트할 메소드
        Assertions.assertEquals(0,calculator.minus(10,10));
        //Market에서의 price는 항상 1100원이 아니다
        //Mocking처리를 해야된다.
        //특별한 객체가 Mocking처리가 되야될떄 원하는 메소드로 호출되었을때 원하는 결과값을 리턴 시켜줄수 있다.

    }
    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);
        System.out.println(calculator.sum(10,10));
        Assertions.assertEquals(60000 , calculator.sum(10,10));
        Assertions.assertEquals(0,calculator.minus(10,10));

    }

}
