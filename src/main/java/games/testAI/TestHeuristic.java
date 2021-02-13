package games.testAI;

import iialib.games.algs.IHeuristic;

public class TestHeuristic {
	
	public static IHeuristic<TestBoard, TestRole>  feuille = (board,role,prof) -> {
		
		int[] leafvalues = board.getLeafValues();
		int res = leafvalues[board.cpt];
		
		System.out.println("Feuille : " + board.cpt + " valeur : "+res);
		board.cpt += 1;
		return res;
	};

	
}
