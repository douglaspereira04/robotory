package dominioProblema;

public class Player {
	
	protected String name;
	protected boolean color;
	protected boolean turn;
	protected boolean winner;
	protected int whiteEnergy;
	protected int blackEnergy;
	
	public Player() {
	}
	
	public Player(String opponent) {
		this.name = opponent;
	}

	public boolean isTurn() {
		return false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isWinner() {
		return this.winner;
	}
	
	public void switchTurn() {
		this.turn = !this.turn;
	}
	
	public void reset() {
		this.name = null;
		this.color = false;
		this.turn = false;
		this.winner = false;
		this.whiteEnergy = 2;
		this.blackEnergy = 2;
	}
	
	public void setFirst() {
		this.color = true;
		this.turn = true;
	}
	
	public boolean getColor() {
		return this.color;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	public boolean isEnergyEmpty(boolean color) {
		if (color) {
			return (this.whiteEnergy == 0);
		} else {
			return (this.blackEnergy == 0);
		}
	}

	public void decrementEnergy(boolean color) {
		if (color) {
			this.whiteEnergy -= 1;
		} else {
			this.blackEnergy -= 1;
		}
	}
	
	public void incrementEnergy(boolean color, int amount) {
		if (color) {
			this.whiteEnergy += amount;
		} else {
			this.blackEnergy += amount;
		}
	}
	
	public boolean isEnergyFull() {
		return (this.whiteEnergy + this.blackEnergy == 4);
	}
	
	public boolean isEnergyEmpty() {
		return (this.whiteEnergy + this.blackEnergy == 0);
	}
	
	
}
