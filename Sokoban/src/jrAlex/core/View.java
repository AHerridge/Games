package jrAlex.core;

import javax.swing.JPanel;

public abstract class View extends JPanel
{
	private static final long serialVersionUID = 1L;

	public abstract void update(int delta);
}
