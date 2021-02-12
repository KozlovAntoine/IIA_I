package games.testAI;

import java.util.ArrayList;

import games.otherGame.OtherGameBoard;
import games.otherGame.OtherGameMove;
import games.otherGame.OtherGameRole;
import iialib.games.algs.AIPlayer;
import iialib.games.algs.AbstractGame;

/*
 * Classe pour tester les algos
 * */
public class Test extends AbstractGame<OtherGameMove, OtherGameRole, OtherGameBoard> {

	Test(ArrayList<AIPlayer<OtherGameMove, OtherGameRole, OtherGameBoard>> players, OtherGameBoard board) {
		super(players, board);
	}
	
	
}