package interfaceGrafica;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Static class to load game images as {@link Image}
 * @author douglas
 *
 */
public class Images {

	public static final Image BLACK_ENERGY = getImage("black_energy.png", 60, 60);
	public static final Image BLACK_ROBOT = getImage("black_robot.png", 60, 60);
	public static final Image BOARD = getImage("board.png", 780, 530);
	public static final Image RED_ROBOT = getImage("red_robot.png", 60, 60);
	public static final Image HOVER = getImage("translucent.png", 60, 60);
	public static final Image WHITE_ENERGY = getImage("white_energy.png", 60, 60);
	public static final Image WHITE_ROBOT = getImage("white_robot.png", 60, 60);

	private static Image getImage(String name, int width, int height) {
		Image board = null;
		try {
			board = ImageIO.read(BoardPanel.class.getResource("/view/board/"+name));
			//for jar board = ImageIO.read(Robotory.class.getResourceAsStream("/resources/view/board/"+name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		board = board.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return board;
	}
	
}
