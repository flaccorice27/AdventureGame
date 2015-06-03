package Entity;

import TileMap.*;
import Audio.AudioPlayer;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player extends MapObject {
	
	// player stuff
	private int health;
	private int maxHealth;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	
	private Armor chestplate;
	private Armor gauntlets;
	private Armor leggings;
	private Armor boots;
	private Armor helmet;
	
	private int chestplateMat;
	private int gauntletsMat;
	private int leggingsMat;
	private int bootsMat;
	private int helmetMat;
	
	// scratch
	private boolean swinging;
	private int swordDamage;
	private int swordRange;
	
	// animations
	private double xorig, yorig;
	private ArrayList<BufferedImage[]> sprites;
	private int animNumber = 0;
	private final int[] numFrames = {1, 1, 1, 8, 8, 15, 5, 5, 5};
	
	// animation actions
	private static final int IDLEHORIZONTAL = 0;
	private static final int IDLEDOWN = 1;
	private static final int IDLEUP = 2;
	private static final int WALKINGUP = 3;
	private static final int WALKINGDOWN = 4;
	private static final int WALKINGHORIZONTAL = 5;
	private static final int SWINGING = 6;
	private static final int SWINGINGUP = 7;
	private static final int SWINGINGDOWN = 8;
	
	private HashMap<String, AudioPlayer> sfx;
	
	public Player(TileMap tm, int x, int y) {
		
		super(tm);
		
		width = 32;
		height = 32;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		//stopSpeed = 0.4;
		
		facingRight = true;
		
		health = maxHealth = 5;
		
		setPosition(x, y);
		
		swordDamage = 8;
		swordRange = 40;
		
		// instantiate armors
		chestplate = new Armor(tileMap, 1, "test", getx(), gety());
		gauntlets = new Armor(tileMap, 2, "test", getx(), gety());
		leggings = new Armor(tileMap, 3, "test", getx(), gety());
		boots = new Armor(tileMap, 4, "test", getx(), gety());
		helmet = new Armor(tileMap, 5, "test", getx(), gety());
		
		// load sprites
		try 
		{
			
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/hero.png"));
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 9; i++) 
			{
				
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) 
				{
					
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLEHORIZONTAL;
		animation.setFrames(sprites.get(IDLEHORIZONTAL));
		animation.setDelay(400);
		
		sfx = new HashMap<String, AudioPlayer>();
		//sfx.put("jump", new AudioPlayer("/SFX/jump.mp3"));
		//sfx.put("swing", new AudioPlayer("/SFX/scratch.mp3"));
		
	}
	
	public int getHealth() 
	{ 
		return health; 
	}
	public int getMaxHealth()
	{ 
		return maxHealth; 
	}
	
	public void setSwinging() 
	{
		swinging = true;
	}
	
	public boolean isSwinging()
	{
		return swinging;
	}
	
	public int getAction()
	{
		return currentAction;
	}
	
	public String getDirection()
	{
		return lastDirection;
	}
	
	public void changeArmor(int location, String material)
	{
		switch(location)
		{
			case 1:
				chestplate.setMaterial(material);
				break;
				
			case 2:
				gauntlets.setMaterial(material);
				break;
				
			case 3:
				leggings.setMaterial(material);
				break;
				
			case 4:
				boots.setMaterial(material);
				break;
				
			case 5:
				helmet.setMaterial(material);
				break;
		}
	}
	
	public void checkAttack(ArrayList<Enemy> enemies) 
	{
		
		// loop through enemies
		/*for(int i = 0; i < enemies.size(); i++) 
		{
			
			Enemy e = enemies.get(i);
			
			// scratch attack
			if(swinging) 
			{
				if(facingRight)
				{
					if(e.getx() > x && e.getx() < x + swordRange && e.gety() > y - height / 2 && e.gety() < y + height / 2) 
					{
						e.hit(swordDamage);
					}
				}
				else {
					if(e.getx() < x && e.getx() > x - swordRange && e.gety() > y - height / 2 && e.gety() < y + height / 2) 
					{
						e.hit(swordDamage);
					}
				}
			}
			
			// check enemy collision
			if(intersects(e)) 
			{
				hit(e.getDamage());
			}
			
		}*/
		
	}
	
	public void hit(int damage) 
	{
		if(flinching) return;
		health -= damage;
		if(health < 0) health = 0;
		if(health == 0) dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
	private void getNextPosition() 
	{
		
		// horizontal movement
		if(left) 
		{
			dx -= moveSpeed;
			if(dx < -maxSpeed) 
			{
				dx = -maxSpeed;
			}
		}
		else if(right) 
		{
			dx += moveSpeed;
			if(dx > maxSpeed) 
			{
				dx = maxSpeed;
			}
		}
		else 
		{
			if(dx > 0) 
			{
				//dx -= stopSpeed;
				//if(dx < 0) 
				//{
					dx = 0;
				//}
			}
			else if(dx < 0) 
			{
				//dx += stopSpeed;
				//if(dx > 0) 
				//{
					dx = 0;
				//}
			}
		}
		
		// vertical movement
		if(up) 
		{
			dy -= moveSpeed;
			if(dy < -maxSpeed) 
			{
				dy = -maxSpeed;
			}
		}
		else if(down) 
		{
			dy += moveSpeed;
			if(dy > maxSpeed) 
			{
				dy = maxSpeed;
			}
		}
		else 
		{
			if(dy > 0) 
			{
				//dy -= stopSpeed;
				//if(dy < 0) 
				//{
					dy = 0;
				//}
			}
			else if(dy < 0) 
			{
				//dy += stopSpeed;
				//if(dy > 0) 
				//{
					dy = 0;
				//}
			}
		}
		
		// cannot move while attacking
		if(currentAction == SWINGING) 
		{
			dx = 0;
			dy = 0;
		}
		
	}
	
	public void update()
	{
	
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(previousAction == SWINGING && animation.hasPlayedOnce())
		{

			resumeInput();
			
		}
		
		// check attack has stopped
		if(currentAction == SWINGING) 
		{
			if(animation.hasPlayedOnce()) swinging = false;
		}
		
		// check done flinching
		if(flinching) 
		{
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 1000) 
			{
				flinching = false;
			}
		}
		
		// set animation
		if(swinging) 
		{
			xorig = x;
			yorig = y;
			//stopInput();
			
			if(currentAction != SWINGING) 
			{
				//sfx.get("swing").play();
				currentAction = SWINGING;
				if(lastDirection == "left" || lastDirection == "right")
				{
					animNumber = SWINGING;
					animation.setFrames(sprites.get(SWINGING));
					animation.setDelay(75);
					width = 32;
					height = 32;
					
				}
				else if(lastDirection == "up")
				{
					//y -= 16;
					animNumber = SWINGINGUP;
					animation.setFrames(sprites.get(SWINGINGUP));
					animation.setDelay(75);
					width = 32;
					height = 32;
					
					//specialShadow(0, +32);
					
				}
				else if(lastDirection == "down")
				{
					//y += 16;
					animNumber = SWINGINGDOWN;
					animation.setFrames(sprites.get(SWINGINGDOWN));
					animation.setDelay(75);
					width = 32;
					height = 32;
					
				}
			}
		}
		else if(left || right)
		{
			if(currentAction != WALKINGHORIZONTAL)
			{
				animNumber = WALKINGHORIZONTAL;
				currentAction = WALKINGHORIZONTAL;
				animation.setFrames(sprites.get(WALKINGHORIZONTAL));
				animation.setDelay(50);
				width = 32;
				height = 32;
				
			}
		}
		
		else if(up) 
		{
			if(currentAction != WALKINGUP)
			{
				animNumber = WALKINGUP;
				currentAction = WALKINGUP;
				animation.setFrames(sprites.get(WALKINGUP));
				animation.setDelay(100);
				width = 32;
				height = 32;
				
			}
		}
		else if(down) 
		{
			if(currentAction != WALKINGDOWN) 
			{
				animNumber = WALKINGDOWN;
				currentAction = WALKINGDOWN;
				animation.setFrames(sprites.get(WALKINGDOWN));
				animation.setDelay(100);
				width = 32;
				height = 32;
				
			}
		}
		else 
		{
			if(dx == 0 && (lastDirection == "left" || lastDirection == "right"))
			{
				if(currentAction != IDLEHORIZONTAL) 
				{
					animNumber = IDLEHORIZONTAL;
					currentAction = IDLEHORIZONTAL;
					animation.setFrames(sprites.get(IDLEHORIZONTAL));
					animation.setDelay(300);
					width = 32;
					height = 32;
					
				}
			}
			
			if(dy == 0 && lastDirection == "down")
			{
				if(currentAction != IDLEDOWN)
				{
					animNumber = IDLEDOWN;
					currentAction = IDLEDOWN;
					animation.setFrames(sprites.get(IDLEDOWN));
					animation.setDelay(300);
					width = 32;
					height = 32;
					
				}
			}
			
			if(dy == 0 && lastDirection == "up")
			{
				if(currentAction != IDLEUP)
				{
					animNumber = IDLEUP;
					currentAction = IDLEUP;
					animation.setFrames(sprites.get(IDLEUP));
					animation.setDelay(300);
					width = 32;
					height = 32;
					
				}
			}
		}
		
		animation.update();
		
		previousAction = currentAction;
		
		chestplate.update(this, animNumber);
		gauntlets.update(this, animNumber);
		leggings.update(this, animNumber);
		boots.update(this, animNumber);
		helmet.update(this, animNumber);
		
		// set direction
		if(currentAction != SWINGING) 
		{
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		// draw player
		if(flinching) 
		{
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) 
			{
				return;
			}
		}
		
		super.draw(g);
		
		/*
		chestplate.draw(g);
		gauntlets.draw(g);
		leggings.draw(g);
		boots.draw(g);
		helmet.draw(g);
		*/
		
	}
	
}

















