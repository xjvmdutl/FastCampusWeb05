package Cal;

public class Calculator {
    private ICalculator iCalculator;

    public Calculator(ICalculator iCalculator){
        this.iCalculator = iCalculator;
        //외부에서 주입받는다
    }

    public int sum(int x,int y){
        return this.iCalculator.sum(x,y);
    }
    public int minus(int x,int y){
        return this.iCalculator.minus(x, y);
    }
}
