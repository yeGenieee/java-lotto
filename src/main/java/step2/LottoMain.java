package step2;

public class LottoMain {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        LottoGame lottoGame;

        inputView.init();
        inputView.showInputMessage();
        int money = inputView.getPurchaseAmount();
        lottoGame = new LottoGame(money);

        resultView.showPurchasedLottery(lottoGame.getLotteryList());
    }
}
