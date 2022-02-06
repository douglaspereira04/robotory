package view.board;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.board.util.BoardEvent;
import view.resources.Images;
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
				addBoardEvent(h);
				h.setBounds(xoffset, yoffset + (hexagonsize * y) + (topoffset * y), hexagonsize, hexagonsize);
				this.add(h);
			}
			x++;
			yoffset -= jumpoffset;
			xoffset += leftoffset;
		}

		x = 4;
		yoffset += (jumpoffset * 2);
		for (int i = 5; i > 3; i--) {
			for (int y = 0; y < i; y++) {
				h = new BoardHexagon(x, y);
				hexagons.put(new Point(x, y), h);
				addBoardEvent(h);
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

		for (int y = 0; y < 3; y++) {
			h = new BoardHexagon(BoardHexagon.Type.P1_SUPPLY, y);
			p1Supply.add(h);
			addBoardEvent(h);
			h.setBounds(xoffset, yoffset + (hexagonsize * y) + (topoffset * y), hexagonsize, hexagonsize);
			this.add(h);
		}

		yoffset -= jumpoffset;
		xoffset += leftoffset;
		h = new BoardHexagon(BoardHexagon.Type.P1_SUPPLY, 3);
		p1Supply.add(h);
		addBoardEvent(h);
		h.setBounds(xoffset, yoffset + (hexagonsize * 2) + (topoffset * 2), hexagonsize, hexagonsize);
		this.add(h);

		yoffset = 58;
		xoffset = 617;

		h = new BoardHexagon(BoardHexagon.Type.P2_SUPPLY, 0);
		p2Supply.add(h);
		addBoardEvent(h);
		h.setBounds(xoffset, yoffset, hexagonsize, hexagonsize);
		this.add(h);

		yoffset -= jumpoffset;
		xoffset += leftoffset;

		for (int y = 0; y < 3; y++) {
			h = new BoardHexagon(BoardHexagon.Type.P2_SUPPLY, y + 1);
			p2Supply.add(h);
			addBoardEvent(h);
			h.setBounds(xoffset, yoffset + (hexagonsize * y) + (topoffset * y), hexagonsize, hexagonsize);
			this.add(h);
		}

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
		addBoardEvent(h);
		h.setBounds(xoffset, yoffset, hexagonsize, hexagonsize);
		this.add(h);

		h = new BoardHexagon(BoardHexagon.Type.COMMON_WHITE);
		h.setText("10");
		h.setHorizontalTextPosition(JLabel.CENTER);
		whiteSupply = h;
		addBoardEvent(h);
		h.setBounds(xoffset + hexagonsize, yoffset, hexagonsize, hexagonsize);
		this.add(h);

	}

	/**
	 * Ensure component click event to be dispatched to board itself Make possible
	 * to handle every board {@link BoardHexagon} click in board click event listener See
	 * {@link BoardEvent}
	 * 
	 * @param component whose click event will dispatch an board event
	 */
	protected void addBoardEvent(Component component) {
		BoardPanel board = this;
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BoardEvent event = new BoardEvent(component, e);
				board.dispatchEvent(event);
				super.mouseClicked(e);
			}
		});
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
