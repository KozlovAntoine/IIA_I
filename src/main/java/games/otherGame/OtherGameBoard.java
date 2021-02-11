package games.otherGame;

import iialib.games.model.IBoard;
import iialib.games.model.Player;
import iialib.games.model.Score;

import java.util.ArrayList;

import games.dominos.DominosRole;

public class OtherGameBoard implements IBoard<OtherGameMove, OtherGameRole, OtherGameBoard> {


	// ----------------------Constantes----------------------
	
	public static final int TAILLE = 12;

	// ---------------------- Attributes ---------------------
	// Attributes

	//TODO
	
	private int[] board;
	private int scoreJ1, scoreJ2;
	
	public OtherGameBoard() {
		board = new int[TAILLE];
		for(int i = 0 ; i < TAILLE ; i++) { //On initialise le board avec 4 graines dans chaque 'trous'
			board[i] = 4;
		}
		scoreJ1 = 0; scoreJ2 = 0;
	}
	
	public OtherGameBoard(OtherGameBoard other) {
		board = other.copyBoard();
		scoreJ1 = other.scoreJ1;
		scoreJ2 = other.scoreJ2;
	}
	
	public OtherGameBoard(int[] tab, int scoreJ1, int scoreJ2) {
		int[] tmp = new int[TAILLE];
		for(int i = 0 ; i < TAILLE ; i++) {
			tmp[i] = tab[i];
		}
		this.board = tmp;
		this.scoreJ1 = scoreJ1;
		this.scoreJ2 = scoreJ2;
	}
	
	private int[] copyBoard() {
		int[] tmp = new int[TAILLE];
		for(int i = 0 ; i < TAILLE ; i++) {
			tmp[i] = board[i];
		}
		return tmp;
	}
	
	// --------------------- IBoard Methods ---------------------

	@Override
	public ArrayList<OtherGameMove> possibleMoves(OtherGameRole playerRole) {
		ArrayList<OtherGameMove> allMoves = new ArrayList<>();
		/*
		JOUEUR 1
		*/
		if(playerRole == OtherGameRole.J1) {//tous les coups de J1 se situe pour un move.x entre 0 et 5
			if(grainesBoardJ2() == 0) {//Si le J2 est en famine on doit OBLIGATOIREMENT jouer un coup qui lui donne une graine
				int index_arrive = 6;
				for(int i = 0 ; i < TAILLE / 2 ; i++) {
					if(board[i] >= index_arrive - i) {
						allMoves.add(new OtherGameMove(i));
					}
				}
			} 
			else { //Tous les autres coups
				for(int i = 0 ; i < TAILLE / 2 ; i++) {
					if(board[i] > 0) {
						allMoves.add(new OtherGameMove(i));
					}
				}
			}
		} 
		/*
		JOUEUR 2
		*/
		else {//tous les coups de J2 se situe pour un move.x entre 6 et 11
			if(grainesBoardJ1() == 0) {//Si le J1 est en famine on doit OBLIGATOIREMENT jouer un coup qui lui donne une graine
				int index_arrive = 12;
				for(int i = TAILLE / 2 ; i < TAILLE ; i++) {
					if(board[i] >= index_arrive - i) {
						allMoves.add(new OtherGameMove(i));
					}
				}
			} 
			else { //Tous les autres coups
				for(int i = TAILLE / 2 ; i < TAILLE ; i++) {
					if(board[i] > 0) {
						allMoves.add(new OtherGameMove(i));
					}
				}
			}
		}
		return allMoves;
	}

	@Override
	public OtherGameBoard play(OtherGameMove move, OtherGameRole playerRole) {
		int[] newBoard = copyBoard();
		int graines = newBoard[move.x];
		int index = move.x;
		final int index_depart = move.x;
		newBoard[index_depart] = 0;
		while(graines > 0) {
			if(index != index_depart) {
				newBoard[index] += 1;
				graines --;
			}
			if(graines > 0) //quand il n'y a plus de graines on s'arrete
				index++;
			if(index == TAILLE)
				index = 0;
		}
		// Tour de j1
		if(playerRole == OtherGameRole.J1 && peutEnleverSurJ2()) {
			//entre 11 et 6 on peut enlever les graines
			while(index >= TAILLE / 2 && (newBoard[index] == 2 || newBoard[index] == 3)) {
				scoreJ1 += newBoard[index];
				newBoard[index] = 0;
				index--;
			}
		}
		// Tour de j2
		else if(playerRole == OtherGameRole.J2 && peutEnleverSurJ1()) {
			while(index < TAILLE / 2 && index >= 0 && (newBoard[index] == 2 || newBoard[index] == 3)) {
				scoreJ2 += newBoard[index];
				newBoard[index] = 0;
				index--;
			}
		}
		
		return new OtherGameBoard(newBoard, scoreJ1, scoreJ2);
	}

