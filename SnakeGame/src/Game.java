import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

@SuppressWarnings( "serial" )
public class Game extends JFrame implements ActionListener
{
	private static int screenWidth = 1346 , screenHeight = 770 ,
			tileWidth = 32 , tileHeight = 32 , width = screenWidth / tileWidth ,
			height = screenHeight / tileHeight , currCol = width / 2 ,
			currRow = height / 2 , speed = 150 , length , moves , eff;

	private Direction currDir = null;

	private boolean lost = false;

	private Timer timer;

	private ArrayList< int[] > bodyPos = new ArrayList< int[] >();

	private static Block[][] blocks = new Block[ height ][ width ];

	public Game()
	{
		setFocusable( true );
		requestFocus();
		setUndecorated( true );
		setSize( screenWidth , screenHeight );
		empty();
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible( true );
		setLocationRelativeTo( null );
		addKeyListener( new GameKeyListener() );

		bodyPos.add( new int[]
		{ currCol , currRow } );

		bodyPos.add( new int[]
		{ currCol , currRow + 1 } );

		shift();
		spawnNewApple();

		timer = new Timer( speed , this );
		timer.start();
	}

	public void gameOver( Graphics g )
	{
		for ( int animation = 0; animation < getHeight(); animation += 1 )
		{
			g.fillRect( 0 , 0 , getWidth() , animation );
			try
			{
				Thread.sleep( 5 );
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
		try
		{
			BufferedReader br = new BufferedReader(
					new FileReader( "ScoreBoard.txt" ) );

			String line = br.readLine();

			if ( line != null && !line.isEmpty() )
			{
				String[] leader = line.split( " " );

				if ( length > Integer.parseInt( leader[0] ) )
				{
					String name = JOptionPane.showInputDialog( "Name = " );

					if ( name != null && !name.isEmpty() )
					{
						BufferedWriter bw = new BufferedWriter(
								new FileWriter( "Scoreboard.txt" ) );

						bw.write( length + " " + name );

						bw.close();
					}
				}
				br.close();
			}
			else
			{
				String name = JOptionPane.showInputDialog( "Name = " );

				if ( name != null && !name.isEmpty() )
				{
					BufferedWriter bw = new BufferedWriter(
							new FileWriter( "Scoreboard.txt" ) );

					bw.write( length + " " + name );

					bw.close();
				}
			}
		}
		catch ( FileNotFoundException e1 )
		{
			e1.printStackTrace();
		}
		catch ( IOException e1 )
		{
			e1.printStackTrace();
		}

		System.exit( 1 );
	}

	public static void empty()
	{
		for ( int row = 0; row < height; row++ )
			for ( int col = 0; col < width; col++ )
				if ( blocks[row][col] != Block.APPLE
						&& blocks[row][col] != Block.EMPTY )
					blocks[row][col] = Block.EMPTY;
	}

	public void shift()
	{
		ArrayList< int[] > blockUpdate = new ArrayList< int[] >();
		blockUpdate.add( new int[]
		{ bodyPos.get( 0 )[0] , bodyPos.get( 0 )[1] } );
		blockUpdate.add( new int[]
		{ bodyPos.get( 1 )[0] , bodyPos.get( 1 )[1] } );
		blockUpdate.add( new int[]
		{ bodyPos.get( bodyPos.size() - 1 )[0] ,
				bodyPos.get( bodyPos.size() - 1 )[1] } );

		if ( bodyPos.size() > length )

		{
			int lastCol = bodyPos.get( bodyPos.size() - 1 )[0] ,
					lastRow = bodyPos.get( bodyPos.size() - 1 )[1];

			blocks[lastRow][lastCol] = Block.EMPTY;

			blockUpdate.add( new int[]
			{ bodyPos.get( bodyPos.size() - 2 )[0] ,
					bodyPos.get( bodyPos.size() - 2 )[1] } );

			bodyPos.remove( bodyPos.size() - 1 );
		}

		blocks[bodyPos.get( bodyPos.size() - 1 )[1]][bodyPos
				.get( bodyPos.size() - 1 )[0]] = Block.TAIL;
		blocks[bodyPos.get( 0 )[1]][bodyPos.get( 0 )[0]] = Block.HEAD;

		for ( int i = 1; i < bodyPos.size() - 1; i++ )
			blocks[bodyPos.get( i )[1]][bodyPos.get( i )[0]] = Block.BODY;

		for ( int i = 0; i < blockUpdate.size(); i++ )
			repaint( blockUpdate.get( i )[0] * tileWidth ,
					blockUpdate.get( i )[1] * tileHeight ,
					( blockUpdate.get( i )[0] + 1 ) * tileWidth ,
					( blockUpdate.get( i )[1] + 1 ) * tileHeight );

		repaint( getWidth() / 2 - tileWidth * 3 , 0 ,
				getWidth() / 2 + tileWidth * 3 , tileHeight );
	}

	public void spawnNewApple()
	{
		Random random = new Random();

		int ranCol = random.nextInt( width ) ,
				ranRow = random.nextInt( height );

		if ( length < width * height - 1 )
			while ( blocks[ranRow][ranCol] != Block.EMPTY )
			{
				ranCol = random.nextInt( width );
				ranRow = random.nextInt( height );
			}

		blocks[ranRow][ranCol] = Block.APPLE;

		repaint( ranCol * tileWidth , ranRow * tileHeight ,
				( ranCol + 1 ) * tileWidth , ( ranRow + 1 ) * tileHeight );
		length++;
	}

	class GameKeyListener implements KeyListener
	{
		@Override
		public void keyPressed( KeyEvent e )
		{
			for ( int i = 0; i < Direction.values().length; i++ )
				if ( e.getKeyCode() == Direction.values()[i].getKey() )
					if ( currDir == null )
						currDir = Direction.values()[i];
					else if ( Direction.values()[i] != currDir.getOpposite() )
						currDir = Direction.values()[i];
		}

		@Override
		public void keyReleased( KeyEvent e )
		{

		}

		@Override
		public void keyTyped( KeyEvent e )
		{

		}

	}

	public void paint( Graphics g )
	{
		g.drawRect( 0 , 0 , getWidth() , getHeight() );

		for ( int row = 0; row < height; row++ )
		{
			for ( int col = 0; col < width; col++ )
			{
				g.setColor( blocks[row][col].getColor() );
				g.fillRect( col * tileWidth , row * tileHeight , tileWidth ,
						tileHeight );

				g.setColor( Color.black );
				g.drawLine( col * tileWidth , row * tileHeight ,
						( col + 1 ) * tileWidth , row * tileHeight );
				g.drawLine( col * tileWidth , row * tileHeight ,
						col * tileWidth , ( row + 1 ) * tileHeight );

			}
		}
		g.setColor( Color.red );
		g.drawString(
				"Length : " + length + " / " + ( width * height ) + " Avg M/L: "
						+ eff + " Time: " + ( (double) moves * speed / 1000 ) ,
				getWidth() / 2 - 3 * tileWidth , getHeight() / 16 );
		g.setColor( Color.black );
		g.drawLine( getWidth() - 1 , 0 , getWidth() - 1 , getHeight() - 1 );
		g.drawLine( 0 , getHeight() - 1 , getWidth() - 1 , getHeight() - 1 );
	}

	@Override
	public void actionPerformed( ActionEvent arg0 )
	{
		if ( currDir != null )
		{
			if ( currRow + currDir.getyShift() >= height )
				currRow = -1;
			else if ( currRow + currDir.getyShift() < 0 )
				currRow = height;
			else if ( currCol + currDir.getxShift() >= width )
				currCol = -1;
			else if ( currCol + currDir.getxShift() < 0 )
				currCol = width;

			if ( currRow + currDir.getyShift() < height
					&& currRow + currDir.getyShift() >= 0
					&& currCol + currDir.getxShift() < width
					&& currCol + currDir.getxShift() >= 0 )
			{

				if ( blocks[currRow + currDir.getyShift()][currCol
						+ currDir.getxShift()] == Block.APPLE )
					spawnNewApple();
				else if ( blocks[currRow + currDir.getyShift()][currCol
						+ currDir.getxShift()] != Block.EMPTY )
					lost = true;

				currRow += currDir.getyShift();
				currCol += currDir.getxShift();
				bodyPos.add( 0 , new int[]
				{ currCol , currRow } );
				shift();
				moves++;
				eff = moves / length;
			}
			else
				lost = true;

			if ( lost )
			{
				if ( length == width * height )
					System.out.println( "You won!" );
				else
				{
					timer.stop();
					gameOver( getGraphics() );
				}
			}
		}
	}
}
