package Sokoban;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.swing.*;

public class Options extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4060267970477907153L;
	
	private MapGenerator mapGen;
	private Game game;
	private String map;
	private JFrame j;
	private JButton btnBack, btnSave;
	//private JButton btnSave;
	private JTextArea area; 
	private Handler handler;
	private JOptionPane opt;
	
	public Options(Game game,Handler handler, int width, int height){
		mapGen = new MapGenerator();
		this.game = game;
		this.handler =handler;
		this.setPreferredSize(new Dimension(width,height));
		this.setMaximumSize(new Dimension(height, height));
		this.setMinimumSize(new Dimension(width, height));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		initComponents();
		pack();
	}
	


	private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.gray);
    	JPanel panel = new JPanel();
    	JPanel panel1 = new JPanel();
    	//panel.setSize(5,2);
    	
    	
    	JLabel box = new JLabel(new ImageIcon("Assets/box32.png"));
    	JLabel boxChar = new JLabel("-> 'box'");
    	//box.setBounds(50,100, 100,30); 
    	//box.
    	JLabel player = new JLabel(new ImageIcon("Assets/player32.png"));
    	JLabel playerChar = new JLabel("-> '@'");
    	
		JLabel wall = new JLabel(new ImageIcon("Assets/wall32.png"));
    	JLabel wallChar = new JLabel("-> '#'");
    	
    	JLabel storage = new JLabel(new ImageIcon("Assets/storage32.png"));
    	JLabel storageChar = new JLabel("-> 'x'");
    	
    	JLabel emptySpace = new JLabel(new ImageIcon("Assets/sand32.png"));
    	JLabel emptySpaceChar = new JLabel("-> '-'");
    	
		area = new JTextArea(15, 15);
		setCharacterOnly(area);
		//area.setBackground(Color.gray);
        btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
      	

        panel1.add(player);
        panel1.add(playerChar);
        
        panel1.add(box);
        panel1.add(boxChar);
        
        panel1.add(wall);
        panel1.add(wallChar);
        
        panel1.add(storage);
        panel1.add(storageChar);
        
        panel1.add(emptySpace);
        panel1.add(emptySpaceChar);
        
        panel1.add(area);
        panel1.add(btnSave);
        panel1.add(btnBack);
        
        opt = new JOptionPane("Do you want to create this map?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
        

        
        JLabel info = new JLabel("Use these characters to create correct Sokoban map");
        //this.add(panel,BorderLayout.SOUTH);
        this.add(info, BorderLayout.NORTH);
        this.add(panel1, BorderLayout.CENTER);
        //this.add(box);
    }
	
	public void tick() {
		
	}
	public void render() {
		//JPanel panel = new JPanel();
		//g.add()
		//initComponents();
		
		
		
		//g.setColor(Color.white);
		//g.drawString("Options", 100, 100);
	}
	public void close(){
		this.setVisible(false);
	}
	
	public static void setCharacterOnly(JTextArea jTextField){
	    jTextField.addKeyListener((KeyListener) new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	         char[] chars =	{'#','-', '@', 'b', 'x'};
	         
	           char c = e.getKeyChar();
	           if (Character.isDigit(c) || (c != '#') && (c != '-') && (c != '#') && (c != 'b') && (c != 'x') && (c != '@')){
	                e.consume();
	              }
	         }
	    });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnBack) {
    		Window.frame.setVisible(true);
    		game.gameState = STATE.Menu;
    		game.paused = false;

    		close();
		}
		if(e.getSource()== btnSave) {
			map = area.getText();
			
	        JDialog jd = opt.createDialog(this, "Verify");
	        jd.setVisible(true);
	        if(opt.getValue().equals(0)) {
				try {
					mapGen.save(map);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				game.restart();
				area.setText("");
				JOptionPane.showMessageDialog (null, "Created Successfully!", "Sokoban Map", JOptionPane.INFORMATION_MESSAGE);
	        }
			
		}
		
	}
	

}
