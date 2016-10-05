package jrAlex.core;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public abstract class View extends JPanel
{
	private static final long serialVersionUID = 1L;

	public abstract void update(int delta);
	
	public View()
	{
		addKeybind("ESC", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				MainWindow.setView(new MenuView());
			}
		});
	}

	protected void addKeybind(String key, AbstractAction action)
	{
		getInputMap().put(KeyStroke.getKeyStroke(key), key);
		getActionMap().put(key, action);
	}
}
