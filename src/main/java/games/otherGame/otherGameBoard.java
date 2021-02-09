package games.otherGame;

import iialib.games.model.IBoard;
import iialib.games.model.Player;
import iialib.games.model.Score;

import java.util.ArrayList;

public class otherGameBoard implements IBoard<otherGameMove, otherGameRole, otherGameBoard> {



	// ---------------------- Attributes ---------------------
	// Attributes

	//TODO
	private final int TAILLE = 12;
	private int[] board;
	
	public otherGameBoard() {
		board = new int[TAILLE];
		for(int i = 0 ; i < TAILLE ; i++) {
			board[i] = 4;
		}
	}
	
	// --------------------- IBoard Methods ---------------------

	@Override
	public ArrayList<otherGameMove> possibleMoves(otherGameRole playerRole) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Score<otherGameRole>> getScores() {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------------- Other Methods ---------------------


	
}
