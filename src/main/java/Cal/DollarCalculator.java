package Cal;

public class DollarCalculator implements ICalculator{
    private int price = 1;
    private MarketApi marketApi;
    public DollarCalculator(MarketApi marketApi){
        this.marketApi = marketApi;
    }
    public void init(){
        this.price = marketApi.connect();
    }
    /*
    public int connect(){
        //naver
        //kakao
        //특정 WEB사이트 접근하여 현재 환율을 읽어 왓다고 가정
        //계산기 인테 connect가 있으면안된다
        //분리

        return 1100;
    }
    */
    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x+y;
    }
    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;
        return x-y;
    }
}
