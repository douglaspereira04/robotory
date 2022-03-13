package interfaceGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import netgames.InterfaceJogador;

public class InterfaceRobotory {
	
	protected RobotoryFrame frame;
	protected InterfaceJogador actor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceRobotory window = new InterfaceRobotory();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public InterfaceRobotory() {
		super();
		this.frame = new RobotoryFrame(new BoardPanel());
		this.actor = new InterfaceJogador(this);
		
		initializeBehavior();
	}



	public boolean connect() {
		return false;
	}
	
	public String getPlayerName() {
		return JOptionPane.showInputDialog("Nome do jogador: ");
	}
	
	public String getServerIP() {
		return JOptionPane.showInputDialog("Endereço do servidor: ");
	}
	
	public void notify(String notification) {
		
	}
	
	public void disconnect() {
		
	}
	
	public void displayState() {
		
	}
	
	public void selectPosition(int x, int y) {
		
	}
	
	public void startMatch() {
		
	}
	
	public void selectFromPersonalSupply(boolean color, boolean owner) {
		
	}
	
	public void getEnergy(boolean color) {
		
	}
	
	public void selectEnergyFromSupply(boolean color) {
		
	}
	
	public void pressEndMove() {
		
	}
	
	/**
	 * Where sequence diagrams that starts 
	 * with gui calls to InterfaceRobotory are coded
	 */
	public void initializeBehavior() {
		
		/**
		 * Connect
		 */
		frame.getConnectMenuItem().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actor.connect();
			}
		});
		
		/**
		 * Disconnect
		 */
		frame.getDisconnectMenuItem().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actor.disconnect();
			}
		});
		
	}
	
	
}
