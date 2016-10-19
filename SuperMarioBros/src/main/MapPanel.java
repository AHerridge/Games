package main;

import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings( "serial" )
public class MapPanel extends JPanel
{
	private ArrayList< Level >	levels	= new ArrayList< Level >();
	private Level				currLevel;

	public MapPanel()
	{
		loadLevels();
		currLevel = levels.get( 0 );
		this.add( currLevel );
		this.setLayout( new GridLayout( 1 , 1 ) );
		this.setSize( Launcher.scrW , Launcher.scrH );
		this.setVisible( true );
	}

	public void loadLevels()
	{
		File dir = new File( "maps" );
		File[] files = dir.listFiles();

		for ( File file : files )
			levels.add( new Level( file ) );
	}
}
