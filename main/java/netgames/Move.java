package netgames;

import br.ufsc.inf.leobr.cliente.Jogada;

public abstract class Move implements Jogada{
	public MoveType moveType;
	
	Move(MoveType type) {
		this.moveType = type;
	}

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}
	
	
}
