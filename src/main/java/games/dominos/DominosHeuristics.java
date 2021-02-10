package games.dominos;

import iialib.games.algs.IHeuristic;

public class DominosHeuristics {
	
	public static IHeuristic<DominosBoard,DominosRole>  hVertical = (board,role) -> {
		int verticaux = board.nbVerticalMoves();
		int horizontaux = board.nbHorizontalMoves();
		
		//System.out.println("[hVertical] Verticaux = " + verticaux + " | Horizontaux = " + horizontaux + " role "+role + "\n"+board.toString());
		if (verticaux == 0) {
			return IHeuristic.MIN_VALUE;
		}
		else if(horizontaux == 0) {
			return IHeuristic.MAX_VALUE;
		}
		return verticaux - horizontaux;
    };
    
	public static IHeuristic<DominosBoard,DominosRole> hHorizontal = (board,role) -> {
		int verticaux = board.nbVerticalMoves();
		int horizontaux = board.nbHorizontalMoves();
		//System.out.println("[hVertical] Verticaux = " + verticaux + " | Horizontaux = " + horizontaux + " role "+role + "\n"+board.toString());
		if (horizontaux == 0) {
			return IHeuristic.MIN_VALUE;
		}
		else if(verticaux == 0) {
			return IHeuristic.MAX_VALUE;
		} 
		return horizontaux - verticaux;
	};
   
}
	