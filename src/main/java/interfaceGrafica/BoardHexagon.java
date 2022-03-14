package interfaceGrafica;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Label that represents one position in board
 * 
 * @author douglas
 *
 */
public class BoardHexagon extends JLabel {

	/**
	 * Classifies {@link BoardHexagon} function in board.<br>
	 * Possible values are:
	 * <ul>
	 * <li>{@link #WALK_HEX};
	 * <li>{@link #P1_SUPPLY};
	 * <li>{@link #P2_SUPPLY};
	 * <li>{@link #COMMON_BLACK};
	 * <li>{@link #COMMON_WHITE};
	 * </ul>
	 * 
	 * @author douglas
	 */
	public enum Type {
		/**
		 * Is a walking hexagon.
		 */
		WALK_HEX,
		/**
		 * Is a player 1 personal supply hexagon.
		 */
		P1_SUPPLY,
		/**
		 * Is a player 2 personal supply hexagon.
		 */
		P2_SUPPLY,
		/**
		 * Is the common black supply square.
		 */
		COMMON_BLACK,
		/**
		 * Is the common white supply square.
		 */
		COMMON_WHITE;
	}

	/**
	 * Identifies piece occupying {@link BoardHexagon}.<br>
	 * Possible values are:
	 * <ul>
	 * <li>{@link #RED_ROBOT};
	 * <li>{@link #BLACK_ROBOT};
	 * <li>{@link #WHITE_ROBOT};
	 * <li>{@link #BLACK_ENERGY};
	 * <li>{@link #WHITE_ENERGY};
	 * <li>{@link #EMPTY};
	 * <ul>
	 * 
	 * @author douglas
	 */
	public enum Piece {
		/**
		 * A red robot.
		 */
		RED_ROBOT,
		/**
		 * A black robot.
		 */
		BLACK_ROBOT,
		/**
		 * A white robot.
		 */
		WHITE_ROBOT,
		/**
		 * A black energy.
		 */
		BLACK_ENERGY,
		/**
		 * A white energy.
		 */
		WHITE_ENERGY,
		/**
		 * No piece.<br>
		 * The {@link Hexagon} is empty.
		 */
		EMPTY;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8809176813937027938L;

	/**
	 * Size
	 */
	public static final Dimension DIMENSION = new Dimension(60, 60);

	protected Point position = null;
	protected Type type = null;
	protected Piece piece = Piece.EMPTY;

	public BoardHexagon(Type type) {
		this();
		this.type = type;
	}

	public BoardHexagon(Type type, int position) {
		this();
		this.type = type;
		this.position = new Point(position, 0);
	}

	public BoardHexagon(int x, int y) {
		this();
		this.position = new Point(x, y);
		this.type = Type.WALK_HEX;
	}

	public BoardHexagon(Type type, int x, int y) {
		this();
		this.position = new Point(x, y);
		this.type = type;
	}

	public BoardHexagon() {
		super(" ");
		this.setPreferredSize(DIMENSION);
		this.setMinimumSize(DIMENSION);
		this.setVisible(true);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				onMouseOver();
				super.mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				onMouseLeave();
				super.mouseExited(e);
			}
		});
	}

	/**
	 * Default method to be executed when mouse enters this {@link BoardHexagon}.
	 */
	protected void onMouseOver() {
		Icon icon = null;
		if (this.piece != Piece.EMPTY) {
			
			BufferedImage combinedImage = new BufferedImage( 
					DIMENSION.width, 
					DIMENSION.height, 
	                BufferedImage.TYPE_INT_ARGB );

	        Graphics2D g = combinedImage.createGraphics();
	        g.drawImage(getImage(this.piece),0,0,null);
	        g.drawImage(Images.HOVER,0,0,null);
	        g.dispose();
	        icon = new ImageIcon(combinedImage);
			/*ImageIcon pieceIcon = new ImageIcon(getImage(this.piece));
			icon = new CompoundIcon(CompoundIcon.Axis.Z_AXIS, pieceIcon, hover);*/
		} else {
			icon = new ImageIcon(Images.HOVER);
		}
		this.setIcon(icon);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	/**
	 * Default method to be executed when mouse leaves this {@link BoardHexagon}.
	 */
	protected void onMouseLeave() {
		displayPiece();
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	}

	/**
	 * Displays piece image.
	 */
	public void displayPiece() {
		Icon icon = null;
		if (this.piece != Piece.EMPTY && this.piece != null) {
			icon = new ImageIcon(getImage(this.piece));
		} else {
			icon = new ImageIcon();
		}

		if(this.getIcon() != null) {
			if (!this.getIcon().equals(icon)) {
				this.setIcon(icon);
			}
		} else {
			this.setIcon(icon);
		}
	}

	/**
	 * Gets piece {@link Image} given a {@link Piece}.
	 * 
	 * @param piece.
	 * @return {@link Image} or null when {@link Piece#EMPTY}.
	 */
	private Image getImage(Piece piece) {

		if(piece != null) {
			switch (piece) {
			case RED_ROBOT:
				return Images.RED_ROBOT;
			case BLACK_ROBOT:
				return Images.BLACK_ROBOT;
			case WHITE_ROBOT:
				return Images.WHITE_ROBOT;
			case BLACK_ENERGY:
				return Images.BLACK_ENERGY;
			case WHITE_ENERGY:
				return Images.WHITE_ENERGY;
			case EMPTY:
				return null;
			default:
				return null;
			}
		}
		return null;
	}

	/**
	 * Gets a {@link Point} representing the position of walking hexagon, personal supply or
	 * common supply.<br>
	 * Returns: <br>
	 * <ul>
	 * <li>{@link Point} with variable x and y when this has {@link Type#WALK_HEX};
	 * <li>{@link Point} with variable x and fixed y = 0 when this has
	 * {@link Type#P1_SUPPLY} or {@link Type#P2_SUPPLY};
	 * <li>{@link Point} with fixed x =0 and y = 0 when this has
	 * {@link Type#COMMON_BLACK} or {@link Type#COMMON_WHITE}.
	 * </ul>
	 * 
	 * @return {@link Point} to be returned.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Gets this {@link BoardHexagon} function in board.
	 * 
	 * @return {@link Type}.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Gets piece placed in this {@link BoardHexagon}.
	 * 
	 * @return {@link Type}.
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * Sets piece in this {@link BoardHexagon}.
	 * 
	 * @param piece
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

}
