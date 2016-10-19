package game;

import javax.swing.JFrame;

public class GameMaker
{

	public static void main( String[] args )
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( 1336 , 768 );
		frame.add( new GamePanel() );
		frame.setLocationRelativeTo( null );
		frame.setVisible( true );
	}

}
