package Entity;

import java.awt.*;
import java.util.ArrayList;

public class TextBox {
	
	private String message, token;
	private int scale, length = 1, line = 1;
	private boolean showing = false;
	
	private int lineLength = 0;
	private final int LINES = 2;
	private ArrayList<Integer> cutoffs = new ArrayList<Integer>();
	
	private int targetTime = 20;
	private long start, elapsed, wait;
	private int x = 0, y = 0, w = 0, h = 0;
	
	public TextBox(int scale)
	{
		this.scale = scale;
		lineLength = 112 * scale - 16;
		start = System.nanoTime();
		cutoffs.add(0);
	}
	
	public void setMessage(String message)
	{
		this.message = message;
		token = message.substring(0, 1);
		cutoffs.clear(); cutoffs.add(0);
	}
	
	public void show()
	{
		this.showing = true;
	}
	
	public void hide()
	{
		this.showing = false;
	}
	
	public void setTextSpeed(String speed)
	{
		if(speed.equals("slow"))
		{
			targetTime = 20;
		}
		else if(speed.equals("normal"))
		{
			targetTime = 10;
		}
		else if(speed.equals("fast"))
		{
			targetTime = 5;
		}
	}
	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g)
	{
		Font font = new Font(Font.SANS_SERIF, 0, (int)(scale * 3.5));
		int tempLength = 1, currentLine = 1;
		if(showing)
		{
			
			elapsed = System.nanoTime() - start;
				
			wait = targetTime - elapsed / 1000000;
			if(wait <= 0)
			{
				length++;
				tempLength = g.getFontMetrics(font).stringWidth(token);
				currentLine = tempLength - ((line - 1) * lineLength);
				if(currentLine >= lineLength)
				{
					line++;
					cutoffs.add(length); System.out.println("CODE EXECUTED");
				}
				if(length > message.length())
				{
					length = message.length();
				}
				start = System.nanoTime();
				//System.out.println(tempLength + " " + currentLine + " " + lineLength + " " + line + " " + cutoffs.size());
			}
			g.setColor(Color.WHITE);
			x = 4 * scale; y = 50 * scale; w = 112 * scale; h = 7 * scale;
			g.fillRect(x, y, w, h);
			g.setFont(font);
			g.setColor(Color.BLACK);
			token = message.substring(0, length);
			for(int i = 0; i < line; i++)
			{
				System.out.println(cutoffs.size());
				int begin = cutoffs.get(i);
				int end;
				if((i + 1) == cutoffs.size())
				{
					end = token.length();
				}
				else if(cutoffs.size() > 1)
				{
					end = cutoffs.get(i + 1);
				}
				else
				{
					end = token.length();
				}
				if(end > token.length()) { end = token.length(); }
				g.drawString(token.substring(begin, end), x + scale, y + (scale * 3 * (1 + (i % 2))));
			}
			
		}
	}
	
}
