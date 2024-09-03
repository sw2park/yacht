package yacht;

import java.util.Scanner;

public class Main {
	/*
	 * playerScoreBoard 점수판 각 번호값
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
	static int[] playerScoreBoard = new int [12]; // 플레이어 점수판
	static int[] playerDiceArr = new int [5]; // 플레이어가 굴린 주사위 넘겨받아서 저장하는 배열
	
	static int[] cpuScoreBoard = new int [12]; // cpu 점수판
	
	static int[] checkNumArr = new int [5]; // 체크용 카운트
	static int[] countNumArr = new int [6]; // 각숫자 카운트용
	
	static boolean cpuHasBonus; // cpu 보너스 활성화 여부
	static boolean playerHasBonus; // 플레이어 보너스 활성화 여부
	
	static boolean cpuIsSstraight; // cpu S_Straight 활성화 여부
	static boolean cpuIsLstraight; // cpu L_Straight 활성화 여부
	static boolean cpuIsYacht; // cpu Yacht 활성화 여부
	
	static Scanner scanner = new Scanner(System.in); // 키 입력을 받을 스캐너 선언 
	
	// 플레이어가 주사위 굴리는 메서드
	public static void rollDice() {
		for(int i=0; i<5; i++) {
			playerDiceArr[i] = (int)(Math.random() * 6) + 1;
			System.out.print(playerDiceArr[i] + " ");
		}
	}

	// 점수판 초기값 0으로 세팅
	public static void initScoreBoard() {
		// 플레이어 점수판, cpu 점수판 초기값 0으로 세팅
		for(int i=0; i < playerScoreBoard.length; i++) {
			playerScoreBoard[i] = 0;
			cpuScoreBoard[i] = 0;
		}
	}
	
	// 플레이어 보너스 체크 메서드 
	public static void checkBonus() {
		int check_bonus = 0;
		
		for(int i=0; i<6; i++) {
			check_bonus += playerScoreBoard[i];
			// 63점을 넘을시 보너스 활성화
			if(check_bonus >= 63) {
				playerHasBonus = true;
				return;
			} else {
				playerHasBonus = false;
			}
		}
	}
	
	// cpu 보너스 체크 메서드
	public static void cpuCheckBonus() {
		int check_bonus = 0;
		
		for(int i=0; i<6; i++) {
			check_bonus += cpuScoreBoard[i];
			// 63점을 넘을 시 보너스 활성화
			if(check_bonus >= 63) {
				cpuHasBonus = true;
				return;
			} else {
				cpuHasBonus = false;
			}
		}
	}
	
	// total 점수 계산 메서드 
	public static int checkTotal() {
		int total = 0;
		for(int i=0; i<12; i++) {
			total += playerScoreBoard[i];
		}
		checkBonus();
		if(playerHasBonus) {
			return total+35;
		} else
			return total;
	}
	
	// Cpu Total 점수 계산 메서드
	public static int checkCpuTotal() {
		int total = 0;
		for(int i=0; i<12; i++) {
			total += cpuScoreBoard[i];
		}
		cpuCheckBonus();
		if(cpuHasBonus) {
			return total+35;
		} else
			return total;
	}
	
	// Cpu 점수 넣는 메서드
	public static void insertCpuScore() {
		int S_Straight = 0;
		int L_Straight = 0;
		int Yacht = 0;
		
		// Cpu Aces 값 세팅
		cpuScoreBoard[0] = (int)(Math.random() * 5);
		// Cpu Deuces 값 세팅
		cpuScoreBoard[1] = (int)(Math.random() * 10);
		// Cpu Threes 값 세팅
		cpuScoreBoard[2] = (int)(Math.random() * 15);
		// Cpu Fours 값 세팅
		cpuScoreBoard[3] = (int)(Math.random() * 20);
		// Cpu Fives 값 세팅
		cpuScoreBoard[4] = (int)(Math.random() * 25);
		// Cpu Sixes 값 세팅
		cpuScoreBoard[5] = (int)(Math.random() * 30);
		// Cpu Choice 값 세팅
		cpuScoreBoard[6] = (int)(Math.random() * 30);
		// Cpu 4 of Kind 값 세팅
		cpuScoreBoard[7] = (int)(Math.random() * 30);
		// Cpu Full House 값 세팅
		cpuScoreBoard[8] = (int)(Math.random() * 30);
		
		// 임시 변수
		S_Straight = (int)(Math.random() * 2);
		L_Straight = (int)(Math.random() * 2);
		Yacht = (int)(Math.random() * 2);
		
		if(S_Straight % 2 == 0) {
			cpuScoreBoard[9] = 15;
		} else {
			cpuScoreBoard[9] = 0;
		}
		if(L_Straight % 2 == 0) {
			cpuScoreBoard[10] = 30;
		} else {
			cpuScoreBoard[10] = 0;
		}
		if(Yacht % 2 == 0) {
			cpuScoreBoard[11] = 50;
		} else {
			cpuScoreBoard[11] = 0;
		}
	}
	
	// 플레이어 주사위 배열안에 있는 것을 checkNumArr에 복사하는 메서드
	public static void copyCheckNumArr() {
		for(int i=0; i<5; i++) {
			checkNumArr[i] = playerDiceArr[i];
		}
	}
	
	// checkNumArr 배열을 초기화하는 메서드
	public static void initCheckNumArr() {
		for(int i=0; i<5; i++) {
			checkNumArr[i] = 0;
		}
	}
	
	// checkNumArr 내부의 각 숫자들의 개수를 카운트하는 메서드
	public static void countCheckNumArr() {
		final int ONE = 1;
		final int TWO = 2;
		final int THREE = 3;
		final int FOUR = 4;
		final int FIVE = 5;
		final int SIX = 6;
		
		int countOne = 0;
		int countTwo = 0;
		int countThree = 0;
		int countFour = 0;
		int countFive = 0;
		int countSix = 0;
		
		// 각 숫자 카운트
		for(int i=0; i<5; i++) {
			switch(checkNumArr[i]) {
			case ONE :
				countOne++;
				break;
			case TWO :
				countTwo++;
				break;
			case THREE : 
				countThree++;
				break;
			case FOUR :
				countFour++;
				break;
			case FIVE :
				countFive++;
				break;
			case SIX :
				countSix++;
				break;
			default :
				break;
			}
		}
		
		// 각 숫자 복사
		for (int i=1; i<7; i++) {
			
			switch(i) {
			case ONE :
				countNumArr[i-1] = countOne;
				break;
			case TWO :
				countNumArr[i-1] = countTwo;
				break;
			case THREE : 
				countNumArr[i-1] = countThree;
				break;
			case FOUR :
				countNumArr[i-1] = countFour;
				break;
			case FIVE :
				countNumArr[i-1] = countFive;
				break;
			case SIX :
				countNumArr[i-1] = countSix;
				break;
			default :
				break;
			}
		}
	}
	
	// countNumArr 배열을 초기화 하는 메서드
	public static void initCountNumArr() {
		for(int i=0; i<6;i++) {
			countNumArr[i] = 0;
		}
	}
	
	// 점수판 출력 메서드
	public static void printBoard() {
		System.out.printf("-----------점수판------------ \n");
		System.out.printf("Categories   |player|cpu     \n");
		System.out.printf("Aces         |   %2d  |  %2d  \n", playerScoreBoard[0], cpuScoreBoard[0]);
		System.out.printf("Deuces       |   %2d  |  %2d  \n", playerScoreBoard[1], cpuScoreBoard[1]);
		System.out.printf("Threes       |   %2d  |  %2d  \n", playerScoreBoard[2], cpuScoreBoard[2]);
		System.out.printf("Fours        |   %2d  |  %2d  \n", playerScoreBoard[3], cpuScoreBoard[3]);
		System.out.printf("Fives        |   %2d  |  %2d  \n", playerScoreBoard[4], cpuScoreBoard[4]);
		System.out.printf("Sixes        |   %2d  |  %2d  \n", playerScoreBoard[5], cpuScoreBoard[5]);
		System.out.printf("Choice       |   %2d  |  %2d  \n", playerScoreBoard[6], cpuScoreBoard[6]);
		System.out.printf("4 of a Kind  |   %2d  |  %2d  \n", playerScoreBoard[7], cpuScoreBoard[7]);
		System.out.printf("Full House   |   %2d  |  %2d  \n", playerScoreBoard[8], cpuScoreBoard[8]);
		System.out.printf("S.Straight   |   %2d  |  %2d  \n", playerScoreBoard[9], cpuScoreBoard[9]);
		System.out.printf("L.Straight   |   %2d  |  %2d  \n", playerScoreBoard[10], cpuScoreBoard[10]);
		System.out.printf("Yacht        |   %2d  |  %2d  \n", playerScoreBoard[11], cpuScoreBoard[11]);
		System.out.printf("Bonus        |   %2d  |  %2d  \n", playerHasBonus? 35 : 0, cpuHasBonus? 35 : 0);
		System.out.printf("Total        |   %2d  |  %2d  \n", checkTotal(), checkCpuTotal());
		System.out.printf("--------------------------- \n");
	}

	// 에이스 턴 진행
	public static void playAcesTurn() {
		boolean isAcesEnd = false; // Aces 턴 종료 확인
		
		System.out.println();
		System.out.println();
		System.out.println("Aces 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while (isAcesEnd==false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// Aces 체크
				checkAces();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isAcesEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}
		}
		isAcesEnd = false;
	}
	
	// Aces 체크
	public static void checkAces() {
		int count = 0;
		final int ACE = 1;
		
		// 해당 배열에 있는 ACE 개수 검사
		for(int i=0; i<5; i++) {
			if(playerDiceArr[i] == ACE) {
				count += 1;
			}
		}	
		playerScoreBoard[0] = count*ACE;
	}
	
	// Deuces 턴 진행
	public static void playDeucesTurn() {
		boolean isDeucesEnd = false; // Deuces 턴 종료 확인

		System.out.println();
		System.out.println();
		System.out.println("Deuces 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isDeucesEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// Deuces 체크
				checkDeuces();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isDeucesEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}
		}
		isDeucesEnd = false;
	}
	
	// Deuces 체크
	public static void checkDeuces() {
		int count = 0;
		final int Deuces = 2;
		
		// 해당 배열에 있는 Deuces 개수 검사
		for(int i=0; i<5; i++) {
			if(playerDiceArr[i] == Deuces) {
				count += 1;
			}
		}	
		playerScoreBoard[1] = count*Deuces;
	}
	
	// Threes 턴 진행
	public static void playThreesTurn() {
		boolean isThreesEnd = false; // Threes 턴 종료 확인
		
		System.out.println();
		System.out.println();
		System.out.println("Threes 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isThreesEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// Threes 체크
				checkThrees();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isThreesEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}
		}
		isThreesEnd = false;
	}
	
	// Threes 체크
	public static void checkThrees() {
		int count = 0;
		final int THREES = 3;
		
		// 해당 배열에 있는 Threes 개수 검사
		for(int i=0; i<5; i++) {
			if(playerDiceArr[i] == THREES) {
				count += 1;
			}
		}	
		playerScoreBoard[2] = count * THREES;
	}
	// Fours 턴 진행
	public static void playFoursTurn() {
		boolean isFoursEnd = false; // Fours 턴 종료 확인
		
		System.out.println();
		System.out.println();
		System.out.println("Fours 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isFoursEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// Fours 체크
				checkFours();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isFoursEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}
		}
		isFoursEnd = false;
	}
	
	// Fours 체크
	public static void checkFours() {
		int count = 0;
		final int FOURS = 4;
		
		// 해당 배열에 있는 Threes 개수 검사
		for(int i=0; i<5; i++) {
			if(playerDiceArr[i] == FOURS) {
				count += 1;
			}
		}	
		playerScoreBoard[3] = count * FOURS;
	}
	
	// Fives 턴 진행
	public static void playFivesTurn() {
		boolean isFivesEnd = false; // Threes 턴 종료 확인
		System.out.println();
		System.out.println();
		System.out.println("Fives 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();

		while(isFivesEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// Fives 체크
				checkFives();
				//개행
				System.out.println();
				// 점수판 출력
				printBoard();
				isFivesEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}
		}
		isFivesEnd = false;
	}
	
	// Fives 체크
	public static void checkFives() {
		int count = 0;
		final int FIVES = 5;
		
		// 해당 배열에 있는 Threes 개수 검사
		for(int i=0; i<5; i++) {
			if(playerDiceArr[i] == FIVES) {
				count += 1;
			}
		}	
		playerScoreBoard[4] = count * FIVES;
	}
	// Sixes 턴 진행
	public static void playSixesTurn() {
		boolean isSixesEnd = false; // Sixes 턴 종료 확인
		System.out.println();
		System.out.println();
		System.out.println("Sixes 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isSixesEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// Sixes 체크
				checkThrees();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isSixesEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}	
		}
		isSixesEnd = false;
	}
	
	// Sixes 체크
	public static void checkSixes() {
		int count = 0;
		final int SIXES = 6;
		
		// 해당 배열에 있는 Sixes 개수 검사
		for(int i=0; i<5; i++) {
			if(playerDiceArr[i] == SIXES) {
				count += 1;
			}
		}	
		playerScoreBoard[5] = count * SIXES;
	}
	
	// 4 of a Kind 턴 진행
	public static void playFokTurn() {
		boolean isFokEnd = false; // 4 of a Kind 턴 종료 확인
		
		System.out.println();
		System.out.println();
		System.out.println("4 of a Kind 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isFokEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// 4 of a Kind 체크
				checkFok();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isFokEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}			
		}
		isFokEnd = false;
	}
	
	//  4 of a Kind 체크 
	public static void checkFok() {
		boolean hasQuad = false; // 4개인지 검사용
		int sum = 0; // 점수 총합
		
		// playerDiceArr값 복사
		copyCheckNumArr();
		// 카운트
		countCheckNumArr();
		
		// 4개인지 있는지 검사
		for(int i=0; i<5; i++) {
			if(countNumArr[i] >= 4) {
				hasQuad = true;
				System.out.println();
				System.out.println("★★★★★★★★★★★★★★! 4 OF A KIND !★★★★★★★★★★★★★");
				System.out.println();

				break;
			} else {
				hasQuad = false;
			}
		}
		
		if(hasQuad == true) {
			for(int i=0; i<5; i++) {
				sum += checkNumArr[i];
			}
		}
		
		// playerScoreBoard에 점수 넣기
		playerScoreBoard[7] = sum;
		initCountNumArr();
		initCheckNumArr();
	}
	
	// FullHouse 턴 진행
	public static void playFullHouseTurn() {
		boolean isFullHouseEnd = false; // FullHouse 턴 종료 확인
		System.out.println();
		System.out.println();
		System.out.println("FullHouse 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isFullHouseEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				System.out.println();
				// FullHouse 체크
				checkFullHouse();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isFullHouseEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}
		}
		isFullHouseEnd = false;
	}
	
	// FullHouse 체크 
	public static void checkFullHouse() {
		boolean hasTriple = false; // 트리플인지 검사용
		boolean hasPair = false; // 1페어인지 검사용 
		int sum = 0; // 점수 총합
		
		// playerDiceArr 값 복사
		copyCheckNumArr();
		// 카운트
		countCheckNumArr();
		
		// 트리플 있는지 검사
		for(int i=0; i<5; i++) {
			if(countNumArr[i] >= 3) {
				hasTriple = true;
				break;
			} else {
				hasTriple = false;
			}
		}
		
		for(int i=0; i<5; i++) {
			// 1페어인지 검사
			if(countNumArr[i] >= 2) {
				hasPair = true;
				break;
			} else {
				hasPair = false;
			}
		}
		
		// 트리플 + 1페어 이상일 경우 점수 총합 계산
		if(hasTriple == true && hasPair == true) {
			System.out.println();
			System.out.println("★★★★★★★★★★★★★★! FULL HOUSE !★★★★★★★★★★★★★");
			System.out.println();
			for(int i=0;i<5;i++) {
				sum += checkNumArr[i];
			}
		}
		
		// playerScoreBoard에 점수 넣기
		playerScoreBoard[8] = sum;
		initCountNumArr();
		initCheckNumArr();
	}
	
	// S.Straight 턴 진행
	public static void playSStraightTurn() {
		boolean isSStraightEnd = false; // S.Straight 턴 종료 확인
		System.out.println();
		System.out.println();
		System.out.println("S.Straight 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isSStraightEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// S.Straight 체크
//				checkSStraight();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isSStraightEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}
		}
		isSStraightEnd = false;
	}
	
	// S.Straight 체크 ★★★★★★★★★★★★★★수정필요★★★★★★★★★★★★★
//	public static void checkSStraight() {
//		
//		int sum = 0;
//		
//		for(int i=0;i <5; i++) {
//			sum += playerDiceArr[i];
//		}
//		playerScoreBoard[6] = sum;
//	}
	
	// L.Straight 턴 진행
	public static void playLStraightTurn() {
		boolean isLStraightEnd = false; // L.Straight 턴 종료 확인

		System.out.println();
		System.out.println();
		System.out.println("L.Straight 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isLStraightEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// L.Straight 체크
//				checkLStraight();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isLStraightEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}
		}
		isLStraightEnd = false;
	}
	
	// L.Straight 체크 ★★★★★★★★★★★★★★수정필요★★★★★★★★★★★★★
//	public static void checkLStraight() {
//		
//		int sum = 0;
//		
//		for(int i=0;i <5; i++) {
//			sum += playerDiceArr[i];
//		}
//		playerScoreBoard[6] = sum;
//	}
	
	// Yacht 턴 진행
	public static void playYachtTurn() {
		boolean isYachtEnd = false; // Yacht 턴 종료 확인
		
		System.out.println();
		System.out.println();
		System.out.println("Yacht 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isYachtEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// Yacht 체크
				checkYacht();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isYachtEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}
		}
		isYachtEnd = false;
	}
	
	// Yacht 체크 
	public static void checkYacht() {
		boolean hasYacht = false; // Yacht인지 검사용
		
		// playerDiceArr값 복사
		copyCheckNumArr();
		// 카운트
		countCheckNumArr();
		
		// Yacht인지 검사
		for(int i=0; i<5; i++) {
			if(countNumArr[i] == 5) {
				hasYacht = true;
				System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
				System.out.println("★★★★★★★★★★★★★★! YACHT !★★★★★★★★★★★★★");
				System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
				break;
			} else {
				hasYacht = false;
			}
		}
		
		// Yacht일 경우 점수 추가
		if(hasYacht == true) {
			playerScoreBoard[11] = 50;
		} else {
			playerScoreBoard[11] = 0;
		}
	}
	
	// Choice 턴 진행
	public static void playChoiceTurn() {
		boolean isChoiceEnd = false; // Sixes 턴 종료 확인
		
		System.out.println();
		System.out.println();
		System.out.println("Choice 턴입니다. 주사위를 굴리려면 1을 입력하세요.");
		System.out.println();
		System.out.println();
		
		while(isChoiceEnd == false) {
			int number = scanner.nextInt();
			if(number == 1) {
				// 주사위 굴리기
				rollDice();
				// Choices 합산
				sumChoices();
				//개행
				System.out.println();
				System.out.println();
				// 점수판 출력
				printBoard();
				isChoiceEnd = true;
			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 주사위를 굴리려면 1을 입력하세요.");
				System.out.println();
			}	
		}
		isChoiceEnd = false;
	}
	
	// Choices 체크 
	public static void sumChoices() {
		int sum = 0;
		
		for(int i=0;i <5; i++) {
			sum += playerDiceArr[i];
		}
		playerScoreBoard[6] = sum;
	}
	
	// 누가 이겼는지 체크용 메서드
	public static void checkWinner() {
		int playerScore = 0;
		int cpuScore = 0;
		
		for(int i=0; i<11; i++) {
			playerScore += playerScoreBoard[i];
			cpuScore += cpuScoreBoard[i];
		}
		
		// 플레이어가 이겼을 경우
		if (playerScore > cpuScore) {
			System.out.println();
			System.out.println();
			System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
			System.out.println("★★! 플레이어가 이겼습니다. 축하합니다. !★★");
			System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
			System.out.println();
			System.out.println();
			
		} else if (playerScore == cpuScore) { // 무승부일경우
			System.out.println();
			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("|    ! 무승부입니다. 아쉽습니다. !      |");
			System.out.println("---------------------------------");
			System.out.println();
			System.out.println();
		} else if (cpuScore > playerScore) {
			System.out.println();
			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("| ! 플레이어가 졌습니다. 아쉽습니다. !    |");
			System.out.println("---------------------------------");
			System.out.println();
			System.out.println();
			
		}
		
	}
	
	// 게임 한판 턴 진행
	public static void playGameOne() {
		// 플레이어 턴 진행
		playAcesTurn();
		playDeucesTurn();
		playThreesTurn();
		playFoursTurn();
		playFivesTurn();
		playSixesTurn();
		playChoiceTurn();
		playFokTurn();
		playFullHouseTurn();
		playSStraightTurn();
		playLStraightTurn();
		playYachtTurn();
		
		checkWinner();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("게임이 종료되었습니다.");
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	// 초기화 관련 메서드 
	public static void init() {
		// 플레이어, cpu 점수판 초기화 
		initScoreBoard();
		// Cpu 점수 할당
		insertCpuScore();
	}
	
	// 업데이트 관련 메서드
	public static void update() {
		// 게임 진행
		playGameOne();
	}
	
	public static void main(String[] args) {
		// 초기화 진행
		init();
		// 게임 진행
		update();
	}
}