package games.testAI;

import java.util.ArrayList;

import iialib.games.model.IBoard;
import iialib.games.model.Score;

public class TestBoard implements IBoard< TestMove, TestRole, TestBoard>{

	/* Constantes */
	
	private int[] leaf_values = {1,2,3,4,
								5,6,7,8,
								9,10,11,12,
								13,14,15,16
								};
	
	/* Attributs */
	
	/* Nombre de coup a joue restant (nombre de noeud a crée)*/
	private int nbplays;
	
	public static int cpt = 0;
	
	/* Constructeurs */
	
	public TestBoard() {
		nbplays = 4;
	}
	
	public TestBoard(int nbplays) {
		this.nbplays = nbplays;
	}
	
	/* Permet de creer deux noeuds */
	@Override
	public ArrayList<TestMove> possibleMoves(TestRole playerRole) {
		ArrayList<TestMove> moves = new ArrayList<>();
		moves.add(new TestMove());
		moves.add(new TestMove());
		return moves;
	}

	/* Parcours de l'arbre */
	@Override
	public TestBoard play(TestMove move, TestRole playerRole) {
		if(getNbPlays() == 1) {
			cpt++;
		}
		return new TestBoard(getNbPlays() - 1);
	}

	@Override
	public boolean isValidMove(TestMove move, TestRole playerRole) {
		return true;
	}

	@Override
	public boolean isGameOver() {
		return cpt == 15;
	}

	@Override
	public ArrayList<Score<TestRole>> getScores() {
		ArrayList<Score<TestRole>> scores = new ArrayList<Score<TestRole>>();
		scores.add(new Score<TestRole>(TestRole.Ami,Score.Status.TIE,0));
		scores.add(new Score<TestRole>(TestRole.Adv,Score.Status.TIE,0));
		return scores;
	}
	
	/* getters and setters */
	
	
	public int getNbPlays() {
		return nbplays;
	}

}
