package Main;

import java.awt.Frame;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) {
		
		
		JFrame window = new JFrame("Legend of Fuse");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(Frame.MAXIMIZED_BOTH);
		window.setResizable(true);
		window.pack();
		window.setVisible(true);
		
	}
	
}