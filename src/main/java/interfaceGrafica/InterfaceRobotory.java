package interfaceGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;

import dominioProblema.Board;
import dominioProblema.Piece;
import dominioProblema.Type;
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

	
	public void displayState() {
		Board board = actor.getBoard();
		
		String message = board.getMessage();
		frame.getBoardPanel().getMessage().setText(message);
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				if(frame.getBoardPanel().getHexagon(i, j) != null) {
					Piece piece = board.getPiece(i, j);
					if(piece == null) {
						frame.getBoardPanel().getHexagon(i, j).setPiece(null);
					}else if(piece.getType() == Type.BLACK_ENERGY){
						frame.getBoardPanel().getHexagon(i, j).setPiece(interfaceGrafica.BoardHexagon.Piece.BLACK_ENERGY);
					}else if(piece.getType() == Type.WHITE_ENERGY){
						frame.getBoardPanel().getHexagon(i, j).setPiece(interfaceGrafica.BoardHexagon.Piece.WHITE_ENERGY);
					}else if(piece.getType() == Type.BLACK_ROBOT){
						frame.getBoardPanel().getHexagon(i, j).setPiece(interfaceGrafica.BoardHexagon.Piece.BLACK_ROBOT);
					}else if(piece.getType() == Type.WHITE_ROBOT){
						frame.getBoardPanel().getHexagon(i, j).setPiece(interfaceGrafica.BoardHexagon.Piece.WHITE_ROBOT);
					}else if(piece.getType() == Type.RED_ROBOT){
						frame.getBoardPanel().getHexagon(i, j).setPiece(interfaceGrafica.BoardHexagon.Piece.RED_ROBOT);
					} else {
						frame.getBoardPanel().getHexagon(i, j).setPiece(interfaceGrafica.BoardHexagon.Piece.EMPTY);
					}
					frame.getBoardPanel().getHexagon(i, j).displayPiece();
				}
				
			}
		}
		
		try {
			
			int blackEnergy = board.getBlackEnergy();
			int whiteEnergy = board.getWhiteEnergy();

			frame.getBoardPanel().getBlackSupply().setText(""+blackEnergy);
			frame.getBoardPanel().getWhiteSupply().setText(""+whiteEnergy);
			
			frame.getBoardPanel().getBlackSupply().setPiece(interfaceGrafica.BoardHexagon.Piece.BLACK_ENERGY);
			frame.getBoardPanel().getWhiteSupply().setPiece(interfaceGrafica.BoardHexagon.Piece.WHITE_ENERGY);
			
			frame.getBoardPanel().getBlackSupply().displayPiece();
			frame.getBoardPanel().getWhiteSupply().displayPiece();
			
			int p1BlackEnergy;
			int p1WhiteEnergy;
			int p2BlackEnergy;
			int p2WhiteEnergy;
			
			if (board.getLocalPlayer().getColor() == true) {
				p1BlackEnergy = board.getLocalPlayer().getBlackEnergy();
				p1WhiteEnergy = board.getLocalPlayer().getWhiteEnergy();
				p2BlackEnergy = board.getRemotePlayer().getBlackEnergy();
				p2WhiteEnergy = board.getRemotePlayer().getWhiteEnergy();
			} else {
				p1BlackEnergy = board.getRemotePlayer().getBlackEnergy();
				p1WhiteEnergy = board.getRemotePlayer().getWhiteEnergy();
				p2BlackEnergy = board.getLocalPlayer().getBlackEnergy();
				p2WhiteEnergy = board.getLocalPlayer().getWhiteEnergy();
			}

			frame.getBoardPanel().getP1Supply(0).setText(""+p1BlackEnergy);
			frame.getBoardPanel().getP1Supply(1).setText(""+p1WhiteEnergy);
			frame.getBoardPanel().getP2Supply(0).setText(""+p2BlackEnergy);
			frame.getBoardPanel().getP2Supply(1).setText(""+p2WhiteEnergy);
			
			if (p1BlackEnergy == 0) {
				frame.getBoardPanel().getP1Supply(0).setPiece(interfaceGrafica.BoardHexagon.Piece.EMPTY);
			}else {
				frame.getBoardPanel().getP1Supply(0).setPiece(interfaceGrafica.BoardHexagon.Piece.BLACK_ENERGY);
			}
			
			if (p1WhiteEnergy == 0) {
				frame.getBoardPanel().getP1Supply(1).setPiece(interfaceGrafica.BoardHexagon.Piece.EMPTY);
			}else {
				frame.getBoardPanel().getP1Supply(1).setPiece(interfaceGrafica.BoardHexagon.Piece.WHITE_ENERGY);
			}

			if (p2BlackEnergy == 0) {
				frame.getBoardPanel().getP2Supply(0).setPiece(interfaceGrafica.BoardHexagon.Piece.EMPTY);
			}else {
				frame.getBoardPanel().getP2Supply(0).setPiece(interfaceGrafica.BoardHexagon.Piece.BLACK_ENERGY);
			}
			
			if (p2WhiteEnergy == 0) {
				frame.getBoardPanel().getP2Supply(1).setPiece(interfaceGrafica.BoardHexagon.Piece.EMPTY);
			}else {
				frame.getBoardPanel().getP2Supply(1).setPiece(interfaceGrafica.BoardHexagon.Piece.WHITE_ENERGY);
			}
			
			frame.getBoardPanel().getP1Supply(0).displayPiece();
			frame.getBoardPanel().getP1Supply(1).displayPiece();
			frame.getBoardPanel().getP2Supply(0).displayPiece();
			frame.getBoardPanel().getP2Supply(1).displayPiece();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
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
							//JOptionPane.showMessageDialog(null, position.getPosition());
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
