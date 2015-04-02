package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HUD {
	
	private Player player;
	
	private BufferedImage image;
	private Font font;
	
	private int scale;
	
	public HUD(Player p, int scale) 
	{
		player = p;
		this.scale = scale;
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/HUD/hud.png"));
			image.getScaledInstance(image.getWidth() / scale, image.getHeight() / scale, 0);
			font = new Font("Arial", Font.PLAIN, 14);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(image, 0, 0, image.getWidth() / scale, image.getHeight() / scale - 12, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(player.getHealth() + "/" + player.getMaxHealth(), 30, 25);
		
	}
	
}













