package com.emretech.projectgame.framework;

import java.io.*;
import javax.sound.sampled.*;

public class Music_and_Sounds {
	
	private AudioInputStream audio;
	private Clip clip;
	
	Long currentFrame;
	String filepath;
	boolean playing = false;
	
	public Music_and_Sounds(String filepath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.filepath = filepath;
		audio = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
		clip = AudioSystem.getClip();
		
		clip.open(audio);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void play() {
		clip.start();
		playing = true;
	}
	public void pause() {
		this.currentFrame = this.clip.getMicrosecondPosition();
		clip.stop();
		playing = false;
	}
	public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(currentFrame);
		this.play();
	}
	public void stop() {
		currentFrame = 0L;
		clip.stop();
		clip.close();
		playing = false;
	}
	private void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audio = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
		clip.open(audio);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public Clip returnClip() {
		return clip;
	}
	public boolean returnPlaying() {
		return playing;
	}
}
