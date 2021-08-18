package Cal;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello JUnit");

        //테스트
        Calculator calculator = new Calculator(new KrwCalculator());
        System.out.println(calculator.sum(10,10));

        //
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();
        calculator = new Calculator(dollarCalculator);
        System.out.println(calculator.sum(10,10));


    }
}
