package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.*;

import ca.mcgill.ecse223.block.controller.*;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import acm.graphics.*;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



public class Block223PlayGameUI extends JFrame implements Block223PlayModeInterface  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8135162602456544927L;

	Block223PlayModeListener bp;


	//This
	JPanel playAreaPanel = new JPanel();
	JFrame frame = new JFrame();	
	JPanel rightPanel = new JPanel();
	static JComboBox<String> newGamesComboBox = new JComboBox<String>();
	static JComboBox<String> pausedGamesComboBox = new JComboBox<String>();
	
	//This
	GCanvas playAreaCanvas;
	//This
	GRect block;
	GOval ball;
	GRect paddle;
	
	
	public static JButton startGameButton;
	private JTable table;
	
	private String gameNamePlayed;
	
	private DefaultTableModel hallOfFame;
	
	private String columnNames [] = {"name", "score"};
	
	private int start;
	private int end;
	
	
	public Block223PlayGameUI() {
	
		try {
			updateGamesList();
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		setResizable(false);
		setTitle("Block 223 Gameplay");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 564);
		getContentPane().setLayout(null);
		rightPanel.setBounds(386, 0, 194, 390);
		rightPanel.setPreferredSize(new Dimension(200, 330));
		rightPanel.setMinimumSize(new Dimension(200, 330));
		rightPanel.setMaximumSize(new Dimension(200, 330));
		rightPanel.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		getContentPane().add(rightPanel);
		JLabel lvlLabel = new JLabel("Level:");
		JLabel livesLabel = new JLabel("Lives:");
		JLabel scoreLabel = new JLabel("Score:");	
		JLabel hallOfFameLabel = new JLabel("Hall of Fame");
		hallOfFameLabel.setFont(new Font("Arial", Font.BOLD, 15));
		JButton prevButton = new JButton("<--");
		JButton nextButton = new JButton("-->");
		JLabel lblPrevious = new JLabel("Previous"); 
		JLabel lblNext = new JLabel("Next");
		
		
		JScrollPane scrollPane = new JScrollPane();
			
		GroupLayout rightMenuPanel = new GroupLayout(rightPanel);
		rightMenuPanel.setHorizontalGroup(
			rightMenuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(rightMenuPanel.createSequentialGroup()
					.addGroup(rightMenuPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(rightMenuPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(rightMenuPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lvlLabel, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(livesLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scoreLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(rightMenuPanel.createSequentialGroup()
							.addGap(25)
							.addGroup(rightMenuPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(prevButton)
								.addComponent(lblPrevious, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(rightMenuPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNext, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGroup(rightMenuPanel.createSequentialGroup()
									.addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addGap(8))))
						.addGroup(rightMenuPanel.createSequentialGroup()
							.addGap(15)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, rightMenuPanel.createSequentialGroup()
					.addContainerGap(60, Short.MAX_VALUE)
					.addComponent(hallOfFameLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
		);
		rightMenuPanel.setVerticalGroup(
			rightMenuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(rightMenuPanel.createSequentialGroup()
					.addGap(28)
					.addComponent(lvlLabel)
					.addGap(18)
					.addComponent(livesLabel)
					.addGap(18)
					.addComponent(scoreLabel)
					.addGap(18)
					.addComponent(hallOfFameLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(rightMenuPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(prevButton)
						.addComponent(nextButton))
					.addGap(8)
					.addGroup(rightMenuPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrevious)
						.addComponent(lblNext)))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		rightPanel.setLayout(rightMenuPanel);
		
		playAreaPanel.setBounds(new Rectangle(0, 0, 390, 390));
		playAreaPanel.setPreferredSize(new Dimension(390, 390));
		playAreaPanel.setMinimumSize(new Dimension(390, 390));
		playAreaPanel.setMaximumSize(new Dimension(390, 390));
		playAreaPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		playAreaPanel.setBackground(Color.ORANGE);
		getContentPane().add(playAreaPanel);
		playAreaPanel.setLayout(null);
		
		JPanel playGamePanel = new JPanel();
		playGamePanel.setBounds(0, 0, 150, 150);
		getContentPane().add(playGamePanel);
		playGamePanel.setLayout(null);
		playGamePanel.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0)));
		
		JButton quitGameButton = new JButton("Quit Game");
		quitGameButton.setBounds(418, 499, 162, 23);
		getContentPane().add(quitGameButton);
		
		JButton pauseGameButton = new JButton("Pause");
		pauseGameButton.setBounds(279, 499, 111, 23);
		getContentPane().add(pauseGameButton);
		
		JButton viewHOFButton = new JButton("View Hall of Fame");
		viewHOFButton.setBounds(418, 398, 161, 29);
		getContentPane().add(viewHOFButton);
		newGamesComboBox.setBounds(10, 402, 253, 20);
		getContentPane().add(newGamesComboBox);
		
		newGamesComboBox.setModel(new DefaultComboBoxModel(new String[] {"New playable games"}));
		
		JButton refreshGamesButton = new JButton("Refresh Games");
		refreshGamesButton.setBounds(275, 398, 117, 29);
		getContentPane().add(refreshGamesButton);
		
		startGameButton = new JButton("Start/Resume");
		startGameButton.setBounds(275, 429, 117, 29);
		getContentPane().add(startGameButton);
		

		pausedGamesComboBox.setModel(new DefaultComboBoxModel(new String[] {"Paused games"}));
		pausedGamesComboBox.setBounds(10, 432, 253, 20);
		getContentPane().add(pausedGamesComboBox);
		
		JButton mostRecentButton = new JButton("Most Recent Entry");
		mostRecentButton.setBounds(418, 429, 162, 29);
		getContentPane().add(mostRecentButton);
		
		JButton selectGameButton = new JButton("Select Game");
		selectGameButton.setBounds(10, 464, 117, 29);
		getContentPane().add(selectGameButton);
		
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean a = ((newGamesComboBox.getSelectedItem()).equals("New playable games"));
				boolean b = ((pausedGamesComboBox.getSelectedItem()).equals("Paused games"));
				
				if(a && b) {
					JOptionPane.showMessageDialog(frame, "Please select a game from one of the lists.");
				}
				
				String selectedGame;
				String gameName = "";
				int tokens = 0;
				String id = "";
				String id_input = "";
				String[] tokenNames = null;
		
				if (newGamesComboBox.getSelectedItem() != null && pausedGamesComboBox.getSelectedItem() != null) {
					
					if (!a || !b) {	
						
						if(!a) {						
								gameName = String.valueOf(newGamesComboBox.getSelectedItem());					
								gameNamePlayed = gameName;						
						}
						else {				
							selectedGame = String.valueOf(pausedGamesComboBox.getSelectedItem());
						 	tokenNames = selectedGame.split(" ");
						 	tokens = tokenNames.length;	
						 	gameName = tokenNames[0];		 	
						 	gameNamePlayed = gameName;		 
						 for (int i = 1; i < tokens - 1; i++) { 
							 gameName += " ";	
							 gameName += tokenNames[i];
								
							}
						 
						 	id = tokenNames[tokens-1];
						 	for (int j=1; j<id.length()-1; j++) {
						 			id_input+=id.charAt(j);
						 	}			 	
						 	gameNamePlayed = gameName;	
						 	
						}
				
							try {
								if (!a) {
									Block223Controller.selectPlayableGame(gameName, -1);
								}
								else {
										Block223Controller.selectPlayableGame(null, Integer.valueOf(id_input)); 
								}
							} catch (InvalidInputException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (playAreaCanvas != null) {
								playAreaPanel.remove(playAreaCanvas);
								playAreaCanvas.removeAll();
							}
							bp = new Block223PlayModeListener();
							Runnable runnableA = new Runnable() {
								@Override
								public void run() {
									playAreaCanvas = new GCanvas();
									playAreaCanvas.setVisible(true);
									playAreaCanvas.setBounds(new Rectangle(0, 0, 390, 390));
									playAreaCanvas.setBackground(Color.white);
									playAreaPanel.add(playAreaCanvas);
									playAreaCanvas.addKeyListener(bp);
									refresh();
								}
							};
							Thread threadA = new Thread(runnableA);
							threadA.start();
							
							try {
								threadA.join();
							} catch (InterruptedException e1) {
							}

							
							Runnable runnableB = new Runnable() {
								@Override
								public void run() {
									try {
										startGameButton.setVisible(false);
										Block223Controller.startGame(Block223PlayGameUI.this);
										while (Block223Controller.getCurrentPlayableGame().getLives() > 0) {
											if (Block223Controller.getCurrentPlayableGame().isPaused()) {
												try {
													refreshGameStats();
												} catch (InvalidInputException e) {
													e.printStackTrace();
												}
												
												String userInputs = bp.takeInputs();

												if (userInputs.contains(" ")) {
													Block223Controller.startGame(Block223PlayGameUI.this);
												}
												try {
													Thread.sleep(100);
												} catch (InterruptedException e) {
													e.printStackTrace();
												} 
											}
										}
									} catch (InvalidInputException e) {
									}
									startGameButton.setVisible(true);
									refreshHallOfFame();		
								}
							};
							Thread threadB = new Thread(runnableB);
							threadB.start();
							try {
								updateGamesList();
							} catch (InvalidInputException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								refreshGameStats();
							} catch (InvalidInputException e) {
								e.printStackTrace();
							}
					}
				}
			}
		});
		
		
		
		
		viewHOFButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(gameNamePlayed==null || gameNamePlayed.equals("")) {
					JOptionPane.showMessageDialog(frame, "Please select a game from one of the lists to view its Hall of Fame.");
				}
				else {
				refreshHallOfFame();
				}
			}
				
		});
		
		selectGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
		
				boolean a = ((newGamesComboBox.getSelectedItem()).equals("New playable games"));
				boolean b = ((pausedGamesComboBox.getSelectedItem()).equals("Paused games"));
				
				String selectedGame;
				String gameName = "";
				int tokens = 0;
				String[] tokenNames = null;
				
				if (!a || !b) {	
					if(!a) {
							gameName = String.valueOf(newGamesComboBox.getSelectedItem());
							gameNamePlayed = gameName;
					}
					else {
						selectedGame = String.valueOf(pausedGamesComboBox.getSelectedItem());
					 	tokenNames = selectedGame.split(" ");
					 	tokens = tokenNames.length;	
					 	gameName = tokenNames[0];
					 	
					 	gameNamePlayed = gameName;
					 
					 for (int i = 1; i < tokens - 1; i++) { 
						 gameName += " ";	
						 gameName += tokenNames[i];		
						}
					 
					 	gameNamePlayed = gameName;	
					 	
					}
					
				}
			
			}
		});
		
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				
				table.removeAll();
				hallOfFame = new DefaultTableModel(0, 0);
				hallOfFame.setColumnIdentifiers(columnNames);
				table.setModel(hallOfFame);
				end = end + 10;
				start = start+10;
				try {
					for(TOHallOfFameEntry to : Block223Controller.getHallOfFameWithName(gameNamePlayed,start, end).getEntries()) {
						Object[] obj = {to.getPlayername(),to.getScore()};
						hallOfFame.addRow(obj);
					}
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				Dimension d = table.getPreferredSize();
			}
		});
		mostRecentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				
				if(gameNamePlayed==null || gameNamePlayed.equals("")) {
					JOptionPane.showMessageDialog(frame, "Please select a game from one of the lists to view its Hall of Fame.");
				}
				else {
				table.removeAll();
				hallOfFame = new DefaultTableModel(0, 0);
				hallOfFame.setColumnIdentifiers(columnNames);
				table.setModel(hallOfFame);
				
				try {
					for(TOHallOfFameEntry to : Block223Controller.getHallOfFameWithMostRecentEntry(10,gameNamePlayed).getEntries()) {
						Object[] obj = {to.getPlayername(),to.getScore()};
						hallOfFame.addRow(obj);
						;
					}
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	

				Dimension d = table.getPreferredSize();	
					}
				}
			});
		
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				
				table.removeAll();
				hallOfFame = new DefaultTableModel(0, 0);
				hallOfFame.setColumnIdentifiers(columnNames);
				table.setModel(hallOfFame);
				int newEnd = start;
				start = start - 10;
				try {
					for(TOHallOfFameEntry to : Block223Controller.getHallOfFameWithName(gameNamePlayed,start, newEnd).getEntries()) {
						Object[] obj = {to.getPlayername(),to.getScore()};
						hallOfFame.addRow(obj);
					}
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				Dimension d = table.getPreferredSize();
				
			}
		});
		refreshGamesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					updateGamesList();
				} catch (InvalidInputException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});

		pauseGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAreaCanvas.dispatchEvent(new KeyEvent(playAreaCanvas, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, ' '));

				if (playAreaCanvas != null) {
					playAreaPanel.remove(playAreaCanvas);
					playAreaCanvas.removeAll();
				}
				startGameButton.setVisible(true);
				newGamesComboBox.setVisible(true);
	
				Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
				for (Thread thread : threadSet) {
					if (thread.getName().contains("Thread-")) {
						thread.stop();
					}
				}

			}
		});
		
		

		quitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	private void refreshGameStats() throws InvalidInputException {
		TOCurrentlyPlayedGame currentGame = Block223Controller.getCurrentPlayableGame();
		int livesRemaining = currentGame.getLives();
		int currentLevel = currentGame.getCurrentLevel();
		int currentScore = currentGame.getScore();
		
		Component[] components = rightPanel.getComponents();
		for (Component component : components) {
			if (component instanceof JLabel) {
				JLabel label = (JLabel) component;
				if (label.getText().startsWith("Level:")) {
					label.setText("Level: " + currentLevel);
				}
				else if (label.getText().startsWith("Lives:")) {
					label.setText("Lives: " + livesRemaining);
				}
				else if (label.getText().startsWith("Score:")) {
					label.setText("Score: " + currentScore);
				}
			}
		}
	}

	private void refreshHallOfFame() {
		table.removeAll();
		hallOfFame = new DefaultTableModel(0, 0);
		hallOfFame.setColumnIdentifiers(columnNames);
		table.setModel(hallOfFame);
		start = 0;
		end = 10;
		
		
		try {
			for(TOHallOfFameEntry to : Block223Controller.getHallOfFameWithName(gameNamePlayed,start, end).getEntries()) {
				Object[] obj = {to.getPlayername(),to.getScore()};
				hallOfFame.addRow(obj);
				;
			}
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		Dimension d = table.getPreferredSize();	
		
		
	}
	
	public static void updateGamesList() throws InvalidInputException {
		newGamesComboBox.removeAllItems();
		pausedGamesComboBox.removeAllItems();
		
		newGamesComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"New playable games"}));
		pausedGamesComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Paused games"}));
		
		
		List<TOPlayableGame> games = Block223Controller.getPlayableGames();
		for(TOPlayableGame game : games) {
			if (game.getCurrentLevel() == 0) {
				newGamesComboBox.addItem(game.getName());			
			}
			
			else {
				pausedGamesComboBox.addItem(game.getName() + " (" + game.getNumber()+ ")");
				
				
			}
		}
		newGamesComboBox.setSelectedIndex(0);
		pausedGamesComboBox.setSelectedIndex(0);
	}
	
	@Override
	public String takeInputs() {
		if (bp == null) {
			return "";
		}
		return bp.takeInputs();
	}
	
	@Override
	public void refresh() {
		TOCurrentlyPlayedGame currentGame = null;
		try {
			currentGame = Block223Controller.getCurrentPlayableGame();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TOCurrentBlock> blocks = null;
		if (currentGame != null) { 
			blocks = currentGame.getBlocks(); 
		}
		else {
			return;
		}
		if (Block223Controller.getBallSpeedX() >= 10 || Block223Controller.getBallSpeedY() >= 10) {
			Block223Controller.resetBallSpeed();
		}
		if (ball != null) {
			if (ball.getX() > 390) Block223Controller.outOfPlayAreaX390();
			if (ball.getX() < 0) Block223Controller.outOfPlayAreaX0();
			if (ball.getY() > 390) Block223Controller.outOfPlayAreaY390();
			if (ball.getY() < 0) Block223Controller.outOfPlayAreaY0();
		}
		playAreaCanvas.removeAll();
		ball = new GOval(Block223Controller.getBallDiameter(),Block223Controller.getBallDiameter());
		ball.setLocation(currentGame.getCurrentBallX()-5, currentGame.getCurrentBallY()-5);
		ball.setFillColor(Color.black);
		ball.setFilled(true);
		playAreaCanvas.add(ball);
		
		double paddleLength = currentGame.getCurrentPaddleLength();
		paddle = new GRect(paddleLength, Block223Controller.getPaddleWidth());
		paddle.setLocation(currentGame.getCurrentPaddleX(), 355);
		paddle.setFillColor(Color.black);
		paddle.setFilled(true);
		playAreaCanvas.add(paddle);
		
		for (TOCurrentBlock cblock : blocks) {
			Color color = new Color(cblock.getRed(), cblock.getGreen(), cblock.getBlue());
			block = new GRect(Block223Controller.getBlockSize(), Block223Controller.getBlockSize());  
			block.setFillColor(color);
			block.setColor(Color.black);
			block.setFilled(true);
			playAreaCanvas.add(block, cblock.getX(), cblock.getY());
		}
		try {
			refreshGameStats();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		// TODO Auto-generated method stub
		
	}
}
