package netgames;

import br.ufsc.inf.leobr.cliente.Jogada;

public abstract class Move implements Jogada{
	public MoveType moveType;
	
	Move(MoveType type) {
		this.moveType = type;
	}

	public MoveType getType() {
		return moveType;
	}

	public void setType(MoveType moveType) {
		this.moveType = moveType;
	}
	
	
}
