package main;

import java.util.Scanner;

import javax.swing.JFrame;

public class MainClass
{

	public static Scanner console = new Scanner( System.in );

	public static JFrame window = new JFrame( "Tic Tac Toe" );

	public static void main( String[] args )
	{
		String p1Name = "" , p2Name = "";

		System.out.print( "\nPlayer O's name = " );
		p1Name = console.nextLine();
		System.out.print( "\nPlayer X's name = " );
		p2Name = console.nextLine();

		window.add( new Board( p1Name , p2Name ) );
		window.setVisible( true );
		window.setSize( 600 , 600 );
		window.setLocationRelativeTo( null );
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setResizable( false );
		window.setFocusable( true );
		window.requestFocus();

		while ( true )
		{
			window.repaint();
			try
			{
				Thread.sleep( 16 );
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
	}
}
