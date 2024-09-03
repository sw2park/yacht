package yacht;

public class Render {
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
	
	// 점수판 출력 메서드
	public void renderScoreBoard() {
		System.out.printf("-----------점수판------------ \n");
		System.out.printf("Categories   |player|cpu     \n");
		System.out.printf("Aces         |   %2d  |  %2d  \n");
		System.out.printf("Deuces       |   %2d  |  %2d  \n");
		System.out.printf("Threes       |   %2d  |  %2d  \n");
		System.out.printf("Fours        |   %2d  |  %2d  \n");
		System.out.printf("Fives        |   %2d  |  %2d  \n");
		System.out.printf("Sixes        |   %2d  |  %2d  \n");
		System.out.printf("Choice       |   %2d  |  %2d  \n");
		System.out.printf("4 of a Kind  |   %2d  |  %2d  \n");
		System.out.printf("Full House   |   %2d  |  %2d  \n");
		System.out.printf("S.Straight   |   %2d  |  %2d  \n");
		System.out.printf("L.Straight   |   %2d  |  %2d  \n");
		System.out.printf("Yacht        |   %2d  |  %2d  \n");
		System.out.printf("Bonus        |   %2d  |  %2d  \n");
		System.out.printf("Total        |   %2d  |  %2d  \n");
		System.out.printf("--------------------------- \n");
	}
	
	public void renderPlayerWin() {
		System.out.println();
		System.out.println();
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		System.out.println("★★! 플레이어가 이겼습니다. 축하합니다. !★★");
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		System.out.println();
		System.out.println();
	}
	
	public void renderDraw() {
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("|    ! 무승부입니다. 아쉽습니다. !      |");
		System.out.println("---------------------------------");
		System.out.println();
		System.out.println();
	}
	
	public void renderPlayerLose() {
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("| ! 플레이어가 졌습니다. 아쉽습니다. !    |");
		System.out.println("---------------------------------");
		System.out.println();
		System.out.println();
	}
	
	public void renderGameOver() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("게임이 종료되었습니다.");
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	public void renderYacht() {
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		System.out.println("★★★★★★★★★★★★★★! YACHT !★★★★★★★★★★★★★");
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
	}
}