	@Override
	public boolean isValidMove(OtherGameMove move, OtherGameRole playerRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGameOver() {
		if(grainesRestantes() <= 6 || scoreJ1 >= 25 || scoreJ2 >= 25) //La fin par défaut
			return true;
		else if(grainesBoardJ1() == 0) {//Si le joueur 1 est en famine alors on ajoute le reste des graines au J2
			scoreJ2 += grainesBoardJ2();
			return true;
		}
		else if(grainesBoardJ2() == 0) {//Si le joueur 2 est en famine alors on ajoute le reste des graines au J1
			scoreJ1 += grainesBoardJ1();
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Score<OtherGameRole>> getScores() {
		ArrayList<Score<OtherGameRole>> scores = new ArrayList<Score<OtherGameRole>>();
		if(this.isGameOver()) {
			if (scoreJ1 == scoreJ2) { //Egalité
				scores.add(new Score<OtherGameRole>(OtherGameRole.J1,Score.Status.TIE,scoreJ1));
				scores.add(new Score<OtherGameRole>(OtherGameRole.J2,Score.Status.TIE,scoreJ2));
			}
			else if(scoreJ1 > scoreJ2){ //J1 gagne
				scores.add(new Score<OtherGameRole>(OtherGameRole.J1,Score.Status.WIN,scoreJ1));
				scores.add(new Score<OtherGameRole>(OtherGameRole.J2,Score.Status.LOOSE,scoreJ2));
			}
			else { //J2 gagne
				scores.add(new Score<OtherGameRole>(OtherGameRole.J1,Score.Status.LOOSE,scoreJ1));
				scores.add(new Score<OtherGameRole>(OtherGameRole.J2,Score.Status.WIN,scoreJ2));
			}
		}
		else {
			
		}
		return scores;
	}

	// --------------------- Other Methods ---------------------

	/**
	 * Nombre de graines restant sur le board
	 * @return
	 */
	private int grainesRestantes() {
		return grainesBoardJ1() + grainesBoardJ2();
	}
	
	/**
	 * Nombre de graines restant du côté du joueur 1
	 * @return
	 */
	private int grainesBoardJ1() {
		int total = 0;
		for(int i = 0 ; i < TAILLE / 2; i++) {
			total += board[i];
		}
		return total;
	}
	
	/**
	 * Nombre de graines restant du côté du joueur 2
	 * @return
	 */
	private int grainesBoardJ2() {
		int total = 0;
		for(int i = TAILLE / 2 ; i < TAILLE ; i++) {
			total += board[i];
		}
		return total;
	}
	
	//Si le J2 joue un coup qui peut enlever toutes les graines de J1 alors ce coup peut etre jouer mais les graines ne sont pas enlevé
	private boolean peutEnleverSurJ1() {
		int i = 0;
		while (i < TAILLE / 2) {
			if(board[i] < 2 || board[i] > 3)
				return true;
			i++;
		}
		return false;
	}
	
	//Si le J1 joue un coup qui peut enlever toutes les graines de J2 alors ce coup peut etre jouer mais les graines ne sont pas enlevé
	private boolean peutEnleverSurJ2() {
		int i = TAILLE / 2;
		while (i < TAILLE) {
			if(board[i] < 2 || board[i] > 3)
				return true;
			i++;
		}
		return false;
	}
	
	/* getters and setters */
	
	public int[] getBoard() {
		return board;
	}
	
	public int getScoreJ1() {
		return scoreJ1;
	}
	
	public int getScoreJ2() {
		return scoreJ2;
	}
	
	
	public int nbMoves(OtherGameRole role) {
		return possibleMoves(role).size();
	}
	
	@Override
	public String toString() {
		String res = "J2 : ";
		for(int cpt = TAILLE - 1 ; cpt >= TAILLE / 2; cpt--) {
			res += "|";
			if(board[cpt] <= 9) { // si nombre avec un chiffre
				res += " ";
			}
			if(cpt != (OtherGameBoard.TAILLE / 2))
				res += board[cpt];
			else 
				res += board[cpt] + "|";
		}
		res += "\nJ1 : ";
		for(int cpt = 0; cpt < OtherGameBoard.TAILLE / 2 ; cpt++) {
			res += "|";
			if(board[cpt] < 9) { // si nombre avec un chiffre
				res += " ";
			}
			if(cpt != (OtherGameBoard.TAILLE / 2) - 1) 
				res += board[cpt];
			else
				res += board[cpt] + "|";
		}
		return res + "\n Score J1 = " + scoreJ1 + " | J2 = " + scoreJ2;
	}
}
