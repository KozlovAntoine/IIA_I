package games.otherGame;

import iialib.games.model.IMove;

public class OtherGameMove implements IMove {
	
	public final int x;
    
    OtherGameMove(int x){
        this.x = x;
    }

    @Override
    public String toString() {
        return "Move{" + x + "}";
    }
}
