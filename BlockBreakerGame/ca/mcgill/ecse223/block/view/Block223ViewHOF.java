package ca.mcgill.ecse223.block.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;

import javax.swing.JButton;

public class Block223ViewHOF extends JFrame{

	private JFrame frame;
	private JTable table;

	private JScrollPane scrollPane;
	
	private JButton btnPrevious;
	private JButton btnNext;
	private DefaultTableModel hallOfFame;
	private String columnNames[] = {"Position", "Name", "Score"};
	private static final int HEIGHT_OVERVIEW_TABLE = 200;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Block223ViewHOF window = new Block223ViewHOF("");
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
	public Block223ViewHOF(String gameName) {
		initialize();
		refreshHallOfFame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scrollPane = new JScrollPane();
		
		JLabel lblHallOfFame = new JLabel("Hall of Fame");
		
		btnPrevious = new JButton("Previous");
		
		btnNext = new JButton("Next");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(186)
							.addComponent(lblHallOfFame))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(86)
							.addComponent(btnPrevious)
							.addGap(45)
							.addComponent(btnNext)))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblHallOfFame)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrevious)
						.addComponent(btnNext)))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
		
		btnPrevious.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPreviousActionPerformed(evt);
			}
		});
		
		btnNext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNextActionPerformed(evt);
			}
		});
		
	}
	private void btnPreviousActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	private void btnNextActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	private void refreshHallOfFame() {
		hallOfFame = new DefaultTableModel(0, 0);
		hallOfFame.setColumnIdentifiers(columnNames);
		table.setModel(hallOfFame);
		try {
			for(TOHallOfFameEntry to : Block223Controller.getHallOfFame(0, 10).getEntries()) {
				Object[] obj = {to.getPosition(), to.getPlayername(),to.getScore()};
				hallOfFame.addRow(obj);
			}
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//Object[] obj = {"1", "Mustafain", "1000"};
		//hallOfFame.addRow(obj);
		Dimension d = table.getPreferredSize();
		scrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
	}
}