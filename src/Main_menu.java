import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

public class Main_menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_menu window = new Main_menu();
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
	public Main_menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnPlay = new JButton("Play Game");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String player_name=JOptionPane.showInputDialog("Enter your name");
				try {
					insertIntoDb(player_name);
					RedBall rb=new RedBall();
					rb.showWind(player_name);
			
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			private void insertIntoDb(String player_name) throws SQLException    //adds player name into player table
			{

				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","1962");  
				//here sonoo is database name, root is username and password  
				Statement stmt=con.createStatement(); 
				stmt.executeUpdate( "insert into player(player_name) values('"+player_name+"')");
		
			}
		});
		btnPlay.setBounds(161, 83, 97, 21);
		frame.getContentPane().add(btnPlay);
		
		JButton btnScore = new JButton("Scores");
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Scores sc;
				try {
					sc = new Scores();
					sc.setVisible();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			
		});
		btnScore.setBounds(161, 126, 97, 21);
		frame.getContentPane().add(btnScore);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		btnExit.setBounds(161, 184, 97, 21);
		frame.getContentPane().add(btnExit);
		
		JLabel lblTitle = new JLabel("Red Ball");
		lblTitle.setForeground(Color.RED);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTitle.setBounds(168, 10, 138, 31);
		frame.getContentPane().add(lblTitle);
	}

	public void setVisible() {
		// TODO Auto-generated method stub
		frame.setVisible(true);
		
	}
}
