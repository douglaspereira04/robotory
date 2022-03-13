package netgames;

import java.util.ArrayList;

public class MoveRobot extends Move {
	public ArrayList<Integer> x;
	public ArrayList<Integer> y;
	
	MoveRobot(int x, int y) {
		super(MoveType.MOVE_ROBOT);
		this.x = new ArrayList<Integer>();
		this.y = new ArrayList<Integer>();
	}
	
	public int getRobotX() {
		return this.x.get(0);
	}
	
	public int getRobotY() {
		return this.y.get(0);
	}
}
