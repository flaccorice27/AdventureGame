package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;
import Audio.AudioPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class OverworldState extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	// Both of these are to be implemented later
	private ArrayList<Enemy> enemies;
	//private ArrayList<Explosion> explosions;
	
	private HUD hud;
	
	public OverworldState(GameStateManager gsm) 
	{
		this.gsm = gsm;
		init();
	}
	
	public void init() 
	{
		
		tileMap = new TileMap(32);
		tileMap.loadTiles("/Tilesets/testtileset2.png");
		tileMap.loadMap("/Maps/testmap3.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
		
		populateEnemies();
		
		//explosions = new ArrayList<Explosion>();
		
		// The hud shows important information such as life and energy (currently denoted as a heart and a fire)
		hud = new HUD(player);
		
		bgMusic = new AudioPlayer("/Music/Overworld_Theme.wav");
		bgMusic.play();
		
	}
	
	private void populateEnemies() 
	{
		
		enemies = new ArrayList<Enemy>();
		
	}
	
	public void update()
	{
		
		// update player
		player.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());
		
		// The following code is preemptive of the implementation of enemies
		//
		// attack enemies
		//player.checkAttack(enemies);
		
		// update all enemies
		/*for(int i = 0; i < enemies.size(); i++) 
		{
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) 
			{
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));
			}
		}*/
		
		
		
		// update explosions
		/*for(int i = 0; i < explosions.size(); i++) 
		{
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) 
			{
				explosions.remove(i);
				i--;
			}
		}*/
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		//bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw enemies
		/*for(int i = 0; i < enemies.size(); i++) 
		{
			enemies.get(i).draw(g);
		}*/
		
		// draw explosions
		/*for(int i = 0; i < explosions.size(); i++) 
		{
			explosions.get(i).setMapPosition((int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}*/
		
		// draw hud
		hud.draw(g);
		
	}
	
	public void end()
	{
		bgMusic.stop();
	}
	
	public void keyPressed(int k) 
	{
		if(k == KeyEvent.VK_A) player.setLeft(true);
		if(k == KeyEvent.VK_D) player.setRight(true);
		if(k == KeyEvent.VK_W) player.setUp(true);
		if(k == KeyEvent.VK_S) player.setDown(true);
		if(k == KeyEvent.VK_R) player.setSwinging();
		if(k == KeyEvent.VK_ESCAPE) gsm.pause();
	}
	
	public void keyReleased(int k) 
	{
		if(k == KeyEvent.VK_A) player.setLeft(false);
		if(k == KeyEvent.VK_D) player.setRight(false);
		if(k == KeyEvent.VK_W) player.setUp(false);
		if(k == KeyEvent.VK_S) player.setDown(false);
		if(k == KeyEvent.VK_R) player.setJumping(false);
	}
	
}