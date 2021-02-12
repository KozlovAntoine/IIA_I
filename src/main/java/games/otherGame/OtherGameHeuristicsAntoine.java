package games.otherGame;

import java.util.ArrayList;

import games.dominos.DominosRole;
import iialib.games.algs.IHeuristic;

public class OtherGameHeuristicsAntoine {
	
	public static final double COEF_SCORE = 1.0;

	/*
	 * Heuristic pour jouer en haut index 6 - 11
	 * */
	public static IHeuristic<OtherGameBoard, OtherGameRole>  playerTop = (board,role,prof) -> {
		/* variables courrantes pour evaluer l'heuristic*/
		
		/* verifier si l'un des joueurs a perdu */
		if(board.isGameOver()) {
			if(board.getScoreJ2() >= board.getScoreJ1())
				return IHeuristic.MAX_VALUE - prof;
			else return IHeuristic.MIN_VALUE;
		}
		else {
			// sinon stratégie de base : retourner le nombre de pierre du joueur  - nombre de pierre du joueur adv ET prendre en compte le score
			int score =  board.nbMoves(OtherGameRole.J2) - board.nbMoves(OtherGameRole.J1);
			if(role == OtherGameRole.J2)
				return score + (int)(board.getScoreJ2()* COEF_SCORE) ;
			else
				return score - (int) (board.getScoreJ1() * COEF_SCORE);
		}
		
		
		// Stratégies de début de partie
		// On considere debut de partie si : aucun des joueurs n'a en dessous de 2 pierre par rond
		// ou creer une variable dans OtherGameBoard qui compte le nombre de tour
		
		
		// Stratégies de milieu de partie
		
		// Stratégies de fin de partie
	};
	
	
	/*
	 * Heuristic pour jouer en bas index 0 - 5
	 * */
	public static IHeuristic<OtherGameBoard, OtherGameRole>  playerBottom = (board,role,prof) -> {
		if(board.isGameOver()) {
			if(board.getScoreJ1() >= board.getScoreJ2())
				return IHeuristic.MAX_VALUE - prof;
			else return IHeuristic.MIN_VALUE;
		}
		else {

			// sinon stratégie de base : retourner le nombre de pierre du joueur  - nombre de pierre du joueur adv
			int score =  board.nbMoves(OtherGameRole.J1) - board.nbMoves(OtherGameRole.J1);
			if(role == OtherGameRole.J1)
				return score + board.getScoreJ2();
			else
				return score - board.getScoreJ1();
		}
	};
	
}
