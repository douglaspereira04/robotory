package netgames;

public class PlaceEnergy extends Move {
	public int x;
	public int y;
	public boolean color;

	public PlaceEnergy(boolean color) {
		super(MoveType.PLACE_ENERGY);
		this.color = color;
	}
	
	public PlaceEnergy(int x, int y, boolean color) {
		super(MoveType.PLACE_ENERGY);
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void setPlace(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean getColor() {
		return this.color;
	}
}
