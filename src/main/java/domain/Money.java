package domain;

import java.util.Map;

public class Money {
	public static final double ZERO = 0;
	public static final int MINUS_CONVERTER = -1;
	private static final double BLACK_JACK_BONUS = 1.5;
	private static final String ERROR_MESSAGE_NOT_INT = "숫자가 아닌 문자를 입력하였습니다.";
	private static final String ERROR_MESSAGE_UNDER_ZERO = "0보다 작은 수를 입력하였습니다.";

	private final double money;

	private Money(double money) {
		validate(money);
		this.money = money;
	}

	public static Money valueOf(String money) {
		try {
			Double.parseDouble(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INT);
		}
		return new Money(Double.parseDouble(money));
	}

	public static Money valueOf(int money) {
		return new Money(money);
	}

	private void validate(double money) {
		if (money <= ZERO) {
			throw new IllegalArgumentException(ERROR_MESSAGE_UNDER_ZERO);
		}
	}

	public double getMoney() {
		return money;
	}

	public double toLoseMoney() {
		return money * MINUS_CONVERTER;
	}

	public double toBlackJackWinMoney() {
		return money * BLACK_JACK_BONUS;
	}

	public static double calculateDealerMoney(GameResult gameResult) {
		Map<String, Double> userResult = gameResult.getUserResult();
		return userResult.values().stream()
				.reduce(ZERO, Double::sum) * MINUS_CONVERTER;
	}
}
