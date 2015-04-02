package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Armor extends MapObject {
	
	private int location;
	private int material;
	
	// materials
	private final static int MATERIAL_NONE = 0;
	private final static int MATERIAL_LEATHER = 1;
	private final static int MATERIAL_STEEL = 2;
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
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
	private boolean playerSwinging;
	private double xorig, yorig;

	public Armor(TileMap tm, int location, String material) 
	{
		
		super(tm);
		
		hasShadow = false;
		
		this.location = location;
		
		if(material.equals("none"))
		{
			this.material = MATERIAL_NONE;
		}
		else if(material.equals("leather"))
		{
			this.material = MATERIAL_LEATHER;		
		}
		else if(material.equals("steel"))
		{
			this.material = MATERIAL_STEEL;
		}
		
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
			
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/hero.testblue.png"));
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 9; i++) 
			{
				
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) 
				{
					
					//if(i != SWINGING && i != SWINGINGUP && i != SWINGINGDOWN) 
					//{
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
					//}
					//else if(i == SWINGING)
					//{
					//	bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
					//}
					//else if(i == SWINGINGUP)
					//{
					//	bi[j] = spritesheet.getSubimage(j * width, i * height, width, height * 2);
					//}
					//else if(i == SWINGINGDOWN)
					//{
					//	bi[j] = spritesheet.getSubimage(j * width, i * height + height, width, height * 2);
					//}
					
				}
				
				sprites.add(bi);
				
				
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLEDOWN;
		animation.setFrames(sprites.get(IDLEDOWN));
		animation.setDelay(400);
		
		
	}
	
	
	public void update(Player player)
	{
		
		this.setPosition(player.getx(), player.gety());
		
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
			stopInput();
			
			if(currentAction != SWINGING) 
			{
				//sfx.get("swing").play();
				currentAction = SWINGING;
				if(lastDirection == "left" || lastDirection == "right")
				{
					
					animation.setFrames(sprites.get(SWINGING));
					animation.setDelay(75);
					width = 32;
					height = 32;
					
				}
				else if(lastDirection == "up")
				{
					
					animation.setFrames(sprites.get(SWINGINGUP));
					animation.setDelay(75);
					width = 32;
					height = 32;
					
					specialShadow(0, +32);
					
				}
				else if(lastDirection == "down")
				{
					
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
		if(material.equals("none"))
		{
			this.material = MATERIAL_NONE;
			
			sprites.clear();
			
			try 
			{
				
				BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Armor/noarmor.png"));
				
				sprites = new ArrayList<BufferedImage[]>();
				for(int i = 0; i < 9; i++) 
				{
					
					BufferedImage[] bi = new BufferedImage[numFrames[i]];
					
					for(int j = 0; j < numFrames[i]; j++) 
					{
						
						if(i != SWINGING && i != SWINGINGUP && i != SWINGINGDOWN) 
						{
							bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
						}
						else if(i == SWINGING)
						{
							bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
						}
						else if(i == SWINGINGUP)
						{
							bi[j] = spritesheet.getSubimage(j * width, i * height, width, height * 2);
						}
						else if(i == SWINGINGDOWN)
						{
							bi[j] = spritesheet.getSubimage(j * width, i * height + height, width, height * 2);
						}
						
					}
					
					sprites.add(bi);
					
					
				}
				
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
		else if(material.equals("leather"))
		{
			this.material = MATERIAL_LEATHER;	
			
			sprites.clear();
			
			try 
			{
				
				BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Armor/leatherarmor.png"));
				
				sprites = new ArrayList<BufferedImage[]>();
				for(int i = 0; i < 9; i++) 
				{
					
					BufferedImage[] bi = new BufferedImage[numFrames[i]];
					
					for(int j = 0; j < numFrames[i]; j++) 
					{
						
						if(i != SWINGING && i != SWINGINGUP && i != SWINGINGDOWN) 
						{
							bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
						}
						else if(i == SWINGING)
						{
							bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
						}
						else if(i == SWINGINGUP)
						{
							bi[j] = spritesheet.getSubimage(j * width, i * height, width, height * 2);
						}
						else if(i == SWINGINGDOWN)
						{
							bi[j] = spritesheet.getSubimage(j * width, i * height + height, width, height * 2);
						}
						
					}
					
					sprites.add(bi);
					
					
				}
				
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
		else if(material.equals("steel"))
		{	
			this.material = MATERIAL_STEEL;
			
			sprites.clear();
			
			try 
			{
				
				BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Armor/steelarmor.png"));
				
				sprites = new ArrayList<BufferedImage[]>();
				for(int i = 0; i < 9; i++) 
				{
					
					BufferedImage[] bi = new BufferedImage[numFrames[i]];
					
					for(int j = 0; j < numFrames[i]; j++) 
					{
						
						if(i != SWINGING && i != SWINGINGUP && i != SWINGINGDOWN) 
						{
							bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
						}
						else if(i == SWINGING)
						{
							bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
						}
						else if(i == SWINGINGUP)
						{
							bi[j] = spritesheet.getSubimage(j * width, i * height, width, height * 2);
						}
						else if(i == SWINGINGDOWN)
						{
							bi[j] = spritesheet.getSubimage(j * width, i * height + height, width, height * 2);
						}
						
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
	
}
