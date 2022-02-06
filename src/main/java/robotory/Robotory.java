package robotory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import control.board.BoardController;
import rede.AtorNetgames;
import view.board.BoardPanel;
import view.robotory.RobotoryFrame;

/**
 * Main class 
 * @author douglas
 *
 */
public class Robotory {

	public static void main(String[] args) {
		
		
		BoardPanel boardPanel = new BoardPanel();
		BoardController boardController = new BoardController(boardPanel);
		
		RobotoryFrame robotoryFrame = new RobotoryFrame(boardPanel);
		
		AtorNetgames atorNetgames = new AtorNetgames();
		
		
		robotoryFrame.getConnectMenuItem().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String address = JOptionPane.showInputDialog("Endereço do servidor: ");
				if (address != null) {
					String name = JOptionPane.showInputDialog("Nome do jogador: ");
					if (name != null) {
						String result = atorNetgames.conectar(address, name);
						JOptionPane.showMessageDialog(robotoryFrame, result);
					}
				}
			}
		});
		

		robotoryFrame.getDisconnectMenuItem().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String result = atorNetgames.desconectar();
				JOptionPane.showMessageDialog(robotoryFrame, result);
			}
		});
		
		robotoryFrame.getStartMenuItem().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String result = atorNetgames.iniciarPartida();
				JOptionPane.showMessageDialog(robotoryFrame, result);
			}
		});
		
	}

}
