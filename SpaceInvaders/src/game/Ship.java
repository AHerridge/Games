package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Ship
{
	Alien1( 30 ) , Alien2( 20 ) , Alien3( 10 ) , Boss( 100 ) , Player( 0 );

	private BufferedImage image;

	private int points;

	Ship( int points )
	{
		this.points = points;
		try
		{
			image = ImageIO.read( new File( name() + ".png" ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public int getPoints()
	{
		return points;
	}

	public BufferedImage getImage()
	{
		return image;
	}
}
