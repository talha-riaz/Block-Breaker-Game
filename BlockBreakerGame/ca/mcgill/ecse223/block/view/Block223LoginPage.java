package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
 
public class Block223LoginPage {

	JFrame frame;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Block223LoginPage window = new Block223LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Block223LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 342, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBlock = new JLabel("BLOCK223");
		lblBlock.setFont(new Font("Arial", Font.BOLD, 40));
		lblBlock.setBounds(65, 6, 236, 97);
		frame.getContentPane().add(lblBlock);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblWelcome.setBounds(126, 103, 86, 16);
		frame.getContentPane().add(lblWelcome);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(89, 147, 169, 26);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(89, 178, 169, 26);
		frame.getContentPane().add(passwordField);
		
		JButton loginButton = new JButton("Log In");
		loginButton.setBounds(113, 216, 117, 29);
		frame.getContentPane().add(loginButton);
		
		JLabel lblNewUser = new JLabel("New User?");
		lblNewUser.setBounds(141, 286, 71, 16);
		frame.getContentPane().add(lblNewUser);
		
		JButton signupButton = new JButton("Sign Up");
		signupButton.setBounds(113, 311, 117, 29);
		frame.getContentPane().add(signupButton);
		
		loginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginButtonActionPerformed(evt);
			}
		});
		
		loginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginButtonActionPerformed(evt);
			}
		});
		
		signupButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				signupButtonActionPerformed(event);
			}
		});
		
	}
	
	
	
	
	
	private void loginButtonActionPerformed(ActionEvent evt) {
	
		String username = txtUsername.getText();
		String password = passwordField.getText().toString();
		
		try {
			Block223Controller.login(username, password);
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		
		if (Block223Controller.isPlayer()) {		
				Block223PlayGameUI playScreen = new Block223PlayGameUI();
				playScreen.setVisible(true);
				frame.dispose();
				//JOptionPane.showMessageDialog(frame, "Welcome " + username + ". \nYou logged in as player.");
			}
			
			else if (Block223Controller.isAdmin()) {		
				Block223Page settingsScreen = new Block223Page();
				settingsScreen.setVisible(true);
				frame.dispose();		
				//JOptionPane.showMessageDialog(frame, "Welcome " + username + ". \nYou logged in as admin.");
			}
		
	}
	
	
	private void signupButtonActionPerformed(ActionEvent event) {	
		//Block223SignUpPage signUpScreen = new Block223SignUpPage();
		//signUpScreen.setVisible(true);
			//Block223SignUpPage.main();
			//System.out.println("THIS RAN01");
	}
	
	
	
	
	
	
}
