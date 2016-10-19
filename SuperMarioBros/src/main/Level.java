package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings( "serial" )
public class Level extends JPanel implements ActionListener , KeyListener
{
	private int					mapW , mapH , tileS = 64 , buffW = Launcher.scrW / tileS / 2 + 1 ,
			buffH = Launcher.scrH / tileS / 2 + 1;
	private Block[][]			blocks;
	private ArrayList< Entity >	entities	= new ArrayList< Entity >();
	private Mario				player;
	private Rectangle			bounds;
	private boolean				atExit;
	private Timer				timer		= new Timer( 16 , this );

	public Level( File file )
	{
		loadLevel( file );
		this.setSize( Launcher.scrW , Launcher.scrH );
		this.setFocusable( true );
		this.grabFocus();
		this.addKeyListener( this );
		this.setVisible( true );
		timer.start();
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		this.repaint();

		player.move( this );

		if ( atExit )
			timer.stop();
	}

	public void paint( Graphics g )
	{
		Graphics2D g2d = ( Graphics2D ) g;

		super.paint( g2d );

		int startRow = ( player.getY() / tileS ) - buffH , startCol = ( player.getX() / tileS ) - buffW ,
				endRow = ( player.getY() / tileS ) + buffH + 1 , endCol = ( player.getX() / tileS ) + buffW + 1 ,
				xOff = player.getX() - ( player.getX() / tileS ) * tileS , yOff = player.getY() - ( player.getY() / tileS ) * tileS;
		for ( int row = startRow; row < endRow; row++ )
		{
			int y = ( row - startRow ) * tileS - yOff;
			for ( int col = startCol; col < endCol; col++ )
				if ( row >= 0 && row < mapH && col >= 0 && col < mapW )
				{
					int x = ( ( col - startCol ) * tileS - xOff );
					g2d.drawImage( blocks[ row ][ col ].getSS().getCurrAnim().getNext() , x , y , tileS , tileS ,
							null );
					g2d.drawRect( x , y , tileS , tileS );
				}
		}

		for ( Entity e : getEntities() )
			g2d.drawImage( e.getSS().getCurrAnim().getNext() , e.getX() - startCol * tileS - xOff ,
					e.getY() - startRow * tileS - yOff , tileS , tileS , null );

		g2d.drawImage( player.getSS().getCurrAnim().getNext() ,
				player.getX() - startCol * tileS - ( player.getX() - ( player.getX() / tileS ) * tileS ) ,
				player.getY() - startRow * tileS - ( player.getY() - ( player.getY() / tileS ) * tileS ) , tileS ,
				tileS , null );

		g2d.drawString( player.getX() + " " + player.getY() + " " + player.getVy() ,
				player.getX() - startCol * tileS - ( player.getX() - ( player.getX() / tileS ) * tileS ) ,
				player.getY() - startRow * tileS - ( player.getY() - ( player.getY() / tileS ) * tileS ) );
	}

	@Override
	public void keyPressed( KeyEvent e )
	{
		if ( e.getKeyCode() == KeyEvent.VK_A )
			player.setVx( -1 );
		if ( e.getKeyCode() == KeyEvent.VK_D )
			player.setVx( 1 );

		if ( e.getKeyCode() == KeyEvent.VK_W && player.getVy() == 0 )
			player.setVy( -3 );
		if ( e.getKeyCode() == KeyEvent.VK_S )
			player.setVy( 1 );
	}

	@Override
	public void keyReleased( KeyEvent e )
	{
		if ( e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D )
			player.setVx( 0 );

		if ( e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S )
			player.setVy( 0 );
	}

	@Override
	public void keyTyped( KeyEvent e )
	{

	}

	public void loadLevel( File file )
	{
		try
		{
			BufferedReader br = new BufferedReader( new FileReader( file ) );

			String currLine = br.readLine();
			mapW = Integer.parseInt( currLine );
			currLine = br.readLine();
			mapH = Integer.parseInt( currLine );

			blocks = new Block[mapH][mapW];
			setBounds( new Rectangle( 0 , 0 , mapW * tileS , mapH * tileS ) );
			player = new Mario( "Mario" , 0 , ( mapH - 2 ) * tileS );

			currLine = br.readLine();

			for ( int row = 0; row < mapH; row++ )
			{
				currLine = br.readLine();
				String[] ids = currLine.split( "," );
				for ( int col = 0; col < mapW; col++ )
				{
					blocks[ row ][ col ] = Block.values()[ Integer.parseInt( ids[ col ] ) ];
				}
			}

			currLine = br.readLine();
			currLine = br.readLine();
			String[] entitieList = currLine.split( "," );
			String[][] entityData = new String[entitieList.length][3];
			for ( int i = 0; i < entitieList.length; i++ )
				entityData[ i ] = entitieList[ i ].split( " " );
			for ( String[] entity : entityData )
				entities.add(
						new Goomba( entity[ 0 ] , Integer.parseInt( entity[ 1 ] ) , Integer.parseInt( entity[ 2 ] ) ) );

			br.close();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public int getMapW()
	{
		return mapW;
	}

	public void setMapW( int mapW )
	{
		this.mapW = mapW;
	}

	public int getMapH()
	{
		return mapH;
	}

	public void setMapH( int mapH )
	{
		this.mapH = mapH;
	}

	public int getTileS()
	{
		return tileS;
	}

	public void setTileS( int tileS )
	{
		this.tileS = tileS;
	}

	public Block[][] getBlocks()
	{
		return blocks;
	}

	public void setBlocks( Block[][] blocks )
	{
		this.blocks = blocks;
	}

	public Rectangle getBounds()
	{
		return bounds;
	}

	public void setBounds( Rectangle bounds )
	{
		this.bounds = bounds;
	}

	public ArrayList< Entity > getEntities()
	{
		return entities;
	}

	public void setEntities( ArrayList< Entity > entities )
	{
		this.entities = entities;
	}

	public Mario getPlayer()
	{
		return player;
	}

	public void setPlayer( Mario player )
	{
		this.player = player;
	}
}
