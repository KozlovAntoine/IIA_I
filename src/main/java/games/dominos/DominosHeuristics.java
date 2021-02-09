package games.dominos;

import iialib.games.algs.IHeuristic;

public class DominosHeuristics {
	
	public static IHeuristic<DominosBoard,DominosRole>  hVertical = (board,role) -> {
		int verticaux = board.nbVerticalMoves();
		int horizontal = board.nbHorizontalMoves();
		
		if(horizontal == 0) {
			return IHeuristic.MAX_VALUE;
		}
		else if (verticaux == 0) {
			return IHeuristic.MIN_VALUE;
		}
		return horizontal - verticaux;
    };
    
	public static IHeuristic<DominosBoard,DominosRole> hHorizontal = (board,role) -> {
		int verticaux = board.nbVerticalMoves();
		int horizontal = board.nbHorizontalMoves();
		
		if(verticaux == 0) {
			return IHeuristic.MAX_VALUE;
		}
		else if (horizontal == 0) {
			return IHeuristic.MIN_VALUE;
		}
		return verticaux - horizontal;
	};
   
}
	