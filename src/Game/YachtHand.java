package Game;

import java.util.ArrayList;
import java.util.List;

import yachtGame.Dice;

public class YachtHand {
	private List<Dice> dice; // 주사위 값을 저장할 리스트
	
	// 생성되자마자 바로 실행
	public YachtHand() {
		// ArrayList 객체 생성
		dice = new ArrayList<>();
		// 5번 반복 후 ArrayList에 add
		for(int i=0; i<5; i++) {
			dice.add(new Dice());
		}
	}
	
	// 모든 주사위 굴리는 메서드
	public void rollAll() {
		for(Dice die : dice) {
			die.roll();
		}
	}
	
	// 선택한 주사위만 굴리는 메서드
	public void rollSelected(List<Integer> inDices) {
		for(int index : inDices) {
			dice.get(index).roll();
		}
	}

	public List<Dice> getDice() {
		return dice;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        for (Dice die : dice) {
        	// append() 문자열 합치기
            sb.append(die).append(" ");
        }
        // trim() 문자열 앞뒤 제거
        return sb.toString().trim();
	}

}