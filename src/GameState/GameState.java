package GameState;

import Audio.AudioPlayer;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected AudioPlayer bgMusic;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void end();
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
	public AudioPlayer getBGMusic()
	{
		return bgMusic;
	}
	
}
