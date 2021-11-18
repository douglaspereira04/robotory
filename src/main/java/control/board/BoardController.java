package control.board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import view.board.BoardHexagon;
import view.board.BoardPanel;

/**
 * Class designed to control BoardPanel behavior
 * @author douglas
 *
 */
public class BoardController {
	

	protected BoardPanel boardPanel = null;
	
	public BoardController(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
		
		initializeBehavior();
	}

	/**
	 * Sample behavior
	 * Display random piece in clicked hexagon
	 */
	private void initializeBehavior() {
		
		this.boardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BoardHexagon hexagon = null;
				if (arg0.getSource().getClass() == BoardHexagon.class) {
					hexagon = (BoardHexagon) arg0.getSource();
					hexagon.setPiece(BoardHexagon.Piece.values()[new Random().nextInt(BoardHexagon.Piece.values().length)]);
					hexagon.displayPiece();
				}
				super.mouseClicked(arg0);
			}
		});
		
	}
	
}
