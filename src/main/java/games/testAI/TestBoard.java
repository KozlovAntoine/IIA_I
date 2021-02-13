package games.testAI;

import java.util.ArrayList;

import iialib.games.model.IBoard;
import iialib.games.model.Score;

public class TestBoard implements IBoard< TestMove, TestRole, TestBoard>{

	/* Constantes */
	
	
	/* Valeurs prise par l'arbre de gauche a droite */
	private int[] leaf_values = /* A CHANGER  */{10,11,9,12,
												14,15,13,14,
												5,2,4,1,
												3,22,20,21
								/* A CHANGER  */};
	
	/* Attributs */
	
	/* Nombre de coup a joue restant (nombre de noeud a crée)*/
	private int nbplays;
	
	
	/* curseur des feuilles sur leaf_values */
	public static int cpt = 0;
	
	/* Constructeurs */
	
	public TestBoard() {
		nbplays = 3;
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
		return new TestBoard(getNbPlays() - 1);
	}

	@Override
	public boolean isValidMove(TestMove move, TestRole playerRole) {
		return true;
	}

	@Override
	public boolean isGameOver() {
		return true;
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
	
	public int[] getLeafValues() {
		return leaf_values;
	}

}
