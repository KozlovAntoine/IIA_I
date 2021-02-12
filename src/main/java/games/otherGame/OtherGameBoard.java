package games.otherGame;

import iialib.games.model.IBoard;
import iialib.games.model.Player;
import iialib.games.model.Score;

import java.util.ArrayList;

import games.dominos.DominosRole;

public class OtherGameBoard implements IBoard<OtherGameMove, OtherGameRole, OtherGameBoard> {


	// ----------------------Constantes----------------------
	
	public static final int TAILLE = 12;

	// ---------------------- Attributes ---------------------
	// Attributes

	//TODO
	
	private int[] board;
	private int scoreJ1;
	private int scoreJ2;
	private int lastAddedScoreJ1;
	private int lastAddedScoreJ2;
	
	public OtherGameBoard() {
		board = new int[TAILLE];
		for(int i = 0 ; i < TAILLE ; i++) { //On initialise le board avec 4 graines dans chaque 'trous'
			board[i] = 4;
		}
		this.scoreJ1 = 0; this.scoreJ2 = 0;
		this.lastAddedScoreJ1 = 0;
		this.lastAddedScoreJ2 = 0;
	}
	
	public OtherGameBoard(OtherGameBoard other) {
		this.board = other.copyBoard();
		this.scoreJ1 = other.scoreJ1;
		this.scoreJ2 = other.scoreJ2;
		this.lastAddedScoreJ1 = other.lastAddedScoreJ1;
		this.lastAddedScoreJ2 = other.lastAddedScoreJ2;
	}
	
	private int[] copyBoard() {
		int[] tmp = new int[TAILLE];
		for(int i = 0 ; i < TAILLE ; i++) {
			tmp[i] = board[i];
		}
		return tmp;
	}
	
	// --------------------- IBoard Methods ---------------------

	@Override
	public ArrayList<OtherGameMove> possibleMoves(OtherGameRole playerRole) {
		ArrayList<OtherGameMove> allMoves = new ArrayList<>();
		/*
		JOUEUR 1
		*/
		if(playerRole == OtherGameRole.J1) {//tous les coups de J1 se situe pour un move.x entre 0 et 5
			if(grainesBoardJ2() == 0) {//Si le J2 est en famine on doit OBLIGATOIREMENT jouer un coup qui lui donne une graine
				int index_arrive = 6;
				for(int i = 0 ; i < TAILLE / 2 ; i++) {
					if(board[i] >= index_arrive - i) {
						allMoves.add(new OtherGameMove(i));
					}
				}
			} 
			else { //Tous les autres coups
				for(int i = 0 ; i < TAILLE / 2 ; i++) {
					if(board[i] > 0) {
						allMoves.add(new OtherGameMove(i));
					}
				}
			}
		} 
		/*
		JOUEUR 2
		*/
		else {//tous les coups de J2 se situe pour un move.x entre 6 et 11
			if(grainesBoardJ1() == 0) {//Si le J1 est en famine on doit OBLIGATOIREMENT jouer un coup qui lui donne une graine
				int index_arrive = 12;
				for(int i = TAILLE / 2 ; i < TAILLE ; i++) {
					if(board[i] >= index_arrive - i) {
						allMoves.add(new OtherGameMove(i));
					}
				}
			} 
			else { //Tous les autres coups
				for(int i = TAILLE / 2 ; i < TAILLE ; i++) {
					if(board[i] > 0) {
						allMoves.add(new OtherGameMove(i));
					}
				}
			}
		}
		return allMoves;
	}

	@Override
	public OtherGameBoard play(OtherGameMove move, OtherGameRole playerRole) {
		/**
		 * Création d'un nouveau jeu qui est une copie du celui actuel
		 */
		OtherGameBoard newBoard = new OtherGameBoard(this);
		
		/**
		 * On recupere le nombre de graine a la case x
		 * On garde l'index de depart pour sauter la case si on repasse dessus
		 * On vide la case a l'index de depart
		 */
		int graines = newBoard.board[move.x];
		final int index_depart = move.x;
		int index = index_depart;
		newBoard.board[index_depart] = 0;
		
		/**
		 * Tant qu'on a des graines, on avance d'une case et on ajoute une graine dans la case
		 */
		while(graines > 0) {
			if(index != index_depart) {
				newBoard.board[index] += 1;
				graines --;
			}
			if(graines > 0) //quand il n'y a plus de graines on s'arrete et on garde l'index
				index++;
			if(index == TAILLE) //l'index revient au départ s'il dépasse
				index = 0;
		}
		
		/*
		 * Si le Joueur 1 joue
		 * On peut enlever des graines si le coup n'enleve pas toutes les graines adverses
		 */
		if(playerRole == OtherGameRole.J1 && peutEnleverSurJ2(index)) {
			/*
			 * Tant qu'on reste dans la partie adverse on peut enlever les graines
			 * des cases si celle ci à 2 ou 3 graines dessus
			 */
			while(index >= TAILLE / 2 && (newBoard.board[index] == 2 || newBoard.board[index] == 3)) {
				newBoard.scoreJ1 += newBoard.board[index];
				newBoard.lastAddedScoreJ1 = newBoard.board[index];
				newBoard.board[index] = 0;
				index--;
			}
		}
		/*
		 * Si le Joueur 2 joue
		 * On peut enlever des graines si le coup n'enleve pas toutes les graines adverses
		 */
		else if(playerRole == OtherGameRole.J2 && peutEnleverSurJ1(index)) {
			/*
			 * Tant qu'on reste dans la partie adverse on peut enlever les graines
			 * des cases si celle ci à 2 ou 3 graines dessus
			 */
			while(index < TAILLE / 2 && index >= 0 && (newBoard.board[index] == 2 || newBoard.board[index] == 3)) {
				newBoard.scoreJ2 += newBoard.board[index];
				newBoard.lastAddedScoreJ2 = newBoard.board[index];
				newBoard.board[index] = 0;
				index--;
			}
		}
		return newBoard;
	}

