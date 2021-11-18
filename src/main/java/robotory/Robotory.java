package robotory;

import control.board.BoardController;
import view.board.BoardPanel;
import view.robotory.RobotoryView;

/**
 * Main class 
 * @author douglas
 *
 */
public class Robotory {

	public static void main(String[] args) {
		
		
		BoardPanel boardPanel = new BoardPanel();
		BoardController boardController = new BoardController(boardPanel);
		
		RobotoryView appView = new RobotoryView(boardPanel);
		
	}

}
