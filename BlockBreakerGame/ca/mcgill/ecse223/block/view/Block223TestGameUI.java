package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.*;

import ca.mcgill.ecse223.block.application.Block223Application;
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



public class Block223TestGameUI extends JFrame implements Block223PlayModeInterface  {
	
	private static final long serialVersionUID = -5468712039074806735L;
	Block223PlayModeListener bp;


	JPanel playAreaPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	GCanvas playAreaCanvas;
	GRect block;
	GOval ball;
	GRect paddle;
	
	
	public static JButton testGameButton;
	private JTable table;
	
	private String gameNamePlayed;
	
	private DefaultTableModel hallOfFame;
	
	private String columnNames [] = {"name", "score"};
	
	private int start;
	private int end;
	
	
	public Block223TestGameUI() {
		
		setResizable(false);
		setTitle("Block 223 Test Mode");
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
		JScrollPane scrollPane = new JScrollPane();
		

		GroupLayout rightMenuPanel = new GroupLayout(rightPanel);
		rightMenuPanel.setHorizontalGroup(
			rightMenuPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(rightMenuPanel.createSequentialGroup()
					.addGroup(rightMenuPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(rightMenuPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(rightMenuPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lvlLabel, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(livesLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scoreLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(rightMenuPanel.createSequentialGroup()
							.addGap(15)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(rightMenuPanel.createSequentialGroup()
					.addContainerGap(66, Short.MAX_VALUE)
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
					.addContainerGap(38, Short.MAX_VALUE))
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
		
		JButton quitGameButton = new JButton("Close Window");
		quitGameButton.setBounds(418, 499, 162, 23);
		getContentPane().add(quitGameButton);
		
		JButton pauseGameButton = new JButton("Pause");
		pauseGameButton.setBounds(279, 440, 117, 29);
		getContentPane().add(pauseGameButton);
		
		testGameButton = new JButton("Test Game");
		testGameButton.setBounds(279, 402, 117, 29);
		getContentPane().add(testGameButton);
		
		JLabel lblTestGameMode = new JLabel("Test Game Mode");
		lblTestGameMode.setFont(new Font("Lucida Grande", Font.ITALIC, 25));
		lblTestGameMode.setBounds(21, 407, 246, 62);
		getContentPane().add(lblTestGameMode);
		
		testGameButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
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
							
							Block223Controller.testGame(Block223TestGameUI.this);
							while (Block223Controller.getCurrentPlayableGame().getLives() > 0) {
								if (Block223Controller.getCurrentPlayableGame().isPaused()) {
									try {
										refreshGameStats();
									} catch (InvalidInputException e) {
										e.printStackTrace();
									}
									
									String userInputs = bp.takeInputs();

									if (userInputs.contains(" ")) {
										System.out.println("Starting Game again");
										Block223Controller.testGame(Block223TestGameUI.this);
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
						testGameButton.setVisible(true);
						System.out.println("gameover");
					
					}
				};
				Thread threadB = new Thread(runnableB);
				threadB.start();
				try {
					refreshGameStats();
				} catch (InvalidInputException e) {
					e.printStackTrace();
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
				testGameButton.setVisible(true);
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
				Block223Application.setCurrentPlayableGame(null); 
				dispose(); 
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
	
	//refreshHallofFame
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
		System.out.println("The game name is: " + gameNamePlayed);
		
		
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
		if (playAreaCanvas != null) playAreaCanvas.removeAll(); //***CHANGED***
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
