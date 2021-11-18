package view.board.util;

import java.awt.Component;
import java.awt.event.MouseEvent;

import view.board.BoardPanel;

/**
 * Event dispatched by {@link BoardPanel} on click.<br>
 * {@link #getSource()} returns board element clicked.
 * @author douglas
 *
 */
public class BoardEvent extends MouseEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3984552197626980980L;

	public BoardEvent(Component component, MouseEvent event) {
		super(component, event.getID(), event.getWhen(), event.getModifiers(), event.getX(), event.getY(),
				event.getXOnScreen(), event.getYOnScreen(), event.getClickCount(), event.isPopupTrigger(),
				event.getButton());
	}

}
