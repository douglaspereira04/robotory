package netgames;

import dominioProblema.Board;
import interfaceGrafica.InterfaceRobotory;

public class InterfaceJogador {
	protected AtorNetgames ngames;
	protected Board board;
	protected InterfaceRobotory interfaceRobotory;
	
	public void receiveMove(Move move) {
		
	}
	
	public boolean connect() {
		return false;
	}
	
	public boolean disconnect() {
		return false;
	}
	
	public String selectPosition(int x, int y) {
		return null;
	}
	
	public String startMatch() {
		return null;
	}
	
	public void requestMatchStart(int order, String opponent) {
		
	}
	
	public String selectFromPersonalSupply(boolean color, boolean owner) {
		return null;
	}
	
	public void endMatch() {
		
	}
	
	public Move getMoveInProgress() {
		return null;
	}
	
	public boolean isGetEnergyInProgress() {
		return false;
	}
	
	public String pressEndMove() {
		return null;
	}
	
	public void updateState() {
		
	}
	
	
	
}
