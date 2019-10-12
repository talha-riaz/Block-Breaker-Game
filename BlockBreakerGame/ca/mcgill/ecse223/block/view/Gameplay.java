package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	//private TOCurrentlyPlayedGame game;
	//private int totalBlocks = game.getBlocks().size();
	private double paddleLength = 50;
	private double paddleWidth = 10;
	
	
	
	


	/**
	 * Create the panel.
	 */
	public Gameplay() {	
		//try {
		//game = Block223Controller.getCurrentPlayableGame();
		//}
		//catch (Exception e) {
		//	e.printStackTrace();
		//}	
		
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	}
	
	public void paint (Graphics g) {
		//background
		g.setColor(Color.orange);
		g.fillRect(0, 0, 390, 390);
		
		//borders
		g.setColor(Color.black);
		
		g.fillRect(0, 0, 3, 390);
		g.fillRect(0, 0, 390, 3);
		g.fillRect(388, 0, 3, 390);
		g.fillRect(0, 390, 390, 3);
			
		//paddle
		
		g.setColor(Color.green);
		g.fillRect(390/2, 390-30,(int) paddleLength, (int) paddleWidth);
		
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(390/2, 390/2, 10, 10);
		
	}
	
	

	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
