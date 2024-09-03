package yachtGame;

import java.util.Random;

public class Dice {
	private int value; // 주사위를 굴려서 나온 값
	private static final Random random = new Random(); // Random 사용
	
	// 생성되자마자 주사위 굴릴 수 있게 생성자 지정
	public Dice() {
		roll();
	}
	
	// 주사위 굴리기 메서드
	public void roll() {
		value = random.nextInt(6)+1;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
