import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Scores {

	private static JFrame frame;
	void setVisible()
	{
		frame.setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scores window = new Scores();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Scores() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 34, 358, 202);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList(getModel());
		scrollPane.setViewportView(list);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		btnBack.setBounds(10, 0, 85, 21);
		frame.getContentPane().add(btnBack);
		
	}

	private ListModel getModel() throws SQLException 
	{
		DefaultListModel dml=new DefaultListModel();

		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","1962");  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement(); 
		ResultSet rs=stmt.executeQuery("select * from player");
		while(rs.next())
		{
			String score="Name: "+rs.getString(1)+" Score: "+rs.getInt(2);
			dml.addElement(score);
		}
		
		
		
		return dml;
	}

}
