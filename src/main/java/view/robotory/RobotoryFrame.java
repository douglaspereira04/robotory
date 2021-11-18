package view.robotory;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.board.BoardPanel;

/**
 * Frame designed to display robotory board
 * 
 * @author douglas
 *
 */
public class RobotoryFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1596507511992392643L;

	protected BoardPanel boardPanel = null;
	protected JMenuBar menuBar = null;
	protected JMenu gameMenu = null;
	protected JMenuItem connectMenuItem = null;
	protected JMenuItem disconnectMenuItem = null;
	protected JMenuItem startMenuItem = null;

	public RobotoryFrame(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
		initialize();
	}

	public void initialize() {
		this.setTitle("Robotory");
		
		this.setPreferredSize(new Dimension(BoardPanel.DIMENSION.width, BoardPanel.DIMENSION.height));
		this.setMaximumSize(new Dimension(BoardPanel.DIMENSION.width, BoardPanel.DIMENSION.height));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		this.setVisible(true);
		initializeMenuBar();

		initializeBoardPanel();

		int diff = this.getInsets().top + this.getInsets().bottom + menuBar.getHeight();

		Dimension newSize = new Dimension(BoardPanel.DIMENSION.width, BoardPanel.DIMENSION.height + diff);
		this.setPreferredSize(newSize);
		this.setMaximumSize(newSize);
		this.setSize(newSize);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Initializes menu bar and its menus
	 */
	private void initializeMenuBar() {
		menuBar = new JMenuBar();
		gameMenu = new JMenu("Jogo");
		connectMenuItem = new JMenuItem("Conectar");
		disconnectMenuItem = new JMenuItem("Desconectar");
		startMenuItem = new JMenuItem("Iniciar partida");

		menuBar.add(gameMenu);
		gameMenu.add(connectMenuItem);
		gameMenu.add(disconnectMenuItem);
		gameMenu.add(startMenuItem);

		this.setJMenuBar(menuBar);

	}

	/**
	 * Initializes board panel
	 */
	public void initializeBoardPanel() {

		this.setContentPane(boardPanel);
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public void setBoardPanel(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
	}

	public JMenu getGameMenu() {
		return gameMenu;
	}

	public void setGameMenu(JMenu gameMenu) {
		this.gameMenu = gameMenu;
	}

	public JMenuItem getConnectMenuItem() {
		return connectMenuItem;
	}

	public void setConnectMenuItem(JMenuItem connectMenuItem) {
		this.connectMenuItem = connectMenuItem;
	}

	public JMenuItem getDisconnectMenuItem() {
		return disconnectMenuItem;
	}

	public void setDisconnectMenuItem(JMenuItem disconnectMenuItem) {
		this.disconnectMenuItem = disconnectMenuItem;
	}

	public JMenuItem getStartMenuItem() {
		return startMenuItem;
	}

	public void setStartMenuItem(JMenuItem startMenuItem) {
		this.startMenuItem = startMenuItem;
	}

}
