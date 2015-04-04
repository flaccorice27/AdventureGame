package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
//import Entity.Enemies.*;
import Audio.AudioPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class OverworldState extends GameState {
	
	private TileMap freeTileMap;
	private TileMap blockedTileMapTop, blockedTileMapBottom;
	
	private int scale;
	
	private Player player;
	
	// Both of these are to be implemented later
	private ArrayList<Enemy> enemies;
	//private ArrayList<Explosion> explosions;
	
	private HUD hud;
	
	public OverworldState(GameStateManager gsm) 
	{
		this.gsm = gsm;
		this.scale = gsm.scale;
		init();
	}
	
	public void init() 
	{
		
		freeTileMap = new TileMap(32, false, 0);
		freeTileMap.loadTiles("/Tilesets/freetileset.png");
		freeTileMap.loadMap("/Maps/testingmapfree.map");
		freeTileMap.setPosition(0, 0);
		freeTileMap.setTween(1);
		
		blockedTileMapTop = new TileMap(32, true, 1);
		blockedTileMapTop.loadTiles("/Tilesets/blockedtileset.png");
		blockedTileMapTop.loadMap("/Maps/testingmapblocked.map");
		blockedTileMapTop.setPosition(0, 0);
		blockedTileMapTop.setTween(1);
		
		blockedTileMapBottom = new TileMap(32, true, 2);
		blockedTileMapBottom.loadTiles("/Tilesets/blockedtileset.png");
		blockedTileMapBottom.loadMap("/Maps/testingmapblocked.map");
		blockedTileMapBottom.setPosition(0, 0);
		blockedTileMapBottom.setTween(1);
		
		player = new Player(blockedTileMapTop, 64, 64);
		player.setPosition(64, 64);
		
		populateEnemies();
		
		//explosions = new ArrayList<Explosion>();
		
		// The hud shows important information such as life and energy (currently denoted as a heart and a fire)
		hud = new HUD(player, scale);
		
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
		freeTileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());
		blockedTileMapTop.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());
		blockedTileMapBottom.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());
		
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
		
		// draw free tilemap
		freeTileMap.draw(g);
		
		// draw blocked tilemap bottom so it is below player
		blockedTileMapBottom.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw blocked tilemap top so it is above player
		blockedTileMapTop.draw(g);
		
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
		if(k == KeyEvent.VK_1)
		{
			player.changeArmor(1, "leather");
			player.changeArmor(2, "leather");
			player.changeArmor(3, "leather");
			player.changeArmor(4, "leather");
			player.changeArmor(5, "leather");
		}
		if(k == KeyEvent.VK_2)
		{
			player.changeArmor(1, "copper");
			player.changeArmor(2, "copper");
			player.changeArmor(3, "copper");
			player.changeArmor(4, "copper");
			player.changeArmor(5, "copper");
		}
		if(k == KeyEvent.VK_3)
		{
			player.changeArmor(1, "bronze");
			player.changeArmor(2, "bronze");
			player.changeArmor(3, "bronze");
			player.changeArmor(4, "bronze");
			player.changeArmor(5, "bronze");
		}
		if(k == KeyEvent.VK_4)
		{
			player.changeArmor(1, "iron");
			player.changeArmor(2, "iron");
			player.changeArmor(3, "iron");
			player.changeArmor(4, "iron");
			player.changeArmor(5, "iron");
		}
		if(k == KeyEvent.VK_5)
		{
			player.changeArmor(1, "steel");
			player.changeArmor(2, "steel");
			player.changeArmor(3, "steel");
			player.changeArmor(4, "steel");
			player.changeArmor(5, "steel");
		}
		if(k == KeyEvent.VK_6)
		{
			player.changeArmor(1, "titanium");
			player.changeArmor(2, "titanium");
			player.changeArmor(3, "titanium");
			player.changeArmor(4, "titanium");
			player.changeArmor(5, "titanium");
		}
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