package step2;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    private static List<Lottery> lotteryList;

    /**
     * 입력받은 구입 금액만큼 로또를 구입하는 메소드
     * @param money 입력받은 구입 금액
     * @return 발급된 로또 용지 묶음
     */
    public List<Lottery> buy(int money) {
        if (money < 1000) {
            throw new IllegalArgumentException(Constants.MONEY_IS_LESS_THAN_1000);
        }
        return makeLotteryList(money);
    }

    /**
     * 로또 용지 묶음을 만드는 메소드
     * @param money 입력받은 구입 금액
     * @return 발급된 용지 묶음
     */
    public List<Lottery> makeLotteryList(int money) {
        lotteryList = new ArrayList<>();

        int numberOfLottery = getNumberOfLottery(money);

        for (int i = 0; i < numberOfLottery; i++) {
            Lottery lottery = new Lottery();
            lotteryList.add(lottery);
        }

        return lotteryList;
    }

    /**
     * 입력받은 구입 금액으로 살 수 있는 복권 장 수를 얻는 메소드
     * @param money 입력받은 구입 금액
     * @return 구입 가능한 복권 장 수
     */
    public int getNumberOfLottery(int money) {
        return money / 1000;
    }
}
