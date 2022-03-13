package interfaceGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Swing AWT interface for Robotory board game
 * @author douglas
 *
 */
public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -5329727772582606436L;

	/**
	 * Board size
	 */
	public static final Dimension DIMENSION = new Dimension(780, 530);

	protected HashMap<Point, BoardHexagon> hexagons = null;
	protected List<BoardHexagon> p1Supply = null;
	protected List<BoardHexagon> p2Supply = null;
	protected BoardHexagon blackSupply = null;
	protected BoardHexagon whiteSupply = null;
	protected JButton endMoveButton = null;
	
	/**
	 * Constructor
	 * Initializes interface;
	 */
	public BoardPanel() {
		this.setLayout(null);
		initialize();

	}

	/**
	 * Initializes interface
	 */
	public void initialize() {
		initializeHexagons();
		initializePersonalSupplies();
		initializeCommonSupply();
		
		initializeEndMoveButton();

	}
	

	/**
	 * Initializes end move button
	 */
	protected void initializeEndMoveButton() {
		int xoffset = 30;
		int yoffset = 58;
		
		endMoveButton = new JButton("End Move");
		endMoveButton.setBounds(xoffset, yoffset, 120, 30);
		this.add(endMoveButton);
		
	}

	/**
	 * Initializes board hexagons
	 */
	protected void initializeHexagons() {
		hexagons = new HashMap<>();

		int xoffset = 213;
		int yoffset = 97;
		int leftoffset = 73;
		int topoffset = 3;
		int jumpoffset = 40;
		int hexagonsize = 80;

		BoardHexagon h = null;

		int x = 0;
		for (int i = 4; i < 7; i++) {
			for (int y = 0; y < i; y++) {
				h = new BoardHexagon(x, y);
				hexagons.put(new Point(x, y), h);
				h.setBounds(xoffset, yoffset + (hexagonsize * y) + (topoffset * y), hexagonsize, hexagonsize);
				this.add(h);
			}
			x++;
			yoffset -= jumpoffset;
			xoffset += leftoffset;
		}

		x = 3;
		yoffset += (jumpoffset * 2);
		for (int i = 5; i > 3; i--) {
			for (int y = 0; y < i; y++) {
				h = new BoardHexagon(x, y);
				hexagons.put(new Point(x, y), h);
				h.setBounds(xoffset, yoffset + (hexagonsize * y) + (topoffset * y), hexagonsize, hexagonsize);
				this.add(h);
			}
			x++;
			yoffset += jumpoffset;
			xoffset += leftoffset;
		}

	}

	/**
	 * Initializes personal supply buttons
	 */
	protected void initializePersonalSupplies() {

		p1Supply = new ArrayList<BoardHexagon>();
		p2Supply = new ArrayList<BoardHexagon>();

		int yoffset = 268;
		int xoffset = 30;
		int hexagonsize = 80;
		int topoffset = 3;
		int jumpoffset = 40;
		int leftoffset = 73;

		BoardHexagon h = null;

		h = new BoardHexagon(BoardHexagon.Type.P1_SUPPLY, 0);
		h.setText("2");
		h.setForeground(Color.WHITE);
		h.setHorizontalTextPosition(JLabel.CENTER);
		p1Supply.add(h);
		h.setBounds(xoffset, yoffset + (hexagonsize * 0) + (topoffset * 0), hexagonsize, hexagonsize);
		this.add(h);

		h = new BoardHexagon(BoardHexagon.Type.P1_SUPPLY, 1);
		h.setText("2");
		h.setHorizontalTextPosition(JLabel.CENTER);
		p1Supply.add(h);
		h.setBounds(xoffset, yoffset + (hexagonsize * 1) + (topoffset * 1), hexagonsize, hexagonsize);
		this.add(h);

		yoffset = 58;
		xoffset = 617;

		yoffset -= jumpoffset;
		xoffset += leftoffset;

		h = new BoardHexagon(BoardHexagon.Type.P2_SUPPLY, 0);
		h.setText("2");
		h.setForeground(Color.WHITE);
		h.setHorizontalTextPosition(JLabel.CENTER);
		p2Supply.add(h);
		h.setBounds(xoffset, yoffset + (hexagonsize * 0) + (topoffset * 0), hexagonsize, hexagonsize);
		this.add(h);
		
		h = new BoardHexagon(BoardHexagon.Type.P2_SUPPLY, 1);
		h.setText("2");
		h.setHorizontalTextPosition(JLabel.CENTER);
		p2Supply.add(h);
		h.setBounds(xoffset, yoffset + (hexagonsize * 1) + (topoffset * 1), hexagonsize, hexagonsize);
		this.add(h);

	}

	/**
	 * Initializes common supply buttons
	 */
	protected void initializeCommonSupply() {

		int yoffset = 390;
		int xoffset = 610;
		int hexagonsize = 80;

		BoardHexagon h = null;

		h = new BoardHexagon(BoardHexagon.Type.COMMON_BLACK);
		h.setText("10");
		h.setHorizontalTextPosition(JLabel.CENTER);
		h.setForeground(Color.WHITE);
		blackSupply = h;
		h.setBounds(xoffset, yoffset, hexagonsize, hexagonsize);
		this.add(h);

		h = new BoardHexagon(BoardHexagon.Type.COMMON_WHITE);
		h.setText("10");
		h.setHorizontalTextPosition(JLabel.CENTER);
		whiteSupply = h;
		h.setBounds(xoffset + hexagonsize, yoffset, hexagonsize, hexagonsize);
		this.add(h);

	}

	public JButton getEndMoveButton() {
		return endMoveButton;
	}

	/**
	 * Get board {@link BoardHexagon} given horizontal and vertical position
	 * 
	 * @param x horizontal position
	 * @param y vertical position
	 * @return {@link BoardHexagon};
	 * @return null when position values are invalid;
	 */
	public BoardHexagon getHexagon(int x, int y) {
		return hexagons.get(new Point(x, y));
	}

	/**
	 * Get player 1 personal supply {@link BoardHexagon} given a position
	 * 
	 * @param position of supply {@link BoardHexagon}
	 * @return {@link BoardHexagon} supply;
	 * @return null when position value is invalid;
	 */
	public BoardHexagon getP1Supply(int position) {
		try {
			return p1Supply.get(position);
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * Get player 2 personal supply {@link BoardHexagon} given a position
	 * 
	 * @param position of supply {@link BoardHexagon}
	 * @return {@link BoardHexagon} supply;
	 * @return null when position value is invalid;
	 */
	public BoardHexagon getP2Supply(int position) {
		return p2Supply.get(position);
	}


	/**
	 * Get common black energy supply {@link BoardHexagon}
	 * @return {@link BoardHexagon} black energy supply {@link BoardHexagon};
	 */
	public BoardHexagon getBlackSupply() {
		return blackSupply;
	}

	/**
	 * Get common white energy supply {@link BoardHexagon}
	 * @return {@link BoardHexagon} white energy supply;
	 */
	public BoardHexagon getWhiteSupply() {
		return whiteSupply;
	}

	/**
	 * Overrides paint component to display board image as panel background
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(Images.BOARD, 0, 0, null);
	}
	
	


}
