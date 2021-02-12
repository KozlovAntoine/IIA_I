package games.otherGame;

import java.util.ArrayList;

import games.dominos.DominosRole;
import iialib.games.algs.IHeuristic;

public class OtherGameHeuristicsJeremy {
	
	public static final double COEF_2PIERRES = 1.0;
	
	public static final double COEF_3PIERRES = 5.0;
	
	public static final int COUP_CRITIQUE = 100;

	/*
	 * Heuristic pour jouer en haut index 6 - 11
	 * */
	public static IHeuristic<OtherGameBoard, OtherGameRole>  playerTop = (board,role,prof) -> {
		/* variables courrantes pour evaluer l'heuristic*/
		int[] current_board = board.getBoard();
		
		/* verifier si l'un des joueurs a perdu */
		if(board.isGameOver()) {
			if(board.getScoreJ2() >= board.getScoreJ1())
				return IHeuristic.MAX_VALUE - prof;
			else return IHeuristic.MIN_VALUE;
		}
		else {
			
			int score_heuristic = 0;
			// Stratégies de début de partie
			// On considere debut de partie si : aucun des joueurs n'a en dessous de 2 pierre par rond
			// ou creer une variable dans OtherGameBoard qui compte le nombre de tour
			boolean debutdepartie = true;
			
			
			// heuristic trou 3 ou moins
			int nb2PierreJ1 = 0;
			int nb3PierreJ1 = 0;
			int nb2PierreJ2 = 0;
			int nb3PierreJ2 = 0;
			
			
			for(int cpt = 0; cpt < current_board.length; cpt++) {
				if(cpt >= 6) { // Terrain du joueur 2
					if(current_board[cpt] == 2) {
						nb2PierreJ1++;
					}
					else if(current_board[cpt] == 3) {
						nb3PierreJ1++;
					}
				}
				else { // Terrain du joueur 1
					if(current_board[cpt] == 2) {
						nb2PierreJ2++;
					}
					else if(current_board[cpt] == 3) {
						nb3PierreJ2++;
					}
				}	
			}
			int resultpierre;
			if(role == OtherGameRole.J1) {
				resultpierre = (int) (nb2PierreJ2 * COEF_2PIERRES + nb3PierreJ2 * COEF_3PIERRES - nb2PierreJ1  * COEF_2PIERRES- nb3PierreJ1 * COEF_3PIERRES);  
				if(resultpierre > 0 && current_board[5] > 3)
					resultpierre *= 10 * current_board[5];
			}
			else {
				resultpierre = (int) (nb2PierreJ1 * COEF_2PIERRES + nb3PierreJ1 * COEF_3PIERRES - nb2PierreJ2  * COEF_2PIERRES- nb3PierreJ2 * COEF_3PIERRES);  
				if(resultpierre > 0 && current_board[11] > 3)
					resultpierre *= 2 * current_board[11];
			}
			score_heuristic += resultpierre;
			
			/* Prise en compte du score */
			
			if(role == OtherGameRole.J1) {
				score_heuristic += (board.getScoreJ1() - board.getScoreJ2()) * 20;
			}
			else {
				score_heuristic += (board.getScoreJ2() - board.getScoreJ1()) * 20;
			}
			
			
			// heuristic COUP_CRITIQUE
			return score_heuristic;
			
		}
		
		
		
		
		// Stratégies de milieu de partie
		
		// Stratégies de fin de partie
	};
	
	
	/*
	 * Heuristic pour jouer en bas index 0 - 5
	 * */
	public static IHeuristic<OtherGameBoard, OtherGameRole>  playerBottom = (board,role,prof) -> {
		int[] current_board = board.getBoard();
		if(board.isGameOver()) {
			if(board.getScoreJ1() >= board.getScoreJ2())
				return IHeuristic.MAX_VALUE - prof;
			else return IHeuristic.MIN_VALUE;
		}
		else {

			int score_heuristic = 0;
			// Stratégies de début de partie
			// On considere debut de partie si : aucun des joueurs n'a en dessous de 2 pierre par rond
			// ou creer une variable dans OtherGameBoard qui compte le nombre de tour
			boolean debutdepartie = true;
			
			
			// heuristic trou 3 ou moins
			int nb2PierreJ1 = 0;
			int nb3PierreJ1 = 0;
			int nb2PierreJ2 = 0;
			int nb3PierreJ2 = 0;
			
			int resultpierre;
			for(int cpt = 0; cpt < current_board.length; cpt++) {
				if(cpt >= 6) { // Terrain du joueur 2
					if(current_board[cpt] == 2) {
						nb2PierreJ1++;
					}
					else if(current_board[cpt] == 3) {
						nb3PierreJ1++;
					}
				}
				else { // Terrain du joueur 1
					if(current_board[cpt] == 2) {
						nb2PierreJ2++;
					}
					else if(current_board[cpt] == 3) {
						nb3PierreJ2++;
					}
				}
			}
			if(role == OtherGameRole.J1) {
				resultpierre = (int) (nb2PierreJ2 * COEF_2PIERRES + nb3PierreJ2 * COEF_3PIERRES - nb2PierreJ1  * COEF_2PIERRES- nb3PierreJ1 * COEF_3PIERRES);  
				if(resultpierre > 0 && current_board[5] > 3)
					resultpierre *= 10 * current_board[5];
			}
			else {
				resultpierre = (int) (nb2PierreJ1 * COEF_2PIERRES + nb3PierreJ1 * COEF_3PIERRES - nb2PierreJ2  * COEF_2PIERRES- nb3PierreJ2 * COEF_3PIERRES);  
				if(resultpierre > 0 && current_board[11] > 3)
					resultpierre *= 2 * current_board[11];
			}
			score_heuristic += resultpierre;
			
			/* Prise en compte du score */
			
			if(role == OtherGameRole.J1) {
				score_heuristic += (board.getScoreJ1() - board.getScoreJ2()) * 20;
			}
			else {
				score_heuristic += (board.getScoreJ2() - board.getScoreJ1()) * 20;
			}
			
			return score_heuristic;
		}
	};
	
}
