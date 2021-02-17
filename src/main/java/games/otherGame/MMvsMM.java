package games.otherGame;

import java.util.ArrayList;

import iialib.games.algs.AIPlayer;
import iialib.games.algs.AbstractGame;
import iialib.games.algs.GameAlgorithm;
import iialib.games.algs.algorithms.AlphaBeta;
import iialib.games.algs.algorithms.MiniMax;
import iialib.games.algs.algorithms.NegaMax;

public class MMvsMM extends AbstractGame<OtherGameMove, OtherGameRole, OtherGameBoard> {

	MMvsMM(ArrayList<AIPlayer<OtherGameMove, OtherGameRole, OtherGameBoard>> players, OtherGameBoard board) {
		super(players, board);
	}

	
	public static void main(String[] args) {
		
		OtherGameRole roleJ1 = OtherGameRole.J1;
		OtherGameRole roleJ2 = OtherGameRole.J2;

		GameAlgorithm<OtherGameMove, OtherGameRole, OtherGameBoard> algoJ1 = new NegaMax<OtherGameMove, OtherGameRole, OtherGameBoard>(
				roleJ1, roleJ2, OtherGameHeuristicsJeremy.playerBottom, 4); // Minimax depth 4

		GameAlgorithm<OtherGameMove, OtherGameRole, OtherGameBoard> algoJ2 = new NegaMax<OtherGameMove, OtherGameRole, OtherGameBoard>(
				roleJ2, roleJ1, OtherGameHeuristicsJeremy.playerTop, 4); // Minimax depth 4

		AIPlayer<OtherGameMove, OtherGameRole, OtherGameBoard> playerJ1 = new AIPlayer<OtherGameMove, OtherGameRole, OtherGameBoard>(
				roleJ1, algoJ1);

		AIPlayer<OtherGameMove, OtherGameRole, OtherGameBoard> playerJ2 = new AIPlayer<OtherGameMove, OtherGameRole, OtherGameBoard>(
				roleJ2, algoJ2);

		ArrayList<AIPlayer<OtherGameMove, OtherGameRole, OtherGameBoard>> players = new ArrayList<AIPlayer<OtherGameMove, OtherGameRole, OtherGameBoard>>();
		
		
		players.add(playerJ1);
		players.add(playerJ2); 
		
		// Setting the initial Board
		OtherGameBoard initialBoard = new OtherGameBoard();

		MMvsMM game = new MMvsMM(players, initialBoard);
		game.runGame();
		
	}
}
