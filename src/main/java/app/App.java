package app;

import control.board.BoardController;
import view.app.AppView;
import view.board.BoardPanel;

/**
 * Main class 
 * @author douglas
 *
 */
public class App {

	public static void main(String[] args) {
		
		
		BoardPanel boardPanel = new BoardPanel();
		BoardController boardController = new BoardController(boardPanel);
		
		AppView appView = new AppView(boardPanel);
		
	}

}
