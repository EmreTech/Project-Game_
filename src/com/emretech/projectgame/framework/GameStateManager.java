package com.emretech.projectgame.framework;

public class GameStateManager {
	public boolean paused = false;
	public boolean cutscene = false;
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	public boolean isPaused() {
		return paused;
	}
	public void setCutscene(boolean cutscene) {
		this.cutscene = cutscene;
	}
	public boolean isCutscene() {
		return cutscene;
	}
}
