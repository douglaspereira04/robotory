package dominioProblema;

public class Piece {
	protected Type type;
	
	public Piece(Type type) {
		this.type = type;
	}
	
	public boolean holdsRobot() {
		boolean holdsRobot = 
				type == Type.RED_ROBOT || 
				type == Type.WHITE_ROBOT ||
                type == Type.BLACK_ROBOT;
		
		return holdsRobot;
	}
	
	public Type getType() {
		return type;
	}
}
