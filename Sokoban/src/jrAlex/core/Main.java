package jrAlex.core;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		WorldView worldView = new WorldView(64);
		
		MainWindow.setView(worldView);
		MainWindow.getInstance().setVisible(true);
		
		while(true)
		{
			Thread.sleep(135);
			MainWindow.getInstance().repaint();
			MainWindow.getInstance().update(135);
		}
	}
}