	@Override
	public boolean isValidMove(OtherGameMove move, OtherGameRole playerRole) {
		/*
		 * Un coup est jouable s'il y a des graines dans la case X et
		 * doit nourrir le joueur adverse s'il n'a plus de graine dans son camp
		 */
		
		/**
		 * Joueur 1
		 */
		if(playerRole == OtherGameRole.J1 && move.x >= 0 && move.x < TAILLE / 2 && board[move.x] > 0) {
			if(grainesBoardJ2() > 0) { //coup normal | J1 a des graines
				return true;
			} else { //nourrir le J2 obligatoire
				final int index_arrive = 6;
				if(board[move.x] >= index_arrive - move.x) { //ce coup peut nourrir J2 | J2 n'a pas de graines
					return true;
				} 
				else return false;
			}
		}
		
		/**
		 * Joueur 2
		 */
		else if(playerRole == OtherGameRole.J2 && move.x >= TAILLE / 2 && move.x < TAILLE && board[move.x] > 0) {
			if(grainesBoardJ1() > 0) { //coup normal | J1 a des graines
				return true;
			} else { //nourrir le J1 obligatoire
				final int index_arrive = 12;
				if(board[move.x] >= index_arrive - move.x) { //ce coup peut nourrir J1 | J1 n'a pas de graines
					return true;
				} 
				else return false;
			}
		}
		return false;
	}

