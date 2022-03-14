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



	public void connect() {
		actor.connect();
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
		actor.disconnect();
	}
	
	public void displayState() {
		
	}
	
	public void selectPosition(int x, int y) {
		actor.selectPosition(x, y);
	}
	
	public void startMatch() {
		actor.startMatch();
	}
	
	public void selectFromPersonalSupply(boolean color, boolean owner) {
		actor.selectFromPersonalSupply(color, owner);
	}
	
	public void getEnergy(boolean color) {
		actor.getEnergy(color);
	}
	
	public void pressEndMove() {
		actor.pressEndMove();
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
				connect();
			}
		});
		
		/**
		 * Disconnect
		 */
		frame.getDisconnectMenuItem().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				disconnect();
			}
		});
		

		
		frame.getStartMenuItem().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startMatch();
			}
		});
		
		frame.getBoardPanel().getWhiteSupply().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getEnergy(true);
				super.mouseClicked(e);
			}
		});

		
		frame.getBoardPanel().getBlackSupply().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getEnergy(false);
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
							selectPosition(position.getPosition().x, position.getPosition().y);
							JOptionPane.showMessageDialog(null, position.getPosition());
							super.mouseClicked(e);
						}
					});
				}
			}
		}

		frame.getBoardPanel().getP1Supply(0).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectFromPersonalSupply(false, true);
				super.mouseClicked(e);
			}
		});
		
		frame.getBoardPanel().getP1Supply(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectFromPersonalSupply(true, true);
				super.mouseClicked(e);
			}
		});
		
		frame.getBoardPanel().getP2Supply(0).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectFromPersonalSupply(false, false);
				super.mouseClicked(e);
			}
		});
		
		frame.getBoardPanel().getP2Supply(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectFromPersonalSupply(true, false);
				super.mouseClicked(e);
			}
		});
		
		frame.getBoardPanel().getEndMoveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pressEndMove();
			}
		});
		
	}
	
	
}
