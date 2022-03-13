package netgames;


import dominioProblema.Board;
import interfaceGrafica.InterfaceRobotory;

public class InterfaceJogador {
	protected AtorNetgames ngames;
	protected Board board;
	protected InterfaceRobotory interfaceRobotory;
	
	
	
	public InterfaceJogador(InterfaceRobotory interfaceRobotory) {
		super();
		this.ngames = new AtorNetgames();
		this.board = new Board();
		this.interfaceRobotory = interfaceRobotory;
	}

	public void receiveMove(Move move) {
		
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
