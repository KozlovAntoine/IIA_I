package games.otherGame;

import iialib.games.model.IBoard;
import iialib.games.model.Player;
import iialib.games.model.Score;

import java.util.ArrayList;

import games.dominos.DominosRole;

public class otherGameBoard implements IBoard<otherGameMove, otherGameRole, otherGameBoard> {



	// ---------------------- Attributes ---------------------
	// Attributes

	//TODO
	private final int TAILLE = 12;
	private int[] board;
	private int scoreJ1, scoreJ2;
	
	public otherGameBoard() {
		board = new int[TAILLE];
		for(int i = 0 ; i < TAILLE ; i++) { //On initialise le board avec 4 graines dans chaque 'trous'
			board[i] = 4;
		}
		scoreJ1 = 0; scoreJ2 = 0;
	}
	
	public otherGameBoard(otherGameBoard other) {
		board = other.copyBoard();
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
	public ArrayList<otherGameMove> possibleMoves(otherGameRole playerRole) {
		ArrayList<otherGameMove> allMoves = new ArrayList<>();
		if(playerRole == otherGameRole.J1) {
			
		} else {
			
		}
		return allMoves;
	}

	@Override
	public otherGameBoard play(otherGameMove move, otherGameRole playerRole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidMove(otherGameMove move, otherGameRole playerRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGameOver() {
		if(grainesRestantes() <= 6 || scoreJ1 >= 25 || scoreJ2 >= 25) //La fin par défaut
			return true;
		else if(grainesBoardJ1() == 0) {//Si le joueur 1 est en famine alors on ajoute le reste des graines au J2
			scoreJ2 += grainesRestantes();
			return true;
		}
		else if(grainesBoardJ2() == 0) {//Si le joueur 2 est en famine alors on ajoute le reste des graines au J1
			scoreJ1 += grainesRestantes();
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Score<otherGameRole>> getScores() {
		ArrayList<Score<otherGameRole>> scores = new ArrayList<Score<otherGameRole>>();
		if(this.isGameOver()) {
			if (scoreJ1 == scoreJ2) { //Egalité
				scores.add(new Score<otherGameRole>(otherGameRole.J1,Score.Status.TIE,scoreJ1));
				scores.add(new Score<otherGameRole>(otherGameRole.J2,Score.Status.TIE,scoreJ2));
			}
			else if(scoreJ1 > scoreJ2){ //J1 gagne
				scores.add(new Score<otherGameRole>(otherGameRole.J1,Score.Status.WIN,scoreJ1));
				scores.add(new Score<otherGameRole>(otherGameRole.J2,Score.Status.LOOSE,scoreJ2));
			}
			else { //J2 gagne
				scores.add(new Score<otherGameRole>(otherGameRole.J1,Score.Status.LOOSE,scoreJ1));
				scores.add(new Score<otherGameRole>(otherGameRole.J2,Score.Status.WIN,scoreJ2));
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
	
}
