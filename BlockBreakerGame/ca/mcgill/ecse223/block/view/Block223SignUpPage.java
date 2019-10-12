package ca.mcgill.ecse223.block.view;
import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.view.Block223SignUpPage;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Block223SignUpPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3399168974283960634L;
	private JFrame frame;
	private JTextField textField;
	private JPasswordField playerpwField;
	private JPasswordField adminpwField;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Block223SignUpPage window = new Block223SignUpPage();
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
	public Block223SignUpPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 331, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("BLOCK223");
		label.setFont(new Font("Arial", Font.BOLD, 40));
		label.setBounds(57, 6, 236, 97);
		frame.getContentPane().add(label);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSignUp.setBounds(130, 115, 102, 25);
		frame.getContentPane().add(lblSignUp);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(134, 165, 92, 16);
		frame.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(84, 183, 169, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPlayerPassword = new JLabel("Player Password");
		lblPlayerPassword.setBounds(118, 221, 107, 16);
		frame.getContentPane().add(lblPlayerPassword);
		
		playerpwField = new JPasswordField();
		playerpwField.setBounds(84, 239, 169, 26);
		frame.getContentPane().add(playerpwField);
		
		JLabel lblAdminPassword = new JLabel("Admin Password");
		lblAdminPassword.setBounds(118, 282, 108, 16);
		frame.getContentPane().add(lblAdminPassword);
		
		adminpwField = new JPasswordField();
		adminpwField.setBounds(84, 300, 169, 26);
		frame.getContentPane().add(adminpwField);
		
		JButton signupButton = new JButton("Sign Up");
		signupButton.setBounds(109, 344, 117, 29);
		frame.getContentPane().add(signupButton);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(109, 395, 117, 29);
		frame.getContentPane().add(btnClose);
		
		signupButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signupButtonActionPerformed(evt);
			}
		});	
		
		btnClose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCloseActionPerformed(evt);
			}
		});	
	}
	
	private void signupButtonActionPerformed(ActionEvent evt) {
		
		String username = textField.getText();
		String playerPw = playerpwField.getText().toString();
		String adminPw = adminpwField.getText().toString();
		
		try {
			Block223Controller.register(username, playerPw, adminPw);
			frame.dispose();
			JOptionPane.showMessageDialog(frame, "Signed Up!");
		} catch (InvalidInputException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage());
			
			
		}
		
		
	}
	
	private void btnCloseActionPerformed(ActionEvent evt){
		frame.dispose();
	}
	
	
	
}
