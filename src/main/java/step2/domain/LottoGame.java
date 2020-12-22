package step2.domain;

import step2.exception.IsLessThanTheMiminumAmountOfMoneyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoGame {

    private static final int MINIMUM_INPUT_MONEY = 1_000;
    public static final String MONEY_IS_LESS_THAN_1000 = "구입 금액은 최소 1000원이어야 합니다.";

    private List<Lottery> lotteryList;

    public LottoGame() {

    }

    public LottoGame(int money) {
        this.buy(money);
    }

    /**
     * 입력받은 구입 금액만큼 로또를 구입하는 메소드
     * @param money 입력받은 구입 금액
     * @return 발급된 로또 용지 묶음
     */
    public List<Lottery> buy(int money) {
        if (money < MINIMUM_INPUT_MONEY) {
            throw new IsLessThanTheMiminumAmountOfMoneyException(MONEY_IS_LESS_THAN_1000);
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

    public List<Lottery> getLotteryList() {
        return this.lotteryList;
    }

    /**
     * 로또들의 당첨 결과를 Map으로 리턴하는 메소드
     * @param generatedLotteries 현재 생성된 복권 리스트
     * @param winningLottery 당첨 복권
     * @return 각 복권들의 당첨 결과 Map
     */
    public Map<Lottery, Integer> matchLottery(List<Lottery> generatedLotteries, WinningLottery winningLottery) {
        Map<Lottery,Integer> matchedResult = new HashMap<>();

        for (Lottery lottery : generatedLotteries) {
            matchedResult.put(lottery, lottery.match(winningLottery));
        }

        return matchedResult;
    }

    /**
     * 수익률을 계산하는 메소드
     * @param money 구입 금액
     * @param matchedResult 로또 매칭 결과
     * @return 수익률
     */
    public float calculateProfit(int money, Map<Lottery, Integer> matchedResult) {
        return (float) (getProfit(matchedResult) / money);
    }

    /**
     * 총 당첨 금액을 계산하는 메소드
     * @param matchedResult 로또 매칭 결과
     * @return 당첨 금액
     */
    private int getProfit(Map<Lottery, Integer> matchedResult) {
        int money = 0;

        for (int count : matchedResult.values()) {
            money += Prize.getWinningMoney(count);
        }
        return money;
    }
}
