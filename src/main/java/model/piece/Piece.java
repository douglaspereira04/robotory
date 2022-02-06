package model.piece;

import view.board.BoardHexagon;

public class Piece {


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
	public enum Type {
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

		Object getEnumConstants() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public Piece() {
		
	}
	
	public Piece(Type type) {
		super();
		this.type = type;
	}

	protected Type type = Type.EMPTY;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	
	
}
