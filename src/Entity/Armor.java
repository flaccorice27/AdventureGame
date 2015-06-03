package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Armor extends MapObject {
	
	private int location;
	private int material;
	
	//JESUS FUCKING CHRIST
	
	// materials
	private final static int MATERIAL_TEST = 0;
	private final static int MATERIAL_NONE = 1;
	private final static int MATERIAL_LEATHER = 2;
	private final static int MATERIAL_COPPER = 3;
	private static final int MATERIAL_BRONZE = 4;
	private static final int MATERIAL_IRON = 5;
	private static final int MATERIAL_STEEL = 6;
	private static final int MATERIAL_TITANIUM = 7;
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1, 1, 1, 8, 8, 15, 5, 5, 5};
	private ArmorManager manager;
	
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
	private boolean playerSwinging;
	private double xorig, yorig;

	public Armor(TileMap tm, int location, String material, int x, int y) 
	{
		
		super(tm);
		
		hasShadow = false;
		
		this.location = location;
		
		// determine material
		if(material.equals("test"))
		{
			this.material = MATERIAL_TEST;
		}
		else if(material.equals("none"))
		{
			this.material = MATERIAL_NONE;
		}
		else if(material.equals("leather"))
		{
			this.material = MATERIAL_LEATHER;		
		}
		else if(material.equals("copper"))
		{
			this.material = MATERIAL_COPPER;
		}
		else if(material.equals("bronze"))
		{
			this.material = MATERIAL_BRONZE;
		}
		else if(material.equals("iron"))
		{
			this.material = MATERIAL_IRON;
		}
		else if(material.equals("steel"))
		{
			this.material = MATERIAL_STEEL;
		}
		else if(material.equals("titanium"))
		{
			this.material = MATERIAL_TITANIUM;
		}
		
		manager = new ArmorManager(this.location, this.material);
		
		setPosition(x, y);
		
		width = 32;
		height = 32;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		
		facingRight = true;
		
		try 
		{
			
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(manager.getSpriteSheet()));
			
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
	
	public void update(Player player, int anim)
	{
		
		// update position
		//getNextPosition();
		//checkTileMapCollision();
		//setPosition(xtemp, ytemp);
		
		setPosition(player.getx(), player.gety());
		
		//this.currentAction = player.getAction();
		this.playerSwinging = player.isSwinging();
		this.lastDirection = player.getDirection();
		
		setLeft(player.isLeft()); setRight(player.isRight()); setUp(player.isUp()); setDown(player.isDown());
		
		if(previousAction == SWINGING && animation.hasPlayedOnce())
		{
			
			normalizeShadow();
			resumeInput();
			
		}
		
		// check attack has stopped
		if(currentAction == SWINGING) 
		{
			if(animation.hasPlayedOnce()) playerSwinging = false;
		}
		
		// set animation
		if(playerSwinging) 
		{
			xorig = x;
			yorig = y;
			
			if(currentAction != SWINGING) 
			{
				//sfx.get("swing").play();
				currentAction = SWINGING;
				if(anim == SWINGING)
				{
					
					animation.setFrames(sprites.get(SWINGING));
					animation.setDelay(75);
					width = 32;
					height = 32;
					
				}
				else if(anim == SWINGINGUP)
				{
					
					animation.setFrames(sprites.get(SWINGINGUP));
					animation.setDelay(75);
					width = 32;
					height = 32;
					
					//specialShadow(0, +32);
					
				}
				else if(anim == SWINGINGDOWN)
				{
					
					animation.setFrames(sprites.get(SWINGINGDOWN));
					animation.setDelay(75);
					width = 32;
					height = 32;
					
				}
			}
		}
		
		else if(anim == WALKINGHORIZONTAL)
		{
			if(currentAction != WALKINGHORIZONTAL)
			{
				currentAction = WALKINGHORIZONTAL;
				animation.setFrames(sprites.get(WALKINGHORIZONTAL));
				animation.setDelay(50);
				width = 32;
				height = 32;
				
			}
		}
		
		else if(anim == WALKINGUP) 
		{
			if(currentAction != WALKINGUP)
			{
				currentAction = WALKINGUP;
				animation.setFrames(sprites.get(WALKINGUP));
				animation.setDelay(100);
				width = 32;
				height = 32;
				
			}
		}
		else if(anim == WALKINGDOWN) 
		{
			if(currentAction != WALKINGDOWN) 
			{
				currentAction = WALKINGDOWN;
				animation.setFrames(sprites.get(WALKINGDOWN));
				animation.setDelay(100);
				width = 32;
				height = 32;
				
			}
		}
		else 
		{
			if(anim == IDLEHORIZONTAL)
			{
				if(currentAction != IDLEHORIZONTAL) 
				{
					currentAction = IDLEHORIZONTAL;
					animation.setFrames(sprites.get(IDLEHORIZONTAL));
					animation.setDelay(300);
					width = 32;
					height = 32;
					
				}
			}
			
			if(anim == IDLEDOWN)
			{
				if(currentAction != IDLEDOWN)
				{
					currentAction = IDLEDOWN;
					animation.setFrames(sprites.get(IDLEDOWN));
					animation.setDelay(300);
					width = 32;
					height = 32;
					
				}
			}
			
			if(anim == IDLEUP)
			{
				if(currentAction != IDLEUP)
				{
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
		
		// set direction
		if(currentAction != SWINGING) 
		{
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		
	}
	
	public void draw(Graphics2D g)
	{
		
		setMapPosition();
		
		super.draw(g);
		
	}
	
	public void setMaterial(String material)
	{
		
		manager.setMaterial(material);
		
		sprites.clear();
		
		try 
		{
			
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(manager.getSpriteSheet()));
			
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
		
	}
	
}