	@Override
	public boolean isGameOver() {
		if(grainesRestantes() <= 6 || scoreJ1 >= 25 || scoreJ2 >= 25) //La fin par défaut
			return true;
		else if(grainesBoardJ1() == 0 && !J2peutNourrirJ1()) {//Si le joueur 1 est en famine alors on ajoute le reste des graines au J2 s'il ne peut pas le nourir
			this.scoreJ2 += grainesBoardJ2();
			return true;
		}
		else if(grainesBoardJ2() == 0 && !J1peutNourrirJ2()) {//Si le joueur 2 est en famine alors on ajoute le reste des graines au J1 s'il ne peut pas le nourir
			scoreJ1 += grainesBoardJ1();
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Score<OtherGameRole>> getScores() {
		ArrayList<Score<OtherGameRole>> scores = new ArrayList<Score<OtherGameRole>>();
		if(this.isGameOver()) {
			if (scoreJ1 == scoreJ2) { //Egalité
				scores.add(new Score<OtherGameRole>(OtherGameRole.J1,Score.Status.TIE,scoreJ1));
				scores.add(new Score<OtherGameRole>(OtherGameRole.J2,Score.Status.TIE,scoreJ2));
			}
			else if(scoreJ1 > scoreJ2){ //J1 gagne
				scores.add(new Score<OtherGameRole>(OtherGameRole.J1,Score.Status.WIN,scoreJ1));
				scores.add(new Score<OtherGameRole>(OtherGameRole.J2,Score.Status.LOOSE,scoreJ2));
			}
			else { //J2 gagne
				scores.add(new Score<OtherGameRole>(OtherGameRole.J1,Score.Status.LOOSE,scoreJ1));
				scores.add(new Score<OtherGameRole>(OtherGameRole.J2,Score.Status.WIN,scoreJ2));
			}
		}
		else {
			
		}
		return scores;
	}

	// --------------------- Other Methods ---------------------

	/**
	 * Nombre de graines restant sur le board
	 * @return
	 */
	private int grainesRestantes() {
		return grainesBoardJ1() + grainesBoardJ2();
	}
	
	/**
	 * Nombre de graines restant du côté du joueur 1
	 * @return
	 */
	private int grainesBoardJ1() {
		int total = 0;
		for(int i = 0 ; i < TAILLE / 2; i++) {
			total += board[i];
		}
		return total;
	}
	
	/**
	 * Nombre de graines restant du côté du joueur 2
	 * @return
	 */
	private int grainesBoardJ2() {
		int total = 0;
		for(int i = TAILLE / 2 ; i < TAILLE ; i++) {
			total += board[i];
		}
		return total;
	}
	
	//Si le J2 joue un coup qui peut enlever toutes les graines de J1 alors ce coup peut etre jouer mais les graines ne sont pas enlevé
	private boolean peutEnleverSurJ1(int index) {
		if(index != TAILLE / 2 - 1) //pour enlever toutes les graines il faut arriver sur la derniere case de J1 soit 5
			return true;
		int i = 0;
		while (i < TAILLE / 2) {
			if(board[i] < 2 || board[i] > 3)
				return true;
			i++;
		}
		return false;
	}
	
	//Si le J1 joue un coup qui peut enlever toutes les graines de J2 alors ce coup peut etre jouer mais les graines ne sont pas enlevé
	private boolean peutEnleverSurJ2(int index) {
		if(index != TAILLE - 1) //pour enlever toutes les graines il faut arriver sur la derniere case de J2 soit 11
			return true;
		int i = TAILLE / 2;
		while (i < TAILLE) {
			if(board[i] < 2 || board[i] > 3)
				return true;
			i++;
		}
		return false;
	}
	
	/* getters and setters */
	
	public int[] getBoard() {
		return board;
	}
	
	public int getScoreJ1() {
		int tmp = this.scoreJ1;
		return tmp;
	}
	
	public int getScoreJ2() {
		int tmp = this.scoreJ2;
		return tmp;
	}
	
	public int nbMoves(OtherGameRole role) {
		return possibleMoves(role).size();
	}
	
	@Override
	public String toString() {
		String res = "J2 : ";
		for(int cpt = TAILLE - 1 ; cpt >= TAILLE / 2; cpt--) {
			res += "|";
			if(board[cpt] <= 9) { // si nombre avec un chiffre
				res += " ";
			}
			if(cpt != (OtherGameBoard.TAILLE / 2))
				res += board[cpt];
			else 
				res += board[cpt] + "|";
		}
		res += "\nJ1 : ";
		for(int cpt = 0; cpt < OtherGameBoard.TAILLE / 2 ; cpt++) {
			res += "|";
			if(board[cpt] <= 9) { // si nombre avec un chiffre
				res += " ";
			}
			if(cpt != (OtherGameBoard.TAILLE / 2) - 1) 
				res += board[cpt];
			else
				res += board[cpt] + "|";
		}
		return res + "\n Score J1 = " + scoreJ1 + " | J2 = " + scoreJ2;
	}
	
	private boolean J1peutNourrirJ2() {
		final int index_arrive = 6;
		for(int i = 0 ; i < TAILLE / 2 ; i++) {
			if(board[i] >= index_arrive - i) {
				return true;
			}
		}
		return false;
	}
	
	private boolean J2peutNourrirJ1() {
		final int index_arrive = 12;
		for(int i = TAILLE / 2 ; i < TAILLE ; i++) {
			if(board[i] >= index_arrive - i) {
				return true;
			}
		}
		return false;
	}
	
	public int getLastScoreJ1() {
		return lastAddedScoreJ1;
	}
	
	public int getLastScoreJ2() {
		return lastAddedScoreJ2;
	}
	
	public int getMaxPointJ1() {
		int total = 0;
		for(int i = TAILLE / 2 ; i < TAILLE ; i++) {
			if(board[i] == 1 || board[i] == 2) {
				total += board[i] + 1;
			}
		}
		return total;
	}
	
	public int getMaxPointJ2() {
		int total = 0;
		for(int i = 0 ; i < TAILLE / 2; i++) {
			if(board[i] == 1 || board[i] == 2) {
				total += board[i] + 1;
			}
		}
		return total;
	}
	
	public int getMaxPointPossibleJ2() {
		int max = 0;
		int tmp = 0;
		int index = 0;
		for(int i = 0 ; i < TAILLE / 2; i++) {
			if(board[i] == 1 || board[i] == 2) {
				tmp += board[i] + 1;
				if(tmp > max) {
					max = tmp;
					index = i;
				}
			} 
			else tmp = 0;
		}
		int graines;
		for(int index_depart = TAILLE / 2 ; index_depart < TAILLE ; index_depart++) {
			graines = board[index_depart];
			if(12 + index == index_depart + graines)
				return max;
		}
		return 0;
	}
	
	/*public int getMaxPointPossibleJ1() {
		int max = 0;
		int tmp = 0;
		for(int i = TAILLE / 2 ; i < TAILLE ; i++) {
			if(board[i] == 1 || board[i] == 2) {
				tmp += board[i] + 1;
				if(tmp > max)
					max = tmp;
			} 
			else tmp = 0;
		}
		return max;
	}*/
	
	public int getMaxPointPossibleJ1() {
		int max = 0;
		int tmp = 0;
		int index = 0;
		for(int i = TAILLE / 2 ; i < TAILLE; i++) {
			if(board[i] == 1 || board[i] == 2) {
				tmp += board[i] + 1;
				if(tmp > max) {
					max = tmp;
					index = i;
				}
			} 
			else tmp = 0;
		}
		int graines;
		for(int index_depart = 0 ; index_depart < TAILLE / 2; index_depart++) {
			graines = board[index_depart];
			if(index == index_depart + graines)
				return max;
		}
		return 0;
	}
}
 