package games.testAI;

import java.util.ArrayList;

import iialib.games.algs.AIPlayer;
import iialib.games.algs.AbstractGame;
import iialib.games.algs.GameAlgorithm;
import iialib.games.algs.algorithms.AlphaBeta;
import iialib.games.algs.algorithms.MiniMax;
import iialib.games.algs.algorithms.NegaMax;

/*
 * Classe pour tester les algos
 * Pour tester un algo : 
 *  - Changer l'algo de algoAmi
 *  - modifier les valeurs dans TestBoard : leaf_values (int[])
 *  - Evaluer le resultat obtenue.
 *  /!\ ne pas toucher la profondeur des arbres qui est definie a 4
 * */
public class Test extends AbstractGame<TestMove, TestRole, TestBoard> {

	Test(ArrayList<AIPlayer<TestMove, TestRole, TestBoard>> players, TestBoard board) {
		super(players, board);
	}
	
	public static void main(String[] args) {
		TestRole RoleAmi = TestRole.Ami;
		TestRole RoleAdv = TestRole.Adv;

		GameAlgorithm<TestMove, TestRole, TestBoard> algoAmi = new /* A CHANGER  */NegaMax/* A CHANGER  */<TestMove, TestRole, TestBoard>(
				RoleAmi, RoleAdv, TestHeuristic.feuille, 4);

		
		
		GameAlgorithm<TestMove, TestRole, TestBoard> algoAdv = new MiniMax<TestMove, TestRole, TestBoard>(
				RoleAmi, RoleAdv, TestHeuristic.feuille, 4);

		AIPlayer<TestMove, TestRole, TestBoard> playerAmi = new AIPlayer<TestMove, TestRole, TestBoard>(
				RoleAmi, algoAmi);

		AIPlayer<TestMove, TestRole, TestBoard> playerAdv = new AIPlayer<TestMove, TestRole, TestBoard>(
				RoleAmi, algoAdv);

		ArrayList<AIPlayer<TestMove, TestRole, TestBoard>> players = new ArrayList<AIPlayer<TestMove, TestRole, TestBoard>>();
		
		
		players.add(playerAmi);
		players.add(playerAdv); 
		
		// Setting the initial Board
		TestBoard initialBoard = new TestBoard();

		playerAmi.bestMove(initialBoard);
	}
	
}