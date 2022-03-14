package netgames;

import java.util.ArrayList;
import java.util.Iterator;

public class MoveRobot extends Move {
	public ArrayList<Integer> x;
	public ArrayList<Integer> y;
	
	public MoveRobot(int x, int y) {
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

	public int getLastX() {
		return x.get(x.size()-1);
	}
	
	public int getLastY() {
		return y.get(y.size()-1);
	}

	public boolean isRegistered(int x, int y) {
		for (int i = 0; i < this.x.size(); i++) {
			if (this.x.get(i).equals(x) && this.y.get(i).equals(y)) {
				return true;
			}
		} 
		
		return false;
	}

	public void addEnergy(int x, int y) {
		this.x.add(x);
		this.y.add(y);
		
	}
}
