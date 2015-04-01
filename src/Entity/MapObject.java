package Entity;

import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Tile;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class MapObject {
	
	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	// position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	// dimensions
	protected int width;
	protected int height;
	
	// collision box
	protected int cwidth;
	protected int cheight;
	
	// collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	protected boolean shadowbl;
	protected boolean shadowbr;
	
	// necessary contact stuff
	protected boolean contactFromBelow;
	protected boolean contactFromAbove;
	
	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	protected Color shadowColor;
	protected int xoff, yoff;
	
	// shadow
	protected BufferedImage shadow;
	
	// movement
	protected boolean takingInput = true;
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	protected String lastDirection;
	
	// movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	// constructor
	public MapObject(TileMap tm) 
	{
		tileMap = tm;
		tileSize = tm.getTileSize();
		shadowColor = new Color(0, 0, 0, 128);
		try 
		{
			shadow = ImageIO.read(getClass().getResourceAsStream("/Sprites/shadow.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean intersects(MapObject o) 
	{
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() 
	{
		return new Rectangle((int)x - cwidth, (int)y - cheight, cwidth, cheight);
	}
	
	public void calculateCorners(double x, double y) 
	{
		
		int leftTile = (int)(x - cwidth / 2) / tileSize;
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
		int topTile = (int)(y - cheight / 2) / tileSize;
		int bottomTile = (int)(y + cheight / 2 - 1) / tileSize;
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
		
	}
	
	public void checkTileMapCollision() 
	{
		
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydest);
		
		if(dy < 0) 
		{
			if(topLeft || topRight) 
			{
				dy = 0;
				ytemp = currRow * tileSize + cheight / 2;
				
				contactFromAbove = true;
				
			}
			else 
			{
				ytemp += dy;
				
				contactFromAbove = false;
			}
		}
		
		if(dy > 0) 
		{
			if(bottomLeft || bottomRight) 
			{
				dy = 0;
				falling = false;
				ytemp = (currRow + 1) * tileSize - cheight / 2;
			}
			else 
			{
				ytemp += dy;
			}
		}
		
		calculateCorners(xdest, y);
		
		if(dx < 0) 
		{
			if(topLeft || bottomLeft) 
			{
				dx = 0;
				xtemp = currCol * tileSize + cwidth / 2;
			}
			else 
			{
				xtemp += dx;
			}
		}
		
		if(dx > 0) 
		{
			if(topRight || bottomRight) 
			{
				dx = 0;
				xtemp = (currCol + 1) * tileSize - cwidth / 2;
			}
			else 
			{
				xtemp += dx;
			}
		}
		
		if(!falling) 
		{
			calculateCorners(x, ydest + 1);
			if(!bottomLeft && !bottomRight) 
			{
				falling = true;
			}
		}
		
	}
	
	public int getx() { return (int)x; }
	public int gety() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }
	
	public void setPosition(double x, double y) 
	{
		this.x = x;
		this.y = y;
	}
	public void setVector(double dx, double dy) 
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition() 
	{
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}
	
	public void setLeft(boolean b) 
	{ 
		if(b == false)
		{
			left = b;
			//lastDirection = "left";
		}
			
		else if(takingInput)
		{
			left = b;
			lastDirection = "left";
		}
	}
	
	public void setRight(boolean b) 
	{ 
		if(b == false)
		{
			right = b;
			//lastDirection = "right";
		}
			
		else if(takingInput)
		{
			right = b;
			lastDirection = "right";
		}
	}
	
	public void setUp(boolean b) 
	{ 
		if(b == false)
		{
			up = b;
			//lastDirection = "up";
		}
			
		else if(takingInput)
		{
			up = b;
			lastDirection = "up";
		}
	}
	
	public void setDown(boolean b)
	{ 
		if(b == false)
		{
			down = b;
			//lastDirection = "down";
		}
			
		else if(takingInput)
		{
			down = b;
			lastDirection = "down";
		}
	}
	
	public void stopInput()
	{
		takingInput = false;
	}
	
	public void resumeInput()
	{
		takingInput = true;
	}
	
	public void setJumping(boolean b) { jumping = b; }
	
	public boolean notOnScreen() 
	{
		return x + xmap + width < 0 || x + xmap - width > GamePanel.WIDTH || y + ymap + height < 0 || y + ymap - height > GamePanel.HEIGHT;
	}
	
	public void specialShadow(int xoff, int yoff)
	{
		this.xoff += xoff;
		this.yoff += yoff;
	}
	
	public void normalizeShadow()
	{
		xoff = 0; yoff = 0;
	}
	
	public void draw(java.awt.Graphics2D g) 
	{
		
		if(facingRight) 
		{
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height / 2), null);
			g.drawImage(shadow, null, (int)(x + xmap - width / 2) - 6 + xoff, (int)(y + ymap - height / 2) + 28 + yoff);
			//g.setColor(shadowColor);
			//g.fillOval((int)(x + xmap - width / 2) - 6 + xoff, (int)(y + ymap - height / 2) + 28 + yoff, 44, 8);
			
		}
		else 
		{
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width), (int)(y + ymap - height / 2), -width, height, null);
			g.drawImage(shadow, null, (int)(x + xmap - width / 2) - 6 + xoff, (int)(y + ymap - height / 2) + 28 + yoff);
			//g.setColor(shadowColor);
			//g.fillOval((int)(x + xmap - width / 2) - 6 + xoff, (int)(y + ymap - height / 2) + 28 + yoff, 44, 8);
		}
	}
	
}
















