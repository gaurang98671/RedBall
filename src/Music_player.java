import java.io.File; 
import java.io.IOException; 
import java.util.Scanner; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane; 
public class Music_player 
{
	Long currentFrame; 
    Clip clip; 
    String current;
    
    AudioInputStream audioInputStream; 
    static String filePath; 

    public Music_player(String filepath)  throws UnsupportedAudioFileException, 
    IOException, LineUnavailableException  
    {
    	 
    	Music_player.filePath=filepath;
    	audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
         clip = AudioSystem.getClip(); 
           
         clip.open(audioInputStream); 
           
         clip.loop(Clip.LOOP_CONTINUOUSLY); 
    	
    }
    
    public void play()  
    { 
        //start the clip 
        clip.start(); 
          
        current = "play"; 
    } 
    
    public void pause()  
    { 
        if (current.equals("paused"))  
        { 
            JOptionPane.showMessageDialog(null, "Song is alredy paused"); 
        } 
        this.currentFrame =  
        this.clip.getMicrosecondPosition(); 
        clip.stop(); 
        current = "paused"; 
    } 
    
    public void resumeAudio() throws UnsupportedAudioFileException, 
    IOException, LineUnavailableException  
    { 
    	if (current.equals("play"))  
    	{ 
    		JOptionPane.showMessageDialog(null, "already playing");
    	} 
    	clip.close(); 
    	reset(); 
    	clip.setMicrosecondPosition(currentFrame); 
    	this.play(); 
    } 
    
    public void restart() throws IOException, LineUnavailableException, 
    UnsupportedAudioFileException  
    { 
    	clip.stop(); 
    	clip.close(); 
		reset(); 
		currentFrame = 0L; 
		clip.setMicrosecondPosition(0); 
		this.play(); 
	} 

    public void reset() throws UnsupportedAudioFileException, IOException, 
    LineUnavailableException  
    { 
		audioInputStream = AudioSystem.getAudioInputStream( 
		new File(filePath).getAbsoluteFile()); 
		clip.open(audioInputStream); 
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
    }     
}
