package netgames;

import br.ufsc.inf.leobr.cliente.Jogada;

public abstract class Move implements Jogada{
	MoveType moveType;

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}
	
	
}
