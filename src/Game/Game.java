package Game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class Game {
/*
	 * 배열번호	카테고리 이름		각 카테고리별 점수	
	 * 0		Aces			0 ~ 5
	 * 1		Deuces			0 ~ 10
	 * 2		Threes			0 ~ 15
	 * 3		Fours			0 ~ 20
	 * 4		Fives			0 ~ 25
	 * 5		Sixes			0 ~ 30
	 * 6		Choice			0 ~ 30
	 * 7		4 of a Kind		0 ~ 30
	 * 8		Full House		0 ~ 30
	 * 9		S. Straight		0, 15
	 * 10		L. Straight		0, 30
	 * 11		yacht			0, 50
*/
	
	private YachtHand playerHand; // 플레이어 핸드 객체 생성
	private LinkedHashMap<String, Integer> scoreBoard; // 점수판

	private Render render = new Render(); // 렌더 객체 생성
	
	// Main 클래스 생성되자마자 실행
	public Game() {
		// playerHand 초기화
		playerHand = new YachtHand();
		// scoreBoard 초기화
		scoreBoard = new LinkedHashMap<String, Integer>();
		// scoreBoard 초기화 메서드
		initScoreBoard();
	}
	
	// scoreBoard 초기화 메서드
	private void initScoreBoard() {
		scoreBoard.put("Aces", 0);
		scoreBoard.put("Deuces", 0);
		scoreBoard.put("Threes", 0);
		scoreBoard.put("Fours", 0);
		scoreBoard.put("Fives", 0);
		scoreBoard.put("Sixes", 0);
		scoreBoard.put("Bonus", 0);
		scoreBoard.put("Choice", 0);
		scoreBoard.put("4 of a Kind", 0);
		scoreBoard.put("Full House", 0);
		scoreBoard.put("Small Straight", 0);
		scoreBoard.put("Large Straight", 0);
		scoreBoard.put("Yacht", 0);
		scoreBoard.put("Total", 0);
	}
	
	public void play() {
		// scanner 생성
		Scanner scanner = new Scanner(System.in);
		for(int i=0; i<3; i++) {
			System.out.printf("주사위를 굴려주세요. \n");
			System.out.printf("앞으로 주사위를 %d번 굴릴 수 있습니다. \n", 3-i);
			System.out.printf("주사위 전체를 굴리시겠습니까? (y/n) :");
			if(scanner.next().equalsIgnoreCase("y")) {
				// 손패에 있는 주사위 모두 굴리기
				playerHand.rollAll();
				System.out.println("\n주사위 결과 : " + playerHand);
			} else {
				System.out.println("\n굴릴 주사위를 선택해주세요. (0-4)\n(스페이스바로 구분)");
				System.out.println(playerHand);
				scanner.nextLine(); // 공백포함해서 문자열값 받음
				// 받아온 값들을 공백 기준으로 구분해서 inDices에 저장
				String[] inDices = scanner.nextLine().split(" ");
				// 선택한 주사위만 굴리기
				playerHand.rollSelected(parseInDices(inDices));
				System.out.println("\n주사위 결과 : " + playerHand);
			}
		}
		
		// 족보 체크 클래스 필요 << GameManager에 만듬
		// ones, twos ... 식으로 출력말고 현재 손패에서 가능한 족보만 출력하게끔
        System.out.println("점수를 입력할 곳을 적어주세요. \n(Aces, Deuces, Threes, Fours, Fives, Sixes): ");
        String category = scanner.next();
        int score = sumSameScore(category);
        scoreBoard.put(category, score);
        System.out.println(category + ": " + score);
        // 점수판 출력 메서드 
        render.renderScoreBoard(scoreBoard);
	}
	
	// 플레이어 손패에서 선택한 주사위 값만 더해서 score로 반환하는 메서드
	private int sumSameScore(String category) {
		int score = 0;
		for(Dice die : playerHand.getDice()) {
			if(die.getValue() == getCategoryValue(category)) {
				score += die.getValue();
			}
		}
		return score;
	}
	
	// 선택한 카테고리 String에서 int 값으로 변환하는 메서드
	private int getCategoryValue(String category) {
		switch(category) {
		case "Aces" : return 1;
		case "Deuces" : return 2;
		case "Threes" : return 3;
		case "Fours" : return 4;
		case "Fives" : return 5;
		case "Sixes" : return 6;
		default : throw new IllegalArgumentException("Invalid category");
		}
	}
	
	// 선택한 주사위 값만 잘라서 선택한 주사위 위치(숫자값)만 반환하는 메서드
	private List<Integer> parseInDices(String[] inDices){
		// 선택한 주사위 위치 (숫자값) 저장하는 List 생성 
		List<Integer> result = new ArrayList<>();
	
		for(String index : inDices) {
			// String -> Integer 형식으로 변환 후 result에 저장
			result.add(Integer.parseInt(index));
		}
		return result;
	}
}