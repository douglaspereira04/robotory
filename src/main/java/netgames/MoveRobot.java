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
	
	public int getLength() {
		return x.size() - 1;
	}

	public int getEnergyXAt(int i) {
		// TODO Auto-generated method stub
		return x.get(i+1);
	}

	public int getEnergyYAt(int i) {
		// TODO Auto-generated method stub
		return y.get(i+1);
	}
}
