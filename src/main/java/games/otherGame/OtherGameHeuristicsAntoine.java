package games.otherGame;

import java.util.ArrayList;

import games.dominos.DominosRole;
import iialib.games.algs.IHeuristic;

public class OtherGameHeuristicsAntoine {
	
	private static final double COEF_SCORE_MAX_GAGNER = 2;
	private static final double COEF_SCORE_MAX_PERDRE = 4;

	/*
	 * Heuristic pour jouer en haut index 6 - 11
	 * */
	/**
	 * Joueur 2 qui joue
	 */
	public static IHeuristic<OtherGameBoard, OtherGameRole>  playerTop = (board,role,prof) -> {
		if(board.isGameOver()) {
			if(board.getScoreJ2() >= board.getScoreJ1())
				return IHeuristic.MAX_VALUE - prof;
			else return IHeuristic.MIN_VALUE;
		}
		else {
			int scorePris = (int) (board.getLastScoreJ2() * COEF_SCORE_MAX_GAGNER);
			int scoreAPrendre = board.getMaxPointJ2();
			int maxPointsLaisser = (int) (board.getMaxPointPossibleJ1() * COEF_SCORE_MAX_PERDRE);
			int scoreALaisser = board.getMaxPointJ1();
			return scorePris + scoreAPrendre - maxPointsLaisser - scoreALaisser;
		}
	};
	
	
	/*
	 * Heuristic pour jouer en bas index 0 - 5
	 * */
	/**
	 * Joueur 1
	 */
	public static IHeuristic<OtherGameBoard, OtherGameRole>  playerBottom = (board,role,prof) -> {
		if(board.isGameOver()) {
			if(board.getScoreJ1() >= board.getScoreJ2())
				return IHeuristic.MAX_VALUE - prof;
			else return IHeuristic.MIN_VALUE;
		}
		else {
			int scorePris = (int) (board.getLastScoreJ1() * COEF_SCORE_MAX_GAGNER);
			int scoreAPrendre = board.getMaxPointJ1();
			int maxPointsLaisser = (int) (board.getMaxPointPossibleJ2() * COEF_SCORE_MAX_PERDRE);
			int scoreALaisser = board.getMaxPointJ2();
			return scorePris + scoreAPrendre - maxPointsLaisser - scoreALaisser;
		}
	};
	
}
