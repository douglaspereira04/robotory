package INE5417.Robotory;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import interfaceGrafica.BoardHexagon;
import interfaceGrafica.BoardPanel;
import interfaceGrafica.RobotoryFrame;

public class ExamplePostitionClicks {
	
	public static void main(String args[]) {
		BoardPanel boardPanel = new BoardPanel();
		
		new RobotoryFrame(boardPanel);
		
		
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {

				//boardPanel.getHexagon(i, j) pega a posição i,j
				BoardHexagon hexagon = boardPanel.getHexagon(i, j);
				
				if(hexagon != null) {//para ignorar posições não presentes no board
					hexagon.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							//aqui trata o evento do clique do botao na posicao (i,j)
							//nesse teste ele só printa a posição que foi clicada
							JOptionPane.showMessageDialog(null, hexagon.getPosition());
							
							super.mouseClicked(e);
						}
					});
				}
				
			}
		}
		
		
	}
}
