package com.emretech.projectgame.window;

//import java.util.Scanner;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

import com.emretech.projectgame.framework.*;
import com.emretech.projectgame.objects.*;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private Thread thread;
	Handler handler;
	Camera cam;
	Music_and_Sounds music_sounds;
	GameStateManager gameState = new GameStateManager();
	Graphics g;
	Clip clip;
	public static int WIDTH, HEIGHT;
	public static int updates = 0, frames = 0;
	private int levelNumber = 1;
	public static int coins = 0;
	public static boolean super_speed = false;
	private boolean music = false;
	
	private BufferedImage level;
	private BufferedImage shop;
	
	private void init(){
		WIDTH = getWidth();
		HEIGHT = getHeight();
		String currentDirectory = System.getProperty("user.dir");
		System.out.println(currentDirectory);
		
		BufferedImageLoader imageLoader = new BufferedImageLoader();
		
		level = imageLoader.loadImage("/Untitled.png");
		shop = imageLoader.loadImage("/Shop.png");
		
		handler = new Handler();
		
		cam = new Camera(0,0);
		
		try {
			music_sounds = new Music_and_Sounds(currentDirectory + "/res/Test.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
		switchLevel(levelNumber);
		
		/*handler.addObject(new Player(Game.WIDTH / 2,100, handler,ObjectId.Player));
		
		handler.createLevel();*/
		KeyInput key = new KeyInput(handler, gameState);
		this.addKeyListener(key);
		
		if (music) {
			JOptionPane.showMessageDialog(null, "Music: On");
			music_sounds.play();
		}
		else {
			JOptionPane.showMessageDialog(null, "Music: Off");
			music_sounds.setVolume(0.0f);
		}
	}
	
	public synchronized void start() {
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				try {
					tick();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	private void tick() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player)
				cam.tick(handler.object.get(i));
		}
		clip = music_sounds.returnClip();
		if (gameState.isPaused())
			music_sounds.pause();
		else {
			if (!music_sounds.returnPlaying())
				music_sounds.resume();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
			
		//Draw here
		if (!gameState.isPaused()) {
			g.setColor(Color.blue);
			g.fillRect(0, 0, getWidth(), getHeight());
		
			g2d.translate((int)cam.getX(), (int)cam.getY());
			
			handler.render(g);
		
			g2d.translate((int)-cam.getX(), (int)-cam.getY());

			g.setColor(Color.white);
			g.drawString("Coins: " + coins, 20, 20);
		}
		else {
			g.setColor(Color.white);
			g.fillRect(0,0,getWidth(),getHeight());
		}
		g.dispose();
		bs.show();
	}
	private void switchLevel(int levelNumber) {
		switch (levelNumber) {
		case 0:
			LoadImageLevel(shop);
			break;
		case 1:
			LoadImageLevel(level);
			break;
		default:
			break;
		}
	}
	private void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("Width: " + w + " Height: " + h);
		
		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				float red = (pixel >> 16) & 0xff;
				float green = (pixel >> 8) & 0xff;
				float blue = (pixel) & 0xff;
				
				if (red == 0 & green == 0 & blue == 0) handler.addObject(new Block(xx * 32, yy * 32, ObjectId.Block));
				if (red == 0 & green == 0 & blue == 255) handler.addObject(new Player(xx * 32, yy * 32, handler, ObjectId.Player));
				if (red == 245 & green == 242 & blue == 8) handler.addObject(new Coin(xx * 32, yy * 32, ObjectId.Coin));
				if (red == 182 & green == 122 & blue == 9) handler.addObject(new BrownBox(xx * 32, yy * 32, ObjectId.BrownBox));
			}
		}
	}
	public static void main(String[] args) {
		new Window(800,600,"Game Prototype", new Game());
	}
}
