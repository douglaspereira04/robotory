package dominioProblema;

import netgames.GetEnergy;
import netgames.Move;
import netgames.MoveRobot;
import netgames.PlaceEnergy;

public class Board {
	protected Piece[][] board = new Piece[5][6];
	protected Player localPlayer;
	protected Player remotePlayer;
	protected boolean matchInProgress;
	protected Move moveInProgress;
	protected int whiteEnergy;
	protected int blackEnergy;
	
	public void receiveMove(Move move) {
		
	}
	
	public boolean disconnectOngoingMatch() {
		return false;
		
	}
	
	public String selectRobot(int x, int y) {
		return null;
	}
	
	public boolean isRobotMovementInProgress() {
		return false;
	}
	
	public Piece getPiece(int x, int y) {
		return null;
	}
	
	public boolean isCompatible(Piece robot, Piece piece) {
		return false;
	}
	
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		return  false;
	}
	
	public boolean canMove(int x, int y) {
		return false;
	}
	
	public String selectEnergy(int x, int y) {
		return null;
	}
	
	public void updateState() {
		
	}
	
	public void clearMoveInProgress() {
		
	}
	
	public Piece removePiece(int x, int y) {
		return null;
	}
	
	public void placePiece(int x, int y, Piece piece) {
		
	}
	
	public void endMatch() {
		
	}
	
	public void setInitialState(int order, String opponent) {
		
	}
	
	public void setMessage(String message) {
		
	}
	
	public void applyRobotMovement(MoveRobot move) {
		
	}
	
	public boolean isMatchInProgress() {
		return false;
	}
	
	public void checkMatchEnd() {
		
	}
	
	public String selectFromPersonalSupply(boolean color, boolean owner) {
		return null;
	}
	
	public String selectEnergyPlacement(int x, int y) {
		return null;
	}
	
	public boolean isEnergyPlacementInProgress() {
		return false;
	}
	
	public void applyEnergyplacement(PlaceEnergy move) {
		
	}
	
	public void decrementEnergy(boolean color, int amount) {
		
	}
	
	public void applyGetEnergy(GetEnergy move) {
		
	}
	
	public boolean isMoveInProgress() {
		return false;
	}
	
	public boolean isEnergyEmpty(boolean color) {
		return false;
	}
	
	public Move getMoveInProgress() {
		return null;
	}
	
	public boolean isGetEnergyInProgress() {
		return false;
	}
	
	public String getEnergy(boolean color) {
		return null;
	}
	
	public int[] getBoardLine(int index) {
		return null;
	}
}