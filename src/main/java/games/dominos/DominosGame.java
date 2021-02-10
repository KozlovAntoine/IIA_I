package games.dominos;

import java.util.ArrayList;

import iialib.games.algs.AIPlayer;
import iialib.games.algs.AbstractGame;
import iialib.games.algs.GameAlgorithm;
import iialib.games.algs.algorithms.AlphaBeta;
import iialib.games.algs.algorithms.MiniMax;

public class DominosGame extends AbstractGame<DominosMove, DominosRole, DominosBoard> {

	DominosGame(ArrayList<AIPlayer<DominosMove, DominosRole, DominosBoard>> players, DominosBoard board) {
		super(players, board);
	}

	public static void main(String[] args) {

		DominosRole roleV = DominosRole.VERTICAL;
		DominosRole roleH = DominosRole.HORIZONTAL;

		GameAlgorithm<DominosMove, DominosRole, DominosBoard> algV = new MiniMax<DominosMove, DominosRole, DominosBoard>(
				roleV, roleH, DominosHeuristics.hVertical, 2); // Minimax depth 4
		
		GameAlgorithm<DominosMove, DominosRole, DominosBoard> algH = new MiniMax<DominosMove, DominosRole, DominosBoard>(
				roleH, roleV, DominosHeuristics.hHorizontal, 2); // Minimax depth 4

		AIPlayer<DominosMove, DominosRole, DominosBoard> playerV = new AIPlayer<DominosMove, DominosRole, DominosBoard>(
				roleV, algV);

		AIPlayer<DominosMove, DominosRole, DominosBoard> playerH = new AIPlayer<DominosMove, DominosRole, DominosBoard>(
				roleH, algH);

		ArrayList<AIPlayer<DominosMove, DominosRole, DominosBoard>> players = new ArrayList<AIPlayer<DominosMove, DominosRole, DominosBoard>>();
		
		players.add(playerV); 
		players.add(playerH);

		// Setting the initial Board
		DominosBoard initialBoard = new DominosBoard();

		DominosGame game = new DominosGame(players, initialBoard);
		game.runGame();
	}

}