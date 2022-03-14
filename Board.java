package dominioProblema;

import java.util.ArrayList;

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
		MoveType type = move.getType();
		switch(type) {
		case PLACE_ENERGY:
			this.applyEnergyPlacement((PlaceEnergy) move);
			break;
		case MOVE_ROBOT:
			this.applyRobotMovement((MoveRobot) move);
			break;
		case GET_ENERGY:
			this.applyGetEnergy((GetEnergy) move);
			break;
		}
	}
	
	public String selectRobot(int x, int y) {
		String message = "";
		
		boolean turn = localPlayer.isTurn();
		
		if (turn) {
			Piece piece = getPiece(x, y);
			
			if (piece != null) {
				boolean holdsRobot = piece.holdsRobot();
				
				if (holdsRobot) {
					boolean canMove = canMove(x, y);
					
					if (canMove) {
						moveInProgress = new MoveRobot(x, y);
						message = "";
					}else if(!canMove) {
						message = "No possible movement";
					}
				}else if(!holdsRobot) {
					message = "Invalid selection";
				}
			}
		} else if(!turn) {
			message = "Not your turn";
		}
		
		return message;
	}
	
	public boolean isRobotMovementInProgress() {
		if (this.moveInProgress != null) {
			MoveType type = this.moveInProgress.getType();
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
		if (x < 5 && y < 6) {
			return this.board[x][y];
		}
		return null;
	}
	
	public boolean isCompatible(Piece robot, Piece piece) {
		if (piece != null) {
			Type robotType = robot.getType();
			Type pieceType = piece.getType();
			boolean auxRED = (robotType == Type.RED_ROBOT && (pieceType == Type.WHITE_ENERGY || pieceType == Type.BLACK_ENERGY));
			boolean auxBLACK = (robotType == Type.BLACK_ROBOT && (pieceType == Type.BLACK_ENERGY));
			boolean auxWHITE = (robotType == Type.WHITE_ROBOT && (pieceType == Type.WHITE_ENERGY));
			return (auxRED || auxBLACK || auxWHITE);
		}
		return false;
	}
	
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		boolean reachable = (((Math.abs(x1-x2) == 1) && (Math.abs(y1-y2) < 2)
				&& Math.abs(y1-y2) >= 0) || (x1==x2) && (Math.abs(y1-y2) < 2));
		return reachable;
	}
	
	public boolean canMove(int x, int y) {
		boolean canMove = false;
		for (int i = 0; i < 5 && canMove == false; i++) {
			for (int j = 0; j < 6 && canMove == false; j++) {
				boolean reachable = this.isReachable(x, y, i, j);
				if (reachable) {
					Piece piece = this.getPiece(i, j);
					Piece robot = this.getPiece(x, y);
					canMove = this.isCompatible(robot, piece);
				}
			}
		}
		return canMove;
	}
	
	public String selectEnergy(int x, int y) {
		String message = "";
		
		boolean turn = localPlayer.isTurn();
		
		if (turn) {
			Piece piece = getPiece(x, y);
			int robotX = ((MoveRobot)moveInProgress).getRobotX();
			int robotY = ((MoveRobot)moveInProgress).getRobotY();
			
			Piece robot = getPiece(robotX, robotY);
			boolean compatible = isCompatible(robot, piece);
			
			if (compatible) {
				int x2 = ((MoveRobot)moveInProgress).getLastX();
				int y2 = ((MoveRobot)moveInProgress).getLastY();
				
				boolean reachable = isReachable(x, y, x2, y2);
				
				if (reachable) {
					boolean registered = ((MoveRobot)moveInProgress).isRegistered(x,y);
				
					if (!registered) {
						
						((MoveRobot)moveInProgress).addEnergy(x, y);
						
						boolean canMove = canMove(x, y);
						
						if (!canMove) {
							message = "END";
						} else if(canMove) {
							message = "";
						}
					} else if (registered) {
						message = "Already registered";
					}
				} else if(!reachable) {
					message = "Unreachable piece";
				}
			}else if(!compatible) {
				message = "Incompatible piece";
			}
		} else if(!turn) {
			message = "Not your turn";
		}
		
		return message;
	}
	
	public void updateState() {
		if (this.matchInProgress) {
			this.localPlayer.switchTurn();
			this.remotePlayer.switchTurn();
			String name = "";
			if (this.localPlayer.isTurn()) {
				name = this.localPlayer.getName();
			} else {
				name = this.remotePlayer.getName();
			}
		} else {
			String name = this.localPlayer.getName();
			if (this.localPlayer.isWinner()) {
				this.setMessage(name); // ?
			} else {
				this.remotePlayer.isWinner();
				this.setMessage(name); // ?
			}
		}
		this.clearMoveInProgress();
	}
	
	public void clearMoveInProgress() {
		this.moveInProgress = null;
	}
	
	public Piece removePiece(int x, int y) {
		Piece piece = board[x][y];
		board[x][y] = null;
		return piece;
	}
	
	public void placePiece(int x, int y, Piece piece) {
		this.board[x][y] = piece;
	}
	
	public void endMatch() {
		this.setInitialState(0, "");
		this.remotePlayer = new Player();
		this.localPlayer.reset();
		this.updateState();
	}
	
	public void setInitialState(int order, String opponent) {
		this.matchInProgress = true;
		this.moveInProgress = null;
		this.board = new Piece[5][6];
		this.board[1][2] = new Piece(Type.RED_ROBOT);
		this.board[2][2] = new Piece(Type.BLACK_ENERGY);
		this.board[3][2] = new Piece(Type.WHITE_ROBOT);
		this.whiteEnergy = 10;
		this.blackEnergy = 10;
	}
	
	public void setMessage(String message) {
		
	}
	
	public void applyRobotMovement(MoveRobot move) {
		int length = move.getLength();
		
		for (int i = 0; i < length; i++) {
			int x = move.getEnergyXAt(i);
			int y = move.getEnergyYAt(i);
			
			this.removePiece(x, y);
		}
		
		int x = move.getRobotX();
		int y = move.getRobotY();
		
		Piece robot = removePiece(x, y);

		x = move.getLastX();
		y = move.getLastY();
		
		this.placePiece(x, y, robot);
		
		
	}
	
	public boolean isMatchInProgress() {
		return this.matchInProgress;
	}
	
	public void checkMatchEnd() { // Modificar diagrama de sequencia
		int blackSide = 0, whiteSide = 0;
		if (this.whiteEnergy == 0 || this.blackEnergy == 0) {
			for (int i = 0; i < 5; i++) {
				ArrayList<Piece> currentLine = this.getBoardLine(i);
				for (int j = 0; j < currentLine.size(); j++) {
					Piece piece = currentLine.get(j);
					if (piece != null) {
						boolean holdsRobot = piece.holdsRobot();
						if (holdsRobot) {
							if (i < 2) {
								blackSide++;
							} else {
								whiteSide++;
							}
						}
					}
				}
			}
			boolean localColor = this.localPlayer.getColor();
			if (localColor) {
				if (whiteSide > blackSide) {
					this.localPlayer.setWinner(true);
				} else {
					this.remotePlayer.setWinner(true);
				}
			}
			this.matchInProgress = false;
		}
	}
	
	public String selectFromPersonalSupply(boolean color, boolean owner) {
		String message = "";
		boolean co = this.localPlayer.getColor();
		if (owner == co) {
			if (!this.isMoveInProgress()) {
				boolean turn = this.localPlayer.isTurn();
				if (turn) {
					boolean empty = this.localPlayer.isEnergyEmpty();
					if (!empty) {
						PlaceEnergy move = new PlaceEnergy(color);
						this.moveInProgress = move;
					} else {
						message = "No energy available";
					}
				} else {
					message = "Not your turn";
				}
			} else {
				message = "Invalid selection";
			}
		} else {
			message = "Not your supply";
		}
		return message;
	}
	
	public String selectEnergyPlacement(int x, int y) {
		String message = "";
		if (this.localPlayer.isTurn()) {
			Piece piece = this.getPiece(x, y);
			if (piece == null) {
				if (this.moveInProgress.moveType == MoveType.PLACE_ENERGY) {
					((PlaceEnergy) this.moveInProgress).setPlace(x, y);
					message = "END";
				}
			} else {
				message = "Place not empty";
			}
		} else {
			message = "Not your turn";
		}
		return message;
	}
	
	public boolean isEnergyPlacementInProgress() {
		if (this.moveInProgress != null) {
			MoveType type = this.moveInProgress.getType();
			if (type == MoveType.PLACE_ENERGY) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void applyEnergyPlacement(PlaceEnergy move) {
		int x = move.getX();
		int y = move.getY();
		boolean color = move.getColor();
		
		Type type = null;
		if (color) {
			type = Type.WHITE_ENERGY;
		} else if(!color) {
			type = Type.BLACK_ENERGY;
		}
		
		Piece piece = new Piece(type);
		
		this.placePiece(x, y, piece);
		
		boolean turn = localPlayer.isTurn();
		
		if (turn) {
			localPlayer.decrementEnergy(color);
		} if (!turn) {
			remotePlayer.decrementEnergy(color);
		}
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
			MoveType type = this.moveInProgress.getType();
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
	
	public ArrayList<Piece> getBoardLine(int index) {
		int[] auxLinha = null, auxColuna = null;
		switch(index) {
			case 0:
				auxLinha = new int[] {0,1,2};
				auxColuna = new int[] {0,0,0};
				break;
			case 1:
				auxLinha = new int[] {0,1,2,3};
				auxColuna = new int[] {1,1,1,0};
				break;
			case 2:
				auxLinha = new int[] {0,1,2,3,4};
				auxColuna = new int[] {2,2,2,1,0};
				break;
			case 3:
				auxLinha = new int[] {0,1,2,3,4};
				auxColuna = new int[] {3,3,3,2,1};
				break;
			case 4:
				auxLinha = new int[] {1,2,3,4};
				auxColuna = new int[] {4,4,3,2};
				break;
			case 5:
				auxLinha = new int[] {2,3,4};
				auxColuna = new int[] {5,4,3};
				break;
		}
		ArrayList<Piece> aux = new ArrayList<Piece>(auxLinha.length);
		for (int i = 0; i < auxLinha.length; i++) {
			int linha = auxLinha[i];
			int coluna  = auxColuna[i];
			aux.add(this.board[linha][coluna]);
		}
		return aux;
	}
}