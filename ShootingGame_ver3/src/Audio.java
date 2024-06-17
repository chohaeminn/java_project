import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
	private Clip clip;
	private File audio;
	private AudioInputStream audioInputStream;
	private boolean isLoop;
	
	public Audio(String path, boolean isLoop) {
		try {
			clip = AudioSystem.getClip();
			audio = new File(path);
			audioInputStream = AudioSystem.getAudioInputStream(audio);
			clip.open(audioInputStream);
			
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
				}
		}
	
	 public void start() {
		 clip.setFramePosition(0);
		 clip.start();
		 if (isLoop) clip.loop(Clip.LOOP_CONTINUOUSLY);
		 }
	 
	 public void stop() { clip.stop(); }
}