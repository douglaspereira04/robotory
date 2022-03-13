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
		int index = this.x.size() - 1;
		return this.x.get(index);
	}
	
	public int getRobotY() {
		int index = this.y.size() - 1;
		return this.y.get(index);
	}
}
