package GameSounds;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {
	
	private final File shoot;
	private final File hit;
	private final File destroyedEnemy;
	private final File takeDamage;
	private final File gameOver;
	private final File gameSound;
	
	File file1 = new File("Sounds/shoot.wav");
	File file2 = new File("Sounds/hit.wav");
	File file3 = new File("Sounds/AlienDestroyed.wav");
	File file4 = new File("Sounds/GameOver.wav");
	File file5 = new File("Sounds/GameSound3.wav");
	File file6 = new File("Sounds/TakeDamage.wav");
	
	public Sounds() {
		
		this.shoot = file1;
		this.hit = file2;
		this.destroyedEnemy = file3;
		this.gameOver = file4;
		this.gameSound = file5;
		this.takeDamage = file6;
		
	}
	
	public void soundShoot() {
		play(shoot);
	}
	
	public void soundHit() {
		play(hit);
	}
	
	public void soundDestroyedEnemy() {
		play(destroyedEnemy);
	}
	
	public void soundGameOver() {
		play(gameOver);
	}
	
	public void soundTakeDamage() {
		play(takeDamage);
	}
	
	public void soundGame() {
		play(gameSound);
	}
	
	private void play(File file) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			final Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if(event.getType() == LineEvent.Type.STOP) {
						clip.close();
					}
				}
			});
			audioIn.close();
			clip.start();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			System.err.println(e);
		}
	}

}
