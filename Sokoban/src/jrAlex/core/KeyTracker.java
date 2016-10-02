package jrAlex.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyTracker implements KeyListener
{
	private static boolean[] keys;
	
	public KeyTracker()
	{
		keys = new boolean[KeyEvent.KEY_LAST];
	}
	
	public boolean isKeyDown(int key)
	{
		return keys[key];
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
}
