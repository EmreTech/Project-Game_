package com.emretech.projectgame.framework;

import java.io.*;

import javax.swing.JOptionPane;
import javax.sound.sampled.*;

public class Music_and_Sounds {
	public void playMusic(String filepath) {
		try {
		Clip clip = AudioSystem.getClip();
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
		
		clip.open(audio);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Cannot find file");
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			JOptionPane.showMessageDialog(null, "Error: Cannot play music");
		}
		
	}
	
}
