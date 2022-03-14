package dominioProblema;

public class Piece {
	protected Type type;
	
	public Piece(Type type) {
		this.type = type;
	}
	
	public boolean holdsRobot() {
		return false;
	}
	
	public Type getType() {
		return null;
	}
}
