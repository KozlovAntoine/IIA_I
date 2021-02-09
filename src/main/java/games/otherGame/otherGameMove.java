package games.otherGame;

import iialib.games.model.IMove;

public class otherGameMove implements IMove {
	
	public final int x;
    
    otherGameMove(int x){
        this.x = x;
    }

    @Override
    public String toString() {
        return "Move{" + x + "}";
    }
}
