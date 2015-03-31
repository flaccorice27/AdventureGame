package GameState;

import java.util.ArrayList;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	private boolean paused = false;
	
	public static final int NUMGAMESTATES = 3;
	public static final int MENUSTATE = 0;
	public static final int PAUSEDSTATE = 1;
	public static final int OVERWORLDSTATE = 2;
	
	public GameStateManager() 
	{
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		previousState = MENUSTATE;
		loadState(currentState);
		
	}
	
	private void loadState(int state) 
	{
		if(state == MENUSTATE)
		{
			gameStates[state] = new MenuState(this);
		}
		if(state == OVERWORLDSTATE)
		{
			gameStates[state] = new OverworldState(this);
		}
		if(state == PAUSEDSTATE)
		{
			gameStates[state] = new PausedState(this);
		}
	}
	
	private void unloadState(int state) 
	{
		gameStates[state].end();
		gameStates[state] = null;
	}
	
	public void setState(int state) 
	{
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		//gameStates[currentState].init();
	}
	
	public void pause()
	{
		gameStates[currentState].bgMusic.stop();
		
		loadState(PAUSEDSTATE);
		previousState = currentState;
		currentState = PAUSEDSTATE;
		paused = true;
	}
	
	public void resume()
	{
		gameStates[previousState].getBGMusic().resume();
		
		unloadState(PAUSEDSTATE);
		currentState = previousState;
		paused = false;
	}
	
	public void update() 
	{
		
		try 
		{
			gameStates[currentState].update();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
			
	}
	
	public void draw(java.awt.Graphics2D g) 
	{
		if(paused)
		{
			try 
			{
				gameStates[1].draw(g);
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			try 
			{
				gameStates[currentState].draw(g);
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void keyPressed(int k) 
	{
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) 
	{
		gameStates[currentState].keyReleased(k);
	}
	
}
