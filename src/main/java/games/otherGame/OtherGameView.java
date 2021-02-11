package games.otherGame;


/*
 * Vue du jeu sur le terminal
 * */
public class OtherGameView {

	/* Constantes */
	
	/* attributs */
	private OtherGameBoard modele;
	
	/* constructeur */
	
	public OtherGameView(OtherGameBoard modele) {
		this.modele = modele;
	}
	
	public void showGameBoard() {
		showScoreJ2();
		showBoard();
		showScoreJ1();
	}
	
	public void showBoard() {
		int[] board = modele.getBoard();
		System.out.println("-------------------");
		for(int cpt = 0; cpt < OtherGameBoard.TAILLE / 2 ; cpt++) {
			System.out.print("|");
			if(board[cpt] < 9) { // si nombre avec un chiffre
				System.out.print(" ");
			}
			if(cpt != (OtherGameBoard.TAILLE / 2) - 1) 
				System.out.print(board[cpt]);
			else
				System.out.println(board[cpt] + "|");
		}
		System.out.println("-------------------");
		for(int cpt = OtherGameBoard.TAILLE / 2 ; cpt < OtherGameBoard.TAILLE; cpt++) {
			System.out.print("|");
			if(board[cpt] < 9) { // si nombre avec un chiffre
				System.out.print(" ");
			}
			if(cpt != (OtherGameBoard.TAILLE - 1))
				System.out.print(board[cpt]);
			else 
				System.out.println(board[cpt] + "|");
		}
		System.out.println("-------------------");
	}
	
	public void showScoreJ1() {
		System.out.println("Score J1 : " + modele.getScoreJ1());
	}
	
	public void showScoreJ2() {
		System.out.println("Score J2 : " + modele.getScoreJ2());
	}
	
	
}
