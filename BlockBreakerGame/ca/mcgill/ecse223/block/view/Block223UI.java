package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class Block223UI extends JFrame{

	private JFrame frame;
//data element
	 private HashMap< Integer, String> games;
	 JComboBox<String> availableGamesComboBox;

	/**
	 * Create the application.
	 */
	public Block223UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		availableGamesComboBox = new JComboBox<String>(new String[0]);
		
		JLabel availableGamesLabel = new JLabel("Available Games");
		
		JButton playGameButton = new JButton("Play Game");
		
		JButton btnNewButton = new JButton("Get Playable Games");
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNewButtonActionPerformed(evt);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(21, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(availableGamesLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(availableGamesComboBox, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(playGameButton)))
					.addGap(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(availableGamesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(availableGamesLabel)
						.addComponent(playGameButton))
					.addContainerGap(228, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	
		playGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playGameButtonActionPerformed(evt);
			}	
		});
	}
	
	private void playGameButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Block223PlayGameUI block = new Block223PlayGameUI(); 
		block.setVisible(true);
	}
	private void btnNewButtonActionPerformed(ActionEvent evt) {
		try {
			refreshGames();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	private void refreshGames() throws InvalidInputException{
		games = new HashMap<Integer, String>();
		availableGamesComboBox.removeAllItems();
		Integer index = 0;
		if(Block223Controller.getPlayableGames().size()==0) {
			System.out.println("No game");
		}
		for (TOPlayableGame game : Block223Controller.getPlayableGames()) {
			games.put(index, game.getName());
			availableGamesComboBox.addItem(game.getName());
			index++;
			}
		availableGamesComboBox.setSelectedIndex(-1);
	}
}
