package main;

import javax.swing.JFrame;

public class Launcher
{
	public static final int scrW = 1344 , scrH = 768;

	public static void main( String[] args )
	{
		JFrame frm = new JFrame( "Game" );
		frm.setSize( scrW , scrH );
		frm.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frm.setLocationRelativeTo( null );
		frm.setResizable( false );
		frm.add( new MapPanel() );
		frm.setVisible( true );
	}
}
