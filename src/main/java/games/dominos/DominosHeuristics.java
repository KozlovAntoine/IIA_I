package games.dominos;

import iialib.games.algs.IHeuristic;

public class DominosHeuristics {
	
	public static IHeuristic<DominosBoard,DominosRole>  hVertical = (board,role,prof) -> {
		int verticaux = board.nbVerticalMoves();
		int horizontaux = board.nbHorizontalMoves();
		
		//System.out.println("[hVertical] Verticaux = " + verticaux + " | Horizontaux = " + horizontaux + " role "+role + "\n"+board.toString());
		
		if (role == DominosRole.HORIZONTAL && horizontaux == 0) {
			return IHeuristic.MAX_VALUE - prof;
		}
		else if(verticaux == 0) {
			return IHeuristic.MIN_VALUE;
		}
		return verticaux - horizontaux;
    };
    
	public static IHeuristic<DominosBoard,DominosRole> hHorizontal = (board,role,prof) -> {
		int verticaux = board.nbVerticalMoves();
		int horizontaux = board.nbHorizontalMoves();
		//System.out.println("[hHorizontal] Verticaux = " + verticaux + " | Horizontaux = " + horizontaux + " role "+role + "\n"+board.toString());
		if (role == DominosRole.VERTICAL && verticaux == 0) {
			return IHeuristic.MAX_VALUE - prof;
		}
		else if(horizontaux == 0) {
			return IHeuristic.MIN_VALUE;
		} 
		return horizontaux - verticaux;
	};
   
}
	