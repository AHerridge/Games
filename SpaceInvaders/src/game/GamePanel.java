package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings( "serial" )
public class GamePanel extends JPanel implements ActionListener , KeyListener
{
	private static final int ScreenWidth = 1344 , ScreenHeight = 768 ,
			RowCount = 6 , ColCount = 11 , TileSize = 64;

	private int shipX , shipY = TileSize , shipVX = 32 , delta , bossX ,
			playerX = 0 , updateSpeed = 68 , bossVX = 2 * shipVX , playerVX ,
			score , bulletVY = -3 , killCount , lives = 3 , lastRow = RowCount ,
			lastCol = ColCount , playerBullets;

	private boolean gameOver = false;

	private String gameOverText = "";

	private Random random = new Random();

	private Ship[][] enemies = new Ship[ RowCount ][ ColCount ];

	private Ship boss = Ship.Boss , player = Ship.Player;

	private Timer timer = new Timer( 16 , this );

	private BufferedImage bg;

	private ArrayList< Bullet > bullets = new ArrayList< Bullet >();

	public GamePanel()
	{
		this.setSize( new Dimension( ScreenWidth , ScreenHeight ) );
		this.addKeyListener( this );
		this.setFocusable( true );
		this.grabFocus();
		initEnemies();
		try
		{
			bg = ImageIO.read( new File( "BackGround.png" ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		timer.start();
	}

	public void paint( Graphics g )
	{
		g.clearRect( 0 , 0 , getWidth() , getHeight() );
		g.setFont( new Font( "Ariel" , Font.PLAIN , 20 ) );

		if ( !gameOver )
		{
			// Draw background
			g.setColor( Color.red );
			g.drawImage( bg , 0 , 0 , getWidth() , getHeight() , null );
			// g.fillRect( 0 , 0 , getWidth() , getHeight() );

			// Draw enemy block
			for ( int row = 0; row < RowCount; row++ )
				for ( int col = 0; col < ColCount; col++ )
					if ( enemies[row][col] != null )
						g.drawImage( enemies[row][col].getImage() ,
								col * TileSize + shipX ,
								row * TileSize + shipY , TileSize , TileSize ,
								null );

			// Draw boss
			if ( boss != null )
				g.drawImage( boss.getImage() , bossX , 0 , TileSize * 2 ,
						TileSize , null );

			// Draw player
			g.drawImage( player.getImage() , playerX , getHeight() - TileSize ,
					TileSize * 2 , TileSize , null );

			// Draw bullets
			// g.setColor( Color.white );
			for ( Bullet bullet : bullets )
				g.fillRect( bullet.x , bullet.y , 10 , 30 );

			g.drawString( "Score : " + score , getWidth() / 2 - 60 , TileSize );
			g.drawString( "Lives : " + lives , getWidth() / 2 + 60 , TileSize );
		}
		else
		{
			g.setFont( new Font( "Ariel" , Font.PLAIN , 70 ) );
			g.fillRect( 0 , 0 , getWidth() , getHeight() );
			g.setColor( Color.white );
			g.drawString( gameOverText ,
					getWidth() / 2 - gameOverText.length() * 15 ,
					getHeight() / 2 );
		}
	}

	private void initEnemies()
	{
		for ( int row = 0; row < RowCount; row++ )
			for ( int col = 0; col < ColCount; col++ )
				if ( row <= 1 )
					enemies[row][col] = Ship.Alien1;
				else if ( row <= 3 )
					enemies[row][col] = Ship.Alien2;
				else if ( row <= 5 )
					enemies[row][col] = Ship.Alien3;
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		delta++;

		if ( delta % updateSpeed == 0 )
		{
			if ( shipX + ColCount * TileSize > getWidth() || shipX < 0 )
			{
				shipVX = -shipVX;
				shipY += TileSize;
			}
			shipX += shipVX;

			if ( boss != null )
			{
				if ( bossX > getWidth() * 2 || bossX < -getWidth() )
					bossVX = -bossVX;
				bossX += bossVX;
			}
		}
		else if ( delta % 20 * updateSpeed == 0 && boss == null )
		{
			boss = Ship.Boss;
			bossX = -getWidth();
			bossVX = Math.abs( shipVX * 2 );
		}
		else if ( delta % 200 * updateSpeed == 0 )
			bullets.add( new EnemyBullet() );

		// Move bullets
		for ( int bullet = 0; bullet < bullets.size(); bullet++ )
			bullets.get( bullet ).checkCollisions();

		// Move player
		if ( playerX + playerVX >= 0
				&& playerX + playerVX + 2 * TileSize <= getWidth() )
			playerX += playerVX;

		// TODO fix constraints on border
		boolean foundRow = false;
		for ( int row = RowCount - 1; row >= 0 && !foundRow; row-- )
		{
			boolean foundCol = false;
			for ( int col = ColCount - 1; col >= 0 && !foundCol; col-- )
				if ( enemies[row][col] != null )
				{
					foundCol = true;
					foundRow = true;
				}
			if ( !foundCol )
				lastRow--;
		}

		// TODO add win animation
		if ( killCount == ColCount * RowCount + 1 )
		{
			gameOverText = "You Win!";
			gameOver = true;
			timer.stop();
		}
		else if ( shipY >= getHeight() - lastRow * TileSize - TileSize * 2
				|| lives == 0 )
		{
			gameOverText = "You Lose!";
			gameOver = true;
			timer.stop();
		}

		repaint();
	}

	@Override
	public void keyPressed( KeyEvent e )
	{
		if ( e.getKeyCode() == KeyEvent.VK_A )
			playerVX = -4;
		else if ( e.getKeyCode() == KeyEvent.VK_D )
			playerVX = 4;
		else if ( e.getKeyCode() == KeyEvent.VK_SPACE && playerBullets < 5 )
		{
			bullets.add( new PlayerBullet() );
			playerBullets++;
		}
	}

	@Override
	public void keyReleased( KeyEvent e )
	{
		if ( e.getKeyCode() == KeyEvent.VK_A
				|| e.getKeyCode() == KeyEvent.VK_D )
			playerVX = 0;
	}

	@Override
	public void keyTyped( KeyEvent e )
	{

	}

	abstract class Bullet
	{
		protected int x , y;

		public abstract void checkCollisions();
	}

	class PlayerBullet extends Bullet
	{
		public PlayerBullet()
		{
			x = playerX + TileSize - 3;
			y = getHeight() - TileSize;
		}

		public void checkCollisions()
		{
			y += bulletVY;
			int checkCol = ( x - shipX ) / TileSize ,
					checkRow = ( y - shipY ) / TileSize;
			Ship collide = null;
			if ( y < 0 )
			{
				bullets.remove( bullets.indexOf( this ) );
				playerBullets--;
			}
			else if ( checkRow >= 0 && checkRow < RowCount && checkCol >= 0
					&& checkCol < ColCount
					&& enemies[checkRow][checkCol] != null )
				collide = enemies[checkRow][checkCol];
			else if ( x >= bossX && x < bossX + 2 * TileSize && y < TileSize )
				collide = boss;

			if ( collide != null )
			{
				if ( collide == boss )
					boss = null;
				else if ( collide == enemies[checkRow][checkCol] )
					enemies[checkRow][checkCol] = null;

				score += collide.getPoints();
				bullets.remove( bullets.indexOf( this ) );
				killCount++;
				updateSpeed--;
				playerBullets--;
			}
		}
	}

	class EnemyBullet extends Bullet
	{
		public EnemyBullet()
		{
			x = random.nextInt( ColCount ) * TileSize + shipX + TileSize / 2;
			y = random.nextInt( RowCount ) * TileSize + shipY + TileSize / 2;
		}

		public void checkCollisions()
		{
			y -= bulletVY;
			// TODO add hit animation
			if ( y > getHeight() )
				bullets.remove( bullets.indexOf( this ) );
			else if ( x >= playerX && x <= playerX + 2 * TileSize
					&& y >= getHeight() - TileSize )
			{
				lives--;
				bullets.remove( bullets.indexOf( this ) );
			}
		}
	}

}
