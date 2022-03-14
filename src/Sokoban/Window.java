package Sokoban;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1785023453236957334L;
	protected static final String game = null;
	protected static JFrame frame, frame1;
	
	public Window(int width, int height , String title, Game game) {
		frame = new JFrame(title);
		frame1 = new JFrame("map");

		setFrame(frame, width, height);
		frame.add(game);
		frame.setVisible(true);
		frame.setFocusable(true);
		game.start();
		}//
	//}
	
	public void setFrame(JFrame frame, int width, int height) {
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
}
