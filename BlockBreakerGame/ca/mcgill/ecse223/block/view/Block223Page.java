package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import acm.gui.TableLayout;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.model.Admin;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class Block223Page extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 669940174014136227L;

	//private instance variables
	   private int FRAME_WIDTH = 1500 , FRAME_HEIGHT = 1000;
	   private int precision = 0;
	   private JTextField field1;							//input field object
	   private JTextField field2;							//output field object
	   private JTextField precisionBox;						//precision indicator field object	//stores the final result of the input expression
	//register
	   private JLabel usernameLabel;
	   private JTextField usernameField;
	   private JLabel playerPassword;
	   private JTextField playerPasswordField;
	   private JLabel adminLabel;
	   private JTextField adminPassword;
	   private JButton registerButton;
	   
	   //login
	   private JLabel usernameLabel1;
	   private JTextField usernameField1;
	   private JLabel adminLabel2;
	   private JTextField password;
	   private JButton loginButton;
	   //
	   private JButton logoutButton;

	 //create a game
	   private JTextField gameNameField;
	   private JButton createGameButton;
	   private JLabel createGameLabel;

	   //define game settings
	   
	   private JLabel nrLevelsLabel;
	   private JTextField nrLevelsField;
	   private JLabel nrBlocksperlevelLabel;
	   private JTextField nrBlocksperlevelField;
	   private JLabel minBallSpeedXLabel;
	   private JTextField minBallSpeedXField;
	   private JLabel minBallSpeedYLabel;
	   private JTextField minBallSpeedYField;
	   private JLabel ballSpeedIncreaseFactorLabel;
	   private JTextField ballSpeedIncreaseFactorField;
	   private JLabel maxPaddleLengthLabel;
	   private JTextField maxPaddleLengthField;
	   private JLabel minPaddleLengthLabel;
	   private JTextField minPaddleLengthField;
	   private JButton defineSettingsButton;
	   //select game
	   private JButton selectGameButton;
	   private JButton refreshGameButton;
	   //delete game
	   private JComboBox<String> currentDesignableGames;
	   private JButton deleteGameButton;

	   //update game
	   private JLabel availableGamesLabel;
	   private JComboBox currentDesignableGames2;
	   private JLabel selectedGamelabel;
	   private JTextField selectionDisplayField;
	   private JLabel updatedGameNameLabel;
	   private JTextField updatedGameNameField;
	   private JLabel updatedNrLevelsLabel;
	   private JTextField updatedNrLevelsField;
	   private JLabel updatedNrBlocksperlevelLabel;
	   private JTextField updatedNrBlocksperlevelField;
	   private JLabel updatedMinBallSpeedXLabel;
	   private JTextField updatedMinBallSpeedXField;
	   private JLabel updatedMinBallSpeedYLabel;
	   private JTextField updatedMinBallSpeedYField;
	   private JLabel updatedBallSpeedIncreaseFactorLabel;
	   private JTextField updatedBallSpeedIncreaseFactorField;
	   private JLabel updatedMaxPaddleLengthLabel;
	   private JTextField updatedMaxPaddleLengthField;
	   private JLabel updatedMinPaddleLengthLabel;
	   private JTextField updatedMinPaddleLengthField;
	   private JButton updateSettingsButton;

	   // add block
	   private JLabel redLabel;
	   private JTextField redTextField; 
	   private JLabel pointsLabel;
	   private JTextField pointsTextField;
	   private JLabel greenLabel;
	   private JTextField greenTextField;
	   private JLabel blueLabel;
	   private JTextField blueTextField;
	   private JButton addBlockButton;
	   //delete block
	   
	 //selectedBlock
	   
	   private JLabel availableBlocksLabel;
	   private JComboBox<String> availableBlocks;
	   private JLabel selectedBlockLabel;
	   private JTextField selectedBlockField;
	  
	   private JLabel availableGamesLabel1;
	   private JComboBox currentDesignableGames3;
	   private JLabel selectedGamelabel1;
	   private JTextField selectionDisplayField1;
	   private JLabel blocksInGameLabel;
	   private JComboBox blocksInGame;
	   private JButton deleteBlockButton;
	  
	   //update block
	   private JButton updateBlockButton;
	   private JLabel redLabel1;
	   private JTextField redTextField1; 
	   private JLabel pointsLabel1;
	   private JTextField pointsTextField1;
	   private JLabel greenLabel1;
	   private JTextField greenTextField1;
	   private JLabel blueLabel1;
	   private JTextField blueTextField1;
	   
	 //new feature8
	   private JLabel levelLabel;
	   private JTextField levelTextField;
	   private JLabel level;
	   private JLabel blockIDLabel1;
	   private JLabel gridHorizontalPosition;
	   private JTextField gridHorizontalField1; 
	   private JLabel gridVerticalPosition;
	   private JTextField gridVerticalField1;
	   
	   
	   //moveBlock feature 9
	   
	   private JLabel initVertPosLabel;
	   private JLabel initHorPosLabel;
	   private JLabel finalVertPosLabel;
	   private JLabel finalHorPosLabel;
	   
	   private JTextField initVertPosField;
	   private JTextField initHorPosField;
	   private JTextField finalVertPosField;
	   private JTextField finalHorPosField;
	   
	   private JButton moveBlockButton;
	   
	   
	   
	   
	   //feature10
	   
	   private JLabel Level;
	   private JTextField Level1;
	   private JButton deleteBlockLevel;
	   private HashMap<Integer, String> blocksInLevel;
	   
	   private JLabel gridVertical2;
	   private JTextField gridVertical3;
	   
	   private JLabel gridHorizontal2;
	   private JTextField gridHorizontal3;
	   
	   private JButton selectLevelButton;
	   private JComboBox<String> blocksInLevelList;
	   //save game
	   private JLabel availableGamesLabel4;
	   private JComboBox currentDesignableGames6;
	   private JLabel selectedGamelabel4;
	   private JTextField selectionDisplayField4;
	   private JButton saveGameButton;
	   
	   //selectedLevelVariable
	   private int levelSelected;
	
	   

	   
	   //data elements
	   private HashMap< Integer, String> games;
	   
	   private HashMap<Integer, String> blocks;
	   //error
	   private JLabel errorMessage;
	   private JTextField errorField;
	   private String error = "";
	public Block223Page() {
		initComponents();
		refreshData();
	}
	private void initComponents() {
		//frame dimensions
				setSize(FRAME_WIDTH,FRAME_HEIGHT);
				
				TableLayout layout = new TableLayout(38, 14);
				
				setLayout(layout);
				
				
				setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				setTitle("Block223");
				
				//Register
				usernameLabel = new JLabel ("Username: ");
				add(usernameLabel);
				
				usernameField = new JTextField ();
				add(usernameField, "gridwidth=2");
				
				JLabel space = new JLabel (" ");
				add(space);
				
				playerPassword = new JLabel ("Player Password: ");
				add(playerPassword);
				
			    playerPasswordField = new JTextField ();
				add(playerPasswordField, "gridwidth=2");
				
				JLabel space2 = new JLabel (" ");
				add(space2);
				
				adminLabel = new JLabel ("Admin Password: ");
				add(adminLabel);
				
				adminPassword = new JTextField ();
				add(adminPassword, "gridwidth=2");
				
				JLabel space3 = new JLabel (" ");
				add(space3);
				
				registerButton = new JButton ("Register");
				add(registerButton, "gridwidth=2");
				
				JSeparator horizontalLineTopAA = new JSeparator();
				add(horizontalLineTopAA, "gridwidth=14");
				
				//-----------------------------------
				
				//login
				usernameLabel1 = new JLabel ("Username: ");
				add(usernameLabel1);
				
				usernameField1 = new JTextField ();
				add(usernameField1, "gridwidth=2");
				
				JLabel space4 = new JLabel (" ");
				add(space4);
				
				adminLabel2 = new JLabel ("Password: ");
				add(adminLabel2);
				
				password = new JTextField ();
				add(password, "gridwidth=2");
				
				JLabel space5 = new JLabel (" ");
				add(space5, "gridwidth=2");
				
				
		        loginButton = new JButton ("Login");
				add(loginButton, "gridwidth=1");
				
				JButton playGame = new JButton("Play Game");
				add(playGame, "gridwidth=1");
				
				JButton testGame = new JButton("Test Game");
				add(testGame,"gridwidth=1");
				
				logoutButton = new JButton("Logout");
				add(logoutButton, "gridwidth=1");
				
				
				JSeparator horizontalLineTopA = new JSeparator();
				add(horizontalLineTopA, "gridwidth=14");
				
				
				//createGame
				createGameLabel = new JLabel("Game Name:   ");
				add(createGameLabel, "gridwidth=1");
				
				
				field1 = new JTextField(10);
				add(field1, "gridwidth=2");
				
				
				createGameButton = new JButton("Create");
				
			
				JLabel ff = new JLabel("    ");
				add(ff,"gridwidth=9");
				
				add(createGameButton, "gridwidth=2");
				//---------------------------------------------
				//defineSettings
				JSeparator horizontalLineTopB = new JSeparator();
				add(horizontalLineTopB, "gridwidth=14");
				
				nrLevelsLabel = new JLabel("No. of levels:");
				add(nrLevelsLabel);
				
				nrLevelsField = new JTextField();
				add(nrLevelsField, "gridwidth=1");
				

				
				nrBlocksperlevelLabel = new JLabel(" No. of blocks per level:");
				add(nrBlocksperlevelLabel, "gridwidth=2");
				
				nrBlocksperlevelField = new JTextField();
				add(nrBlocksperlevelField);
				
				minBallSpeedXLabel = new JLabel("minBallSpeedX:");
				add(minBallSpeedXLabel);
				
				minBallSpeedXField = new JTextField();
				add(minBallSpeedXField);
				
				minBallSpeedYLabel = new JLabel("minBallSpeedY:");
				add(minBallSpeedYLabel);
				
				minBallSpeedYField = new JTextField();
				add(minBallSpeedYField);
				
				ballSpeedIncreaseFactorLabel = new JLabel(" Speed Increase Factor:");
				add(ballSpeedIncreaseFactorLabel, "gridwidth=2");
				
				ballSpeedIncreaseFactorField = new JTextField();
				add(ballSpeedIncreaseFactorField);
				
				JLabel s2 = new JLabel("    ");
				add(s2,"gridwidth=2");
				
				maxPaddleLengthLabel = new JLabel("Max paddle length:");
				add(maxPaddleLengthLabel);
				
				maxPaddleLengthField = new JTextField();
				add(maxPaddleLengthField);
				
				minPaddleLengthLabel = new JLabel("Min paddle length:");
				add(minPaddleLengthLabel);
				
				minPaddleLengthField = new JTextField();
				add(minPaddleLengthField);
				
				JLabel gg = new JLabel("    ");
				add(gg,"gridwidth=4");
				
				JLabel hh = new JLabel("    ");
				add(hh,"gridwidth=4");
				
				defineSettingsButton = new JButton("Define Settings");
				add(defineSettingsButton, "gridwidth=3");
				
				
				JSeparator horizontalLineTop = new JSeparator();
				add(horizontalLineTop, "gridwidth=14");
				
				
				//---------------------------------------------
				
				//listOfAvailableGames
				
				JLabel abc = new JLabel ("Available Games: ");
				add(abc);
				
				
				
				currentDesignableGames = new JComboBox<String>(new String[0]);
				add(currentDesignableGames, "gridwidth=2");
				
				
				
				refreshGameButton = new JButton("Refresh List");
				add(refreshGameButton);
				
				selectGameButton = new JButton("Select Game");
				add(selectGameButton);
	
				JLabel fff = new JLabel("  ");
				add(fff,"gridwidth=7");
				
				JButton publishButton = new JButton("Publish Game");
				add(publishButton,"gridwidth=2");
				
				JSeparator horizontalLineTop1 = new JSeparator();
				add(horizontalLineTop1, "gridwidth=14");
				
				
				//---------------------------------------------
				//deleteGame

				JLabel e = new JLabel("Select a game from the list of Available Games to Delete. ");
				add(e,"gridwidth=4");
					
				JLabel spaceX = new JLabel(" ");
				add(spaceX, "gridwidth=8");
				
				
				deleteGameButton = new JButton("Delete");
				add(deleteGameButton, "gridwidth=2");

				
				JSeparator horizontalLineTopC = new JSeparator();
				add(horizontalLineTopC, "gridwidth=14");
				
				
				//---------------------------------------------
				
				//updateSettings
				
				JLabel message = new JLabel ("Select a game from the list of Available Games to Update Settings.");
				add(message, "gridwidth=14");
				
				updatedGameNameLabel = new JLabel("New Game Name:");
				add(updatedGameNameLabel);
				
				updatedGameNameField  = new JTextField();
				add(updatedGameNameField, "gridwidth=3");
				
				
				updatedNrLevelsLabel = new JLabel("No. of levels:");
				add(updatedNrLevelsLabel);
				
				updatedNrLevelsField = new JTextField();
				add(updatedNrLevelsField);
				
				updatedNrBlocksperlevelLabel = new JLabel(" No. of blocks per level:");
				add(updatedNrBlocksperlevelLabel,"gridwidth=2");
				
				updatedNrBlocksperlevelField = new JTextField();
				add(updatedNrBlocksperlevelField);
				
				updatedMinBallSpeedXLabel = new JLabel("minBallSpeedX:");
				add(updatedMinBallSpeedXLabel);
				
				updatedMinBallSpeedXField = new JTextField();
				add(updatedMinBallSpeedXField);
				
				JLabel s3 = new JLabel("    ");
				add(s3,"gridwidth=4");
				
				updatedMinBallSpeedYLabel = new JLabel("minBallSpeedY:");
				add(updatedMinBallSpeedYLabel);
				
				updatedMinBallSpeedYField = new JTextField();
				add(updatedMinBallSpeedYField);
				
				updatedBallSpeedIncreaseFactorLabel = new JLabel("Speed Increase Factor: ");
				add(updatedBallSpeedIncreaseFactorLabel,"gridwidth=1");
				
				updatedBallSpeedIncreaseFactorField = new JTextField();
				add(updatedBallSpeedIncreaseFactorField);
				
				
				updatedMaxPaddleLengthLabel = new JLabel("Max paddle length:");
				add(updatedMaxPaddleLengthLabel);
				
				updatedMaxPaddleLengthField = new JTextField();
				add(updatedMaxPaddleLengthField);
				
				updatedMinPaddleLengthLabel = new JLabel(" Min paddle length:");
				add(updatedMinPaddleLengthLabel, "gridwidth=2");
				
				updatedMinPaddleLengthField = new JTextField();
				add(updatedMinPaddleLengthField);
				
				JLabel hhhh = new JLabel("    ");
				add(hhhh,"gridwidth=3");
				
				updateSettingsButton = new JButton("Update Settings");
				add(updateSettingsButton, "gridwidth=2");
				
				JSeparator horizontalLineTop2 = new JSeparator();
				add(horizontalLineTop2, "gridwidth=14");
				
				//---------------------------------------------
				//addBlock

				
				//red
				redLabel = new JLabel("Red:");
				add(redLabel);
				
				redTextField = new JTextField();
				add(redTextField, "gridwidth=2");
				
				JLabel a = new JLabel("    ");
				add(a,"gridwidth=1");
				
				//green
				greenLabel = new JLabel("Green:");
				add(greenLabel);
				
				greenTextField = new JTextField();
				add(greenTextField, "gridwidth=2");
				
				JLabel b = new JLabel("    ");
				add(b,"gridwidth=9");
				
				//blue
				blueLabel = new JLabel("Blue:");
				add(blueLabel);
				
				
				blueTextField = new JTextField();
				add(blueTextField, "gridwidth=2");
				
				JLabel aa = new JLabel("    ");
				add(aa,"gridwidth=1");
				
				//points
				pointsLabel = new JLabel("Points: ");
				add(pointsLabel);
				
				pointsTextField = new JTextField();
				add(pointsTextField, "gridwidth=2");
				
				JLabel c = new JLabel("    ");
				add(c,"gridwidth=5");
				
				addBlockButton = new JButton ("Add Block");
				add(addBlockButton, "gridwidth=2");
				
				JSeparator horizontalLineTopD = new JSeparator();
				add(horizontalLineTopD , "gridwidth=14");
				
				
				
				//---------------------------------------------
				//listOfBlocksInGame
				
				availableBlocksLabel = new JLabel("Blocks in Game: ");
				add(availableBlocksLabel);
				
				availableBlocks = new JComboBox<String>();
				add(availableBlocks, "gridwidth=3");
				
				selectedBlockLabel = new JLabel("Selected Block: ");
				add(selectedBlockLabel);
				
				selectedBlockField = new JTextField();
				selectedBlockField.setEditable(false);
				add(selectedBlockField,"gridwidth = 2");
				
				JLabel spaceCC = new JLabel (" ");
				add(spaceCC, "gridwidth=7");
				
				JSeparator horizontalLineTop3 = new JSeparator();
				add(horizontalLineTop3, "gridwidth=14");
				
				//---------------------------------------------
				//deleteBlock
				
				blocksInGameLabel = new JLabel("Select a game from list of 'Available Games' and then a block from the list of 'Blocks in Game' to Delete Block.");
				add(blocksInGameLabel, "gridwidth=12");
				
				deleteBlockButton = new JButton("Delete Block");
				add(deleteBlockButton, "gridwidth=2");
				
				JSeparator horizontalLineTop4 = new JSeparator();
				add(horizontalLineTop4, "gridwidth=14");
				
				//---------------------------------------------
				//updateBlock
				 
				//red
			   redLabel1 = new JLabel("Red:");
				add(redLabel1);
						
				redTextField1 = new JTextField();
				add(redTextField1, "gridwidth=2");
						
				JLabel a1 = new JLabel("    ");
				add(a1,"gridwidth=1");
						
				//green
				greenLabel1 = new JLabel("Green:");
				add(greenLabel1);
						
				greenTextField1 = new JTextField();
				add(greenTextField1, "gridwidth=2");
						
				JLabel b1 = new JLabel("    ");
				add(b1,"gridwidth=7");
						
				//blue
				blueLabel1 = new JLabel("Blue:");
				add(blueLabel1);
						
				blueTextField1 = new JTextField();
				add(blueTextField1, "gridwidth=2");

				JLabel aa1 = new JLabel("    ");
				add(aa1,"gridwidth=1");
						
				//points
				pointsLabel1 = new JLabel("Points: ");
				add(pointsLabel1);
						
				pointsTextField1 = new JTextField();
				add(pointsTextField1, "gridwidth=2");
							
				JLabel c1 = new JLabel("    ");
				add(c1,"gridwidth=5");
						
				updateBlockButton = new JButton("Update Block");
				add(updateBlockButton, "gridwidth=2");
				//---------------------------------------------
				//positionBlock
				
				JSeparator horizontalLineTopX = new JSeparator();
				add(horizontalLineTopX, "gridwidth=14");
						 
				levelLabel = new JLabel ("Level: ");
				add(levelLabel);
						
				levelTextField = new JTextField();
				add(levelTextField);
						

						
						
				gridHorizontalPosition= new JLabel ("Horizontal Position:");
				add(gridHorizontalPosition);
						
				gridHorizontalField1 = new JTextField();
				add(gridHorizontalField1);
						
				gridVerticalPosition= new JLabel ("Vertical Position:");
				add(gridVerticalPosition);
						
				gridVerticalField1 = new JTextField();
				add(gridVerticalField1);
						
				JLabel spacee = new JLabel (" ");
				add(spacee, "gridwidth=6");
						
				JButton positionBlock = new JButton("Position Block");
				add(positionBlock,"gridwidth=2");
						
				JSeparator horizontalLineTopXSS = new JSeparator();
				add(horizontalLineTopXSS, "gridwidth=14");
				

							
				//---------------------------------------------
				//removeBlock
				
				Level = new JLabel("Level:");
				add(Level);
						
				Level1 = new JTextField();
				add(Level1);
				
				selectLevelButton = new JButton ("Select Level");
				add(selectLevelButton);
				
				JLabel blocksInLevelLabel = new JLabel ("Positioned Blocks: ");
				add(blocksInLevelLabel);
				
				blocksInLevelList = new JComboBox ();
				add(blocksInLevelList);
				
				
				gridHorizontal2 = new JLabel("Horizontal Position:");
				add(gridHorizontal2);
						
				gridHorizontal3 = new JTextField();
				add(gridHorizontal3);
						
						
						
				gridVertical2 = new JLabel("Vertical Position:");
				add(gridVertical2);
						
				gridVertical3 = new JTextField();
				add(gridVertical3);
					
				
		
				JLabel spac1= new JLabel (" ");
				add(spac1, "gridwidth=3");
						
				deleteBlockLevel = new JButton("Remove block");
				add(deleteBlockLevel, "gridwidth=2");
						
			//	JSeparator horizontalLineTop6 = new JSeparator();
			//	add(horizontalLineTop6, "gridwidth=14");
				
				
				
				//---------------------------------------------
				//moveBlock
				
				initVertPosLabel = new JLabel ("Initial Vertical Position: ");
				add(initVertPosLabel, "gridwidth = 2");
				
				initVertPosField = new JTextField ();
				add(initVertPosField);
				
				initHorPosLabel = new JLabel ("Initial Horizontal Position: ");
				add(initHorPosLabel, "gridwidth = 2");
				
				initHorPosField = new JTextField ();
				add(initHorPosField);
				
				finalVertPosLabel = new JLabel ("Final Vertical Position: ");
				add(finalVertPosLabel, "gridwidth = 2");
				
				finalVertPosField = new JTextField ();
				add(finalVertPosField);
				
				finalHorPosLabel = new JLabel ("Final Horizontal Position: ");
				add(finalHorPosLabel, "gridwidth = 2");
				
				finalHorPosField = new JTextField ();
				add(finalHorPosField);
				
				moveBlockButton = new JButton ("Move Block");
				add(moveBlockButton, "gridwidth = 2");
				
				
				
				
				JSeparator horizontalLineTopXSSS = new JSeparator();
				add(horizontalLineTopXSSS, "gridwidth=14");
				
				
				//---------------------------------------------------
				//saveGame
			
				JLabel saveGameLabel = new JLabel ("Select a game from the list of 'Available Games' to Save Game");
				add(saveGameLabel, "gridwidth=12");
				
				saveGameButton = new JButton ("Save Game");
				add(saveGameButton, "gridwidth = 2");
				
				
				JSeparator horizontalLineTop8 = new JSeparator();
				add(horizontalLineTop8, "gridwidth=14");
				//---------------------------------------------------
				//Messages
							
				JLabel errorLabel = new JLabel ("MESSAGES:");
				add(errorLabel);
							
				errorField = new JTextField();
				errorField.setEditable(false);
				add(errorField, "gridwidth=13");
							
				JSeparator horizontalLineTop7 = new JSeparator();
				add(horizontalLineTop7, "gridwidth=14");
						
						
						
					
						String [] Buttons = {"  ", "  B   " ,  "  L  " ,  " O  " , "  C "  ,  " K " , " " , " 2 " , " 2 " , " 3 " , "  " , "  " , "  " , "   " };
					for (int counter1=0; counter1<Buttons.length; counter1++) { 
							if(Buttons[counter1].matches("C")) {
								add(new JButton(Buttons[counter1]), "gridwidth=2");
							}
							else {
								add(new JButton(Buttons[counter1]));
							}
						}
				
				
					
				
	
	
		/*setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Bus Transportation Management System");*/
		registerButton.addActionListener(new java.awt.event.ActionListener(){
						public void actionPerformed(java.awt.event.ActionEvent evt) {
							registerButtonActionPerformed(evt);}
						});
		loginButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginButtonActionPerformed(evt);}
			});
		createGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createGameButtonActionPerformed(evt);}
			});
		defineSettingsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				defineSettingsButtonActionPerformed(evt);
				}
		});
		selectGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				selectGameButtonActionPerformed(evt);
				}
		});
		
		refreshGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				refreshGameButtonActionPerformed(evt);
				}
		});
		
		deleteGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteGameButtonActionPerformed(evt);
			}
		});
		updateSettingsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateSettingsButtonActionPerformed(evt);
			}
		});
		addBlockButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBlockButtonActionPerformed(evt);
			}
		});
		deleteBlockButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteBlockButtonActionPerformed(evt);
			}
		});
		updateBlockButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateBlockButtonActionPerformed(evt);
			}
		});
		
		positionBlock.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				positionBlockActionPerformed(evt);
			}
		});
		
		moveBlockButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveBlockButtonActionPerformed(evt);
			}
		});
		
		saveGameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveGameButtonActionPerformed(evt);
			}
		});
		deleteBlockLevel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteBlockLevelActionPerformed(evt);
			}
		});
		logoutButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutButtonlActionPerformed(evt);
			}
		});
		
		selectLevelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				selectLevelButtonActionPerformed(evt);
			}
		});
		
		playGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playGameActionPerformed(evt);
			}
		});
		publishButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				publishButtonActionPerformed(evt);
			}
		});
		testGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				testGameActionPerformed(evt);
			}
		});

		
	}
	private void refreshData() {
		errorField.setText("No Error");
		errorField.setText(error);
	}
	private void refreshGames() throws InvalidInputException{
		games = new HashMap<Integer, String>();
		currentDesignableGames.removeAllItems();
		Integer index = 0;
		for (TOGame game : Block223Controller.getDesignableGames()) {
			games.put(index, game.getName());
			currentDesignableGames.addItem(game.getName());
			index++;
			}
		currentDesignableGames.setSelectedIndex(-1);
	}
	private void refreshBlocks() throws InvalidInputException{
		blocks = new HashMap<Integer,String>();
		availableBlocks.removeAllItems();
		Integer index = 0;
		for(TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
			String blockData = block.getId() + ": ID ; red: " + block.getRed() + "; green: " + block.getGreen() + "; Blue: " + block.getBlue(); 
			blocks.put(index, blockData);
			availableBlocks.addItem((blockData));
			index++;
		}
		availableBlocks.setSelectedIndex(-1);
	}
	private void refreshSelectedGame() {
		try {
			if(Block223Controller.getCurrentDesignableGame()==null) {
				error= "Bad";
				refreshData();
			}
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			selectionDisplayField.setText(Block223Controller.getCurrentDesignableGame().getName());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
			refreshData();
		}
	}
	private void refreshBlocksInLevel(int level) throws InvalidInputException {
		
		blocksInLevel = new HashMap<Integer,String>();
		blocksInLevelList.removeAllItems();
		List<TOGridCell> blocksLevel = null;
		Integer index = 0;
		try {
		blocksLevel = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level);
		}
		catch (InvalidInputException e){
			error = e.getMessage();
			refreshData();
		}
		
		if(blocksLevel!=null) {
		for(TOGridCell block : blocksLevel) {
			String blockData = block.getId() + ": ID ; red: " + block.getRed() + "; green:  " + block.getGreen() + "; Blue: " + block.getBlue() + "   " + block.getGridHorizontalPosition() + "/" + block.getGridVerticalPosition() ; 
			blocksInLevel.put(index, blockData);
			blocksInLevelList.addItem((blockData));
			index++;
		}
		
		blocksInLevelList.setSelectedIndex(-1);
		}
	}
	
	
	
	
	private void createGameButtonActionPerformed(ActionEvent evt) {
		error = "";
		if(field1.getText().equals("")) {
			error= error + "Game name cannot be empty";
			refreshData();
			return;
		}
		try {
			Block223Controller.createGame(field1.getText());
			error = "game created!";
		}catch(InvalidInputException e) {
			error = e.getMessage();
		}
		refreshData();
		try {
			refreshGames();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error=error+e.getMessage();
		}
		refreshData();
	}
	private void selectGameButtonActionPerformed(ActionEvent evt) {
		error="";
		int index = currentDesignableGames.getSelectedIndex();
		try {
			Block223Controller.selectGame(currentDesignableGames.getItemAt(index));
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			refreshBlocks();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void deleteGameButtonActionPerformed(ActionEvent evt) {
		error= "Game deleted";
		try {
			int index = currentDesignableGames.getSelectedIndex();
			Block223Controller.deleteGame(currentDesignableGames.getItemAt(index));
		} catch (InvalidInputException e) {
			error=e.getMessage();
		}
		refreshData();
		try {
			refreshGames();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error=e.getMessage();
			}
		
	}
	private void defineSettingsButtonActionPerformed(ActionEvent evt) {
		error = "";
		String nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength,minPaddleLength;
		nrLevels = nrLevelsField.getText();
		nrBlocksPerLevel = nrBlocksperlevelField.getText();
		minBallSpeedX = minBallSpeedXField.getText();
		minBallSpeedY = minBallSpeedYField.getText();
		ballSpeedIncreaseFactor = ballSpeedIncreaseFactorField.getText();
		maxPaddleLength = maxPaddleLengthField.getText();
		minPaddleLength = minPaddleLengthField.getText();
		
		//System.out.println(nrLevels);
		if((nrLevels.equals("") || nrBlocksPerLevel.equals("") || minBallSpeedX.equals("") || minBallSpeedY.equals("") || ballSpeedIncreaseFactor.equals("")|| maxPaddleLength.equals("")|| minPaddleLength.equals(""))) {
			error = "Input Fields cannot be Empty";
			refreshData();
		}
		else {
		try {
			Block223Controller.setGameDetails(Integer.parseInt(nrLevelsField.getText()), Integer.parseInt(nrBlocksperlevelField.getText()),Integer.parseInt(minBallSpeedXField.getText()), Integer.parseInt(minBallSpeedYField.getText()), Double.parseDouble(ballSpeedIncreaseFactorField.getText()), Integer.parseInt(maxPaddleLengthField.getText()), Integer.parseInt(minPaddleLengthField.getText()));
			error="game settings defined";
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
		refreshData();
		}
	}
	private void registerButtonActionPerformed(ActionEvent evt) {
		error = "";
		try {
			Block223Controller.register(usernameField.getText(), playerPasswordField.getText(), adminPassword.getText());
			error = "user registered!";
		} catch (InvalidInputException e) {
		   error = e.getMessage();
		}
		
		refreshData();
	}
	private void loginButtonActionPerformed(ActionEvent evt) {
		error="";
		try {
			Block223Controller.login(usernameField1.getText(), password.getText());
			error = "logged in!";
		} catch (InvalidInputException e) {
			error= error + e.getMessage();
		}
		refreshData();
/*		try {
			refreshGames();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = error + e.getMessage();
			refreshData();
		} */
		
	}

	private void updateSettingsButtonActionPerformed(ActionEvent evt) {
		error= "";	
		String nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength,minPaddleLength;
		nrLevels = updatedNrLevelsField.getText();
		nrBlocksPerLevel = updatedNrBlocksperlevelField.getText();
		minBallSpeedX = updatedMinBallSpeedXField.getText();
		minBallSpeedY = updatedMinBallSpeedYField.getText();
		ballSpeedIncreaseFactor = updatedBallSpeedIncreaseFactorField.getText();
		maxPaddleLength = updatedMaxPaddleLengthField.getText();
		minPaddleLength = updatedMinPaddleLengthField.getText();
		String name=updatedGameNameField.getText();
		
		if((name.equals("") || nrLevels.equals("") || nrBlocksPerLevel.equals("") || minBallSpeedX.equals("") || minBallSpeedY.equals("") || ballSpeedIncreaseFactor.equals("")|| maxPaddleLength.equals("")|| minPaddleLength.equals(""))) {
			error = "Input Fields cannot be Empty";
			refreshData();
		}
		
	else {
		try {
			int index = currentDesignableGames.getSelectedIndex();
			Block223Controller.selectGame(currentDesignableGames.getItemAt(index));
		} catch(InvalidInputException e) {
			
			error=error+e.getMessage();
		}
		try {
			Block223Controller.updateGame(name, Integer.parseInt(nrLevels), Integer.parseInt(nrBlocksPerLevel),Integer.parseInt(minBallSpeedX), Integer.parseInt(minBallSpeedY), Double.parseDouble(ballSpeedIncreaseFactor), Integer.parseInt(maxPaddleLength), Integer.parseInt(minPaddleLength));
			error = "game updated";
		} catch (NumberFormatException e1) {
			//System.out.println("THE ERROR WAS HERE: " + e1.getMessage());
			error=error+e1.getMessage();
		} catch (InvalidInputException e1) {
			error=error+e1.getMessage();
		}
		refreshData();
		try {
			refreshGames();
		} 
		
		catch (InvalidInputException e) {
			error = error + e.getMessage();
		
		}
	}
}

	private void addBlockButtonActionPerformed(ActionEvent evt) {
		error="";
		String red = redTextField.getText();
		String green = greenTextField.getText();
		String blue = blueTextField.getText();
		String points = pointsTextField.getText();
		if(red.equals("") || blue.equals("") || green.equals("") || points.equals("")) {
			error = "Input Fields cannot be Empty";
			refreshData();
		}
		else {	
			try {	
				int index = currentDesignableGames.getSelectedIndex();
				Block223Controller.selectGame(currentDesignableGames.getItemAt(index));
				Block223Controller.addBlock(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue), Integer.parseInt(points));
				error="block added!";
				refreshData();
			} catch (InvalidInputException e) {
				error=e.getMessage();
			}
		     refreshData();
		     try {
				refreshBlocks();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				error=e.getMessage();
			}
	      }
		}
	
	private void deleteBlockButtonActionPerformed(ActionEvent evt) {
		error = "";
		int index = currentDesignableGames.getSelectedIndex();
		int index1 = availableBlocks.getSelectedIndex();
		
		if(index<0 || index1<0) {
			error = "please select both Game and Block";
			refreshData();
		}
		try {
			Block223Controller.selectGame(currentDesignableGames.getItemAt(index));
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String fst = ""+ availableBlocks.getItemAt(index1).charAt(0);
			int id = Integer.parseInt(fst);
			Block223Controller.deleteBlock(id);
			error = "block deleted!";
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			error=error+e1.getMessage();
		}
		try {
			refreshBlocks();
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			error=error+e1.getMessage();
		}
		refreshData();
		}
	
	
	
	private void updateBlockButtonActionPerformed(ActionEvent evt) {
			error = "";
		
			int index = currentDesignableGames.getSelectedIndex();		
			int index1 = availableBlocks.getSelectedIndex();		
			//int id = (availableBlocks.getItemAt(index1).charAt(0));	
			String fst = ""+ availableBlocks.getItemAt(index1).charAt(0);
			int id = Integer.parseInt(fst);
			int red = Integer.parseInt(redTextField1.getText());		
			int green = Integer.parseInt(greenTextField1.getText());		
			int blue = Integer.parseInt(blueTextField1.getText());	
			int points = Integer.parseInt(pointsTextField1.getText());
		
			
			try {
				Block223Controller.selectGame(currentDesignableGames.getItemAt(index));
				
	            Block223Controller.getBlockOfCurrentDesignableGame(id);//maybe change id to index1
	            
				Block223Controller.updateBlock(id, red, green, blue, points);
				error = "block updated!";
				refreshData();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				error = e.getMessage();
				refreshData();
			}
	}
	
	private void positionBlockActionPerformed(ActionEvent evt) {
		error = "";
		
		
		int index = currentDesignableGames.getSelectedIndex();		
		int index1 = availableBlocks.getSelectedIndex();		
		

		String selectedLevel, horPos, verPos;
		selectedLevel = levelTextField.getText();
		horPos = gridHorizontalField1.getText();
		verPos = gridVerticalField1.getText();
		
		if(index<0 || index1<0 || selectedLevel.equals("") || horPos.equals("") || verPos.equals("")) {
			error = "Please enter all inputs and select both the game and blocks";
			refreshData();
			}
		
		
	    
	    else {
	    		String fst = ""+ availableBlocks.getItemAt(index1).charAt(0);
			int id = Integer.parseInt(fst);
			try {
				Block223Controller.selectGame(currentDesignableGames.getItemAt(index));
				Block223Controller.getBlockOfCurrentDesignableGame(id);
				Block223Controller.positionBlock(id,Integer.parseInt(selectedLevel), Integer.parseInt(horPos), Integer.parseInt(verPos));
				error = "block positioned!";
			} 
			catch (InvalidInputException e2) {
				// TODO Auto-generated catch block
				error = e2.getMessage();
				
			}
			
			refreshData();
			
	}
}
	
	
	private void deleteBlockLevelActionPerformed(ActionEvent evt) {
		
		error = "";
		
		String selectedLevel, horPos, verPos;
		
		int index = currentDesignableGames.getSelectedIndex();		
		int index1 = availableBlocks.getSelectedIndex();		
			
	
		selectedLevel = Level1.getText();
		horPos = gridHorizontal3.getText();
		verPos = gridVertical3.getText();
		
		
		if(index<0 || index1<0 || selectedLevel.equals("") || horPos.equals("") || verPos.equals("")) {
			error = "Please enter all inputs and select both the game and blocks";
			refreshData();
			}
		
		
		else {	
			String fst = ""+ availableBlocks.getItemAt(index1).charAt(0);
			int id = Integer.parseInt(fst);
			
			try {
		    Block223Controller.selectGame(currentDesignableGames.getItemAt(index));
		    Block223Controller.getBlockOfCurrentDesignableGame(id);
			Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(Integer.parseInt(selectedLevel));
		} catch (InvalidInputException e) {
		error = e.getMessage();	
		refreshData();
	}
			
			try {
		Block223Controller.removeBlock (Integer.parseInt(selectedLevel), Integer.parseInt(horPos),Integer.parseInt(verPos));
		error = "block deleted from level " + selectedLevel;
			}
			catch (InvalidInputException e1) {
				error = e1.getMessage();
				refreshData();
				
			}
		
		try {	
		refreshBlocksInLevel(Integer.parseInt(selectedLevel));
		}
		catch (InvalidInputException e2) {
			error = e2.getMessage();
			refreshData();
			
		}
	refreshData();
			}
	}


	private void saveGameButtonActionPerformed(ActionEvent evt) {
		
		error = "";
		try {
			int index = currentDesignableGames.getSelectedIndex();
			Block223Controller.selectGame(currentDesignableGames.getItemAt(index));
			//refreshSelectedGame();
			Block223Controller.saveGame();
			error = "game saved!";
		} catch (InvalidInputException e) {
			error=e.getMessage();
		}
		refreshData();
	}

	private void logoutButtonlActionPerformed(ActionEvent evt) {
		error="";
		Block223Controller.logout();
		error = "logged out!" ;
		refreshData();
	}
	
	
	private void selectLevelButtonActionPerformed(ActionEvent evt) {
		if(Level1.getText()==null || Level1.getText().equals("")) {
			error = "Enter a level number in the level field.";
			refreshData();
		}
		
		else {
			
		levelSelected = Integer.parseInt(Level1.getText());
		
		try {
			refreshBlocksInLevel(levelSelected);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
			}
		}
	}

	private void playGameActionPerformed(ActionEvent evt) {
		
		if(Block223Controller.isPlayer()) {
		Block223PlayGameUI blocknew = new Block223PlayGameUI();
		blocknew.setVisible(true);
		}
		else {
			error="Player privileges are required to play a game.";
		}
		refreshData();
	}
	
	private void moveBlockButtonActionPerformed(java.awt.event.ActionEvent event) {
		
		int level, xi, yi, xf, yf;
		try {
			level = levelSelected; //The level number entered in the text field is greater than the level index by 1 //***CHANGED*** level is now the same number as inputted in the text field. The moveBlock(...) method takes the level number, not the level index, as a parameter. So, the variable "level" should not be the level index.
			xi = Integer.parseInt(initHorPosField.getText());
			xf = Integer.parseInt(finalHorPosField.getText());
			yi = Integer.parseInt(initVertPosField.getText());
			yf = Integer.parseInt(finalVertPosField.getText());
		} catch (NumberFormatException e) {
			error = "Only integers can be entered in the text fields: Level, Initial Horizontal Position, Initial Vertical Position, Final Horizontal Position, and Final Vertical Position";
			refreshData();
			return;
		}
		
		try {
			Block223Controller.moveBlock(level, xi, yi, xf, yf); 
			error = "The block at position " + initHorPosField.getText() + "/" + initVertPosField.getText() + " has been moved to position " + finalHorPosField.getText() + "/" + finalVertPosField.getText(); //***CHANGED***: Now displays position as horizontal/vertical
			refreshData();
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
	
	private void publishButtonActionPerformed(ActionEvent evt) {
		error="";	
		try {
			Block223Controller.publishGame();
			error = "game published!";
		} catch (InvalidInputException e) {
			error=e.getMessage();
		}
		try {
			refreshGames();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error=error+e.getMessage();
		}
		refreshData();
		}
	
	private void testGameActionPerformed(ActionEvent evt) {
		
		if(!Block223Controller.isAdmin()) {
			error = "Admin privilleges are required to test a game.";
			refreshData();
		}
		
		else if (Block223Controller.isAdmin()) {
			Block223TestGameUI testScreen = new Block223TestGameUI();
			testScreen.setVisible(true);
		}	
	}
	
	private void refreshGameButtonActionPerformed(ActionEvent evt) {
		try {
			refreshGames();
			error = "game refreshed!";
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshData();
	}
	
	
}