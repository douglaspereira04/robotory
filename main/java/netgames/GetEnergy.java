package netgames;

public class GetEnergy extends Move {
	public int white;
	public int black;
	public boolean last;
	
	GetEnergy() {
		super(MoveType.GET_ENERGY);
		this.white = 0;
		this.black = 0;
		this.last = false;
	}
	
	public void addEnergy(boolean color) {
		if (color) {
			this.white += 1;
		} else {
			this.black += 1;
		}
	}
	
	public boolean getLastEnergy() {
		return this.last;
	}
	
	public int getWhiteAmount() {
		return this.white;
	}
	
	public int getBlackAmount() {
		return this.black;
	}
	
	public void setLastEnergy(boolean color) {
		this.last = color;
	}
	
}
