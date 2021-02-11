package iialib.games.algs.algorithms;

import java.util.ArrayList;

import games.dominos.DominosBoard;
import games.dominos.DominosMove;
import games.dominos.DominosRole;
import iialib.games.algs.GameAlgorithm;
import iialib.games.algs.IHeuristic;
import iialib.games.model.IBoard;
import iialib.games.model.IMove;
import iialib.games.model.IRole;

public class MiniMax<Move extends IMove,Role extends IRole,Board extends IBoard<Move,Role,Board>> implements GameAlgorithm<Move,Role,Board> {

	// Constants
	/** Defaut value for depth limit 
     */
	private final static int DEPTH_MAX_DEFAUT = 4;

	// Attributes
	/** Role of the max player 
     */
	private final Role playerMaxRole;

	/** Role of the min player 
     */
	private final Role playerMinRole;

	/** Algorithm max depth
     */
	private int depthMax = DEPTH_MAX_DEFAUT;

	
	/** Heuristic used by the max player 
     */
	private IHeuristic<Board, Role> h;

	//
	/** number of internal visited (developed) nodes (for stats)
     */
	private int nbNodes;
	
	/** number of leaves nodes nodes (for stats)

     */
	private int nbLeaves;

	// --------- Constructors ---------

	public MiniMax(Role playerMaxRole, Role playerMinRole, IHeuristic<Board, Role> h) {
		this.playerMaxRole = playerMaxRole;
		this.playerMinRole = playerMinRole;
		this.h = h;
	}

	//
	public MiniMax(Role playerMaxRole, Role playerMinRole, IHeuristic<Board, Role> h, int depthMax) {
		this(playerMaxRole, playerMinRole, h);
		this.depthMax = depthMax;
	}

	/*
	 * IAlgo METHODS =============
	 */

	@Override
	public Move bestMove(Board board, Role playerRole) {
		Move bestMove = board.possibleMoves(playerRole).get(0);
		int max = IHeuristic.MIN_VALUE;
		long start = System.currentTimeMillis();
		ArrayList<Move> allMoves = board.possibleMoves(playerMaxRole);
		for(Move move : allMoves) {
			int tmp = minMax(board.play(move, playerMaxRole), playerMinRole, 1);
			//System.out.println("Heuristic = " + tmp);
			if(tmp > max) {
				max = tmp;
				bestMove = move;
			}
		}
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("[MiniMax] Temps de calcul : " + (System.currentTimeMillis() - start) + " ms");
		System.out.println("[MiniMax] Nombre de noeuds : " + (nbNodes * 1.0 /1000000.0) + " millions");
		System.out.println("[MiniMax] H = " + max);
		System.out.println("-----------------------------------------------------------------------------");
		return bestMove;
	}
	
	
	

	/*
	 * PUBLIC METHODS ==============
	 */

	public String toString() {
		return "MiniMax(ProfMax=" + depthMax + ")";
	}

	/*
	 * PRIVATE METHODS ===============
	 */
	
	
	
	private int maxMin(Board board , Role playerRole, int prof) {
		ArrayList<Move> moves = board.possibleMoves(playerRole);
		nbNodes += moves.size();
		if(prof == depthMax || estFeuille(moves)) {
			int tmp = h.eval(board,(Role) playerMaxRole, prof);
			//System.out.println("maxMin renvoie = " + tmp);
			return tmp;
		}
		else {
			int max = IHeuristic.MIN_VALUE;
			int tmp;
			for(Move move : moves) {
				tmp = minMax(board.play(move, playerRole),
						playerMinRole, prof + 1);
				if(tmp > max){
					max = tmp;
				}
			}
			return max;
		}
	}
	
	
	private int minMax(Board board , Role playerRole , int prof) {
		ArrayList<Move> moves = board.possibleMoves(playerRole);
		nbNodes += moves.size();
		if(prof == depthMax || estFeuille(moves)) {
			int tmp = h.eval(board,(Role) playerMinRole, prof);
			//System.out.println("minMax renvoie = " + tmp);
			return tmp;
		}
		else {
			int min = IHeuristic.MAX_VALUE;
			int tmp;
			for(Move move : moves) {
				tmp = maxMin(board.play(move, playerRole) ,
						playerMaxRole , prof + 1);
				if(tmp < min) {
					min = tmp;
				}
			}
			return min;
		}
	}
	
	
	private int max_successeur(int a, int b) {
		return a > b ? a : b;
	}
	
	private int min_successeur(int a, int b) {
		return a < b ? a : b;
	}
	
	private boolean estFeuille(ArrayList<Move> moves) {
		return moves.size() == 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//TODO
}
