package netgames;


import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import dominioProblema.Board;
import interfaceGrafica.InterfaceRobotory;

public class InterfaceJogador {
	protected AtorNetgames ngames;
	protected Board board;
	protected InterfaceRobotory interfaceRobotory;
	
	
	
	public InterfaceJogador(InterfaceRobotory interfaceRobotory) {
		super();
		this.ngames = new AtorNetgames(this);
		this.board = new Board();
		this.interfaceRobotory = interfaceRobotory;
	}

	public void receiveMove(Move move) {
		board.receiveMove(move);
		board.updateState();
		interfaceRobotory.displayState();
	}
	
	public void connect() {
		
		boolean connected = ngames.isConnected();
		if (!connected) {

			String address = interfaceRobotory.getServerIP();
			String name = interfaceRobotory.getPlayerName();
			
			String notification = ngames.conectar(address, name);
			
			if(notification.equals("Sucesso: conectado a Netgames Server")) {
				ngames.defineConnected(true);
			}
		}else if(connected) {
			interfaceRobotory.notify("Voce ja esta conectado");
		}
		
	}
	
	public void disconnect() {
		boolean connected = ngames.isConnected();
		
		if(!connected) {
			interfaceRobotory.notify("Voce não está conectado");
		}else if (connected) {
			
			if(board.isMatchInProgress()) {
				board.endMatch();
			}
			
			ngames.finalizarPartidaComErro(null);
			
			String notification = ngames.desconectar();
			
			interfaceRobotory.notify(notification);
			interfaceRobotory.displayState();
		}
		
	}
	
	public void selectPosition(int x, int y) {
		boolean getEnergyInProgress = board.isGetEnergyInProgress();
		String message = "";
		
		if (!getEnergyInProgress) {
			boolean isMoveInProgress = board.isMoveInProgress();
			
			if(isMoveInProgress) {
				boolean robotMovementInProgress = board.isRobotMovementInProgress();
				
				if (robotMovementInProgress) {
					message = board.selectEnergy(x, y);
					
					if (message.equals("END")) {
						board.applyRobotMovement((MoveRobot) board.getMoveInProgress());
					}
					
				}else if(!robotMovementInProgress) {
					message = board.selectEnergy(x, y);
					if(message.equals("END")) {
						board.applyEnergyPlacement((PlaceEnergy) board.getMoveInProgress());
					}
				}
			} else if (!isMoveInProgress) {
				message = board.selectRobot(x, y);
			}

			
			if(message.equals("END")) {
				Move move = board.getMoveInProgress();
				try {
					ngames.sendMove(move);
				} catch (NaoJogandoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				board.updateState();
			}else if (!message.equals("")) {
				interfaceRobotory.notify(message);
				board.clearMoveInProgress();
			}
			
		}else if(getEnergyInProgress) {
			String notification = "Already getting energy";
			interfaceRobotory.notify(notification);
		}
	}
	
	public void startMatch() {
		boolean connected = ngames.isConnected();
		String message = null;
		
		if (connected) {
			boolean matchInProgress = board.isMatchInProgress();
			
			if (matchInProgress) {
				ngames.finalizarPartidaComErro(null);
				board.endMatch();
				ngames.iniciarPartida();
			}
			message = "";
			
		}else if (!connected) {
			message = "Not Connected";
		}
		
		if(message.equals("")) {
			interfaceRobotory.notify(message);
		}
	}
	
	public void requestMatchStart(int order, String opponent) {
		
	}
	
	public void selectFromPersonalSupply(boolean color, boolean owner) {
		String message = board.selectFromPersonalSupply(color, owner);
		if(!message.equals("")) {
			interfaceRobotory.notify(message);
		}
	}
	
	public void getEnergy(boolean color) {
		String message = board.getEnergy(color);
		if (message.equals("END")) {
			try {
				ngames.sendMove(board.getMoveInProgress());
			} catch (NaoJogandoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.updateState();
			interfaceRobotory.displayState();
		} else if(!message.equals("")) {
			interfaceRobotory.notify(message);
		}
	}
	
	public void endMatch() {
		
	}
	
	public Move getMoveInProgress() {
		return null;
	}
	
	public boolean isGetEnergyInProgress() {
		return false;
	}
	
	public void pressEndMove() {
		Move moveInProgress = board.getMoveInProgress();
		String message = "";
		
		if (moveInProgress != null) {
			MoveType type = moveInProgress.getType();
			
			if (type == MoveType.PLACE_ENERGY) {
				message = "Incomplete move";
			} else if(type == MoveType.MOVE_ROBOT) {
				
				MoveRobot robotMovement = (MoveRobot)moveInProgress;
				int length = robotMovement.getLength();
				
				if (length < 1) {
					message = "Incomplete move";
				} else if(!(length < 1)) {
					board.applyRobotMovement(robotMovement);
				}
			} 
		} else if(moveInProgress == null) {
			message = "No move was made";
		}
		
		if(message =="") {
			try {
				ngames.sendMove(moveInProgress);
			} catch (NaoJogandoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.updateState();
			interfaceRobotory.displayState();
		} else if(!message.equals("")) {
			interfaceRobotory.notify(message);
		}
	}
	
	public void updateState() {
		
	}
	
	
	
}
