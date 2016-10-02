package jrAlex.core;

import java.awt.Color;

import javax.swing.JFrame;

public class MainWindow extends JFrame
{
	private static final long		serialVersionUID	= 1L;
	private static final MainWindow	instance			= new MainWindow();

	private static View				currentView;

	private MainWindow()
	{
		setBackground(Color.white);
		setResizable(false);
		setSize(12 * 64 + 6, 12 * 64 + 29);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void setView(View newView)
	{
		if (currentView != newView)
		{
			currentView = newView;
			instance.remove(currentView);
			instance.add(newView);
		}
	}

	public void update(int delta)
	{
		if (currentView != null)
		{
			currentView.update(delta);
			currentView.repaint();
		}
	}

	public static MainWindow getInstance()
	{
		return instance;
	}
}
