import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class RedBall extends JComponent implements ActionListener, MouseMotionListener{

    private int x = 5;
    private int y = 7;
    private int ballx = 150;
    private int bally = 30;
    //private int ballx1 = 100;
    //private int bally1 = 10;
    private int paddlex = 0;
    private int ballySpeed = y;
    private int ballxSpeed = x;
    //private int bally1Speed = 14;
    //private int ballx1Speed = 10;
    public int score = 0;
    public int finalscore;
    //public int score1 = 0;
    //private int scorefinal;
    public int bestscore;
    public static String player=null;
    //public int bestscore1;
    public boolean gameOver, started;
    static JFrame wind;
    public static void showWind(String player2) {
    	
        player=player2;
        wind = new JFrame("RedBall/GamePinfo");
        RedBall g = new RedBall();
        wind.add(g);
        wind.pack();
        wind.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wind.setLocationRelativeTo(null);
        wind.setVisible(true);
        wind.addMouseMotionListener(g);

        Timer tt = new Timer(17, g);
        tt.start();
        Thread go = g.new GameOver();
        go.start();
    }

    public void newball(int ballx, int bally, int ballxspeed, int ballyspeed) {

        ballx = 150;
        bally = 30;
        ballxspeed = x;
        ballyspeed = y;
        JOptionPane.showMessageDialog(null, "new ball !");

        return;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {

        //draw the sky
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.GREEN);
        g.fillRect(0, 550, 800, 100);

        //draw the paddel
        g.setColor(Color.black);
        g.fillRect(paddlex, 500, 100, 20);

        //draw the ball
        g.setColor(Color.RED);
        g.fillOval(ballx, bally, 30, 30);

        /*//draw the ball_1
        if (score >= 5) {
            g.setColor(Color.BLACK);
            g.fillOval(ballx1, bally1, 30, 30);

        }*/
        //score 
        if (score >= 5) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", 8, 50));
            g.drawString(String.valueOf(score), 30 / 1 - 15, 80);
        } else {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 8, 50));
            g.drawString(String.valueOf(score), 30 / 1 - 15, 80);
        }
        // start && gameOver
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 8, 50));

        /*if (gameOver) {
            g.drawString(String.valueOf(" Best Score :" + bestscore), 50 / 1 - 15, 200);
            //wind.dispose();
            Main_menu mm=new Main_menu();
            
            mm.setVisible();
            try {
				setScore(player);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }*/
    }
    class GameOver extends Thread {
    	public void run() {
    		while(!gameOver) {
    			if (bally >= 700 ) {
    	            score = 0;
    	            bally = 30;
    	            gameOver = true;
    	        }
    			if (gameOver) {
//    	            g.drawString(String.valueOf(" Best Score :" + bestscore), 50 / 1 - 15, 200);
    	            //wind.dispose();
    	            Main_menu mm=new Main_menu();
    	            System.out.println(score);
    	            wind.dispose();
    	            
    	            mm.setVisible();
    	            try {
    					setScore(player);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	        }
    			try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    }

    private void setScore(String player) throws SQLException 
    {
	
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","1962");  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement();
		stmt.executeUpdate("update player set score="+finalscore+" where player_name='"+player+"'");
		
	}

	@Override
    public void actionPerformed(ActionEvent e) {

        if(score>=3 && score<5){
            x=6;
            y=8;
        }
        if(score>=5 && score<10){
            x=7;
            y=9;
        }
        if(score>=10 && score<15){
            x=8;
            y=10;
        }
        if(score>=15 && score<20){
            x=10;
            y=12;
        }
        if(score>=20 && score<25){
            x=12;
            y=14;
        }

        ballx = ballx + ballxSpeed;
        bally = bally + ballySpeed;

        // Window Down 
        if (ballx >= paddlex && ballx <= paddlex + 100 && bally >= 475) {
            ballySpeed = -y;
            score++;
            java.awt.Toolkit.getDefaultToolkit().beep();
        }

        

        // Window up
        if (bally <= 0) {
            ballySpeed = y;
        }

        // Window right
        if (ballx >= 775) {
            ballxSpeed = -x;
        }

        // Window left
        if (ballx <= 0) {
            ballxSpeed = x;
        }

        /*//**********************************************************************
        ballx1 = ballx1 + ballx1Speed;
        bally1 = bally1 + bally1Speed;

        // Window down
        if (ballx1 >= paddlex && ballx1 <= paddlex + 100 && bally1 >= 475) {
            bally1Speed = -14;
            score1++;
        }

        if (bally1 >= 700) {
            score1 = 0;
            bally1 = 10;
        }

        // Window up
        if (bally1 <= 0) {
            bally1Speed = 14;
        }

        // Window right
        if (ballx1 >= 775) {
            ballx1Speed = -10;
        }

        // Window left
        if (ballx1 <= 0) {
            ballx1Speed = 10;
        }
*/
        //**********************************************************************
        //bestscore = score;
        //bestscore1 = score1;

        if (score > bestscore) {
            bestscore = score;
            finalscore=score;
        } else {
            bestscore=bestscore;
        }
        
        /*if (scorefinal > bestscore) {
            scorefinal = scorefinal;
        } else {
            scorefinal = bestscore;
            scorefinal = score + score1;
        }*/
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        paddlex = e.getX() - 50;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

}