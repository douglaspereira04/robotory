package dominioProblema;

import netgames.GetEnergy;
import netgames.Move;
import netgames.MoveRobot;
import netgames.MoveType;
import netgames.PlaceEnergy;

public class Board {
	protected Piece[][] board = new Piece[5][6];
	protected Player localPlayer;
	protected Player remotePlayer;
	protected boolean matchInProgress = false;
	protected Move moveInProgress = null;
	protected int whiteEnergy;
	protected int blackEnergy;
	
	public void receiveMove(Move move) {
		
	}
	
	public String selectRobot(int x, int y) {
		return null;
	}
	
	public boolean isRobotMovementInProgress() {
		if (this.moveInProgress != null) {
			MoveType type = this.moveInProgress.getMoveType();
			if (type == MoveType.MOVE_ROBOT) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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
		moveInProgress = null;
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
		if (this.moveInProgress != null) {
			MoveType type = this.moveInProgress.getMoveType();
			if (type == MoveType.PLACE_ENERGY) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void applyEnergyplacement(PlaceEnergy move) {
		
	}
	
	public void decrementEnergy(boolean color, int amount) {
		if (color) {
			this.whiteEnergy -= amount;
		} else {
			this.blackEnergy -= amount;
		}
	}
	
	public void applyGetEnergy(GetEnergy move) {
		if (this.localPlayer.isTurn()) {
			boolean color = move.getLastEnergy();
			this.localPlayer.incrementEnergy(color, 1);
			this.decrementEnergy(color, 1);
		} else {
			int white = move.getWhiteAmount();
			int black = move.getBlackAmount();
			this.remotePlayer.incrementEnergy(true, white);
			this.remotePlayer.incrementEnergy(false, black);
			this.decrementEnergy(true, white);
			this.decrementEnergy(false, black);
		}
		this.checkMatchEnd();
	}
	
	public boolean isMoveInProgress() {
		return (this.moveInProgress != null);
	}
	
	public boolean isEnergyEmpty(boolean color) {
		if (color) {
			return (this.whiteEnergy == 0);
		} else {
			return (this.blackEnergy == 0);
		}
	}
	
	public Move getMoveInProgress() {
		return this.moveInProgress;
	}
	
	public boolean isGetEnergyInProgress() {
		if (this.moveInProgress != null) {
			MoveType type = this.moveInProgress.getMoveType();
			if (type == MoveType.GET_ENERGY) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String getEnergy(boolean color) {
		String message = "";
		if (isGetEnergyInProgress() || this.moveInProgress == null) {
			if (this.localPlayer.isTurn()) {
				if (!this.localPlayer.isEnergyFull()) {
					GetEnergy move = new GetEnergy();
					move.addEnergy(color);
					move.setLastEnergy(color);
					this.moveInProgress = move;
					this.applyGetEnergy(move);
					if (this.localPlayer.isEnergyFull() || !this.matchInProgress) {
						message = "END";
					}
				} else {
					message = "Your supply is already full";
				}
			} else {
				message =  "Not your turn";
			}
		}
		return message;
	}
	
	public int[] getBoardLine(int index) {
		return null;
	}
}
