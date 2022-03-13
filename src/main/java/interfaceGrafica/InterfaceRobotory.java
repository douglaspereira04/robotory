package interfaceGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

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
		JOptionPane.showMessageDialog(null, notification);
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
		

		
		frame.getStartMenuItem().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actor.startMatch();
			}
		});
		
		frame.getBoardPanel().getWhiteSupply().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actor.getEnergy(true);
				super.mouseClicked(e);
			}
		});

		
		frame.getBoardPanel().getBlackSupply().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actor.getEnergy(false);
				super.mouseClicked(e);
			}
		});
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				BoardHexagon position = frame.getBoardPanel().getHexagon(i, j);
				
				if (position != null) {
					position.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							actor.selectPosition(position.getPosition().x, position.getPosition().y);
							super.mouseClicked(e);
						}
					});
				}
			}
		}
		
	}
	
	
}
