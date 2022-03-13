package robotory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import interfaceGrafica.BoardPanel;
import interfaceGrafica.RobotoryFrame;
import netgames.AtorNetgames;

/**
 * Main class 
 * @author douglas
 *
 */
public class Robotory {

	public static void main(String[] args) {
		
		
		BoardPanel boardPanel = new BoardPanel();
		
		RobotoryFrame robotoryFrame = new RobotoryFrame(boardPanel);
		
		AtorNetgames atorNetgames = new AtorNetgames();
		
		
		
		
	}

}
