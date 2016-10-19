package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class SpriteSheet
{
	static Map< String , BufferedImage >	sprites	= new HashMap< String , BufferedImage >();

	static File[]							files	= new File( "sprites" ).listFiles();

	static final int						size	= 64;

	BufferedImage							master;

	Map< String , Animation >				anim	= new HashMap< String , Animation >();

	Animation								currAnim;

	public SpriteSheet( String name )
	{
		for ( File file : files )
			try
			{
				sprites.putIfAbsent( file.getName() , ImageIO.read( file ) );
			}
			catch ( IOException e )
			{
				e.printStackTrace();
			}
		
		master = sprites.get( name + ".png" );
	}

	public void addNew( String name , int start , int end )
	{
		anim.put( name , new Animation( start , end ) );
	}

	class Animation
	{
		BufferedImage[]	images;

		int				index;

		public Animation( int start , int end )
		{
			loadImages( start , end );
		}

		public BufferedImage getNext()
		{
			if ( index + 1 < images.length )
				index++;
			else
				index = 0;

			return images[ index ];
		}

		public void loadImages( int start , int end )
		{
			images = new BufferedImage[end - start];
			for ( int i = start; i < end; i++ )
				images[ i - start ] = master.getSubimage( i * size , 0 , size , size );
		}
	}

	public BufferedImage getMaster()
	{
		return master;
	}

	public void setMaster( BufferedImage master )
	{
		this.master = master;
	}

	public Map< String , Animation > getAnim()
	{
		return anim;
	}

	public void setAnim( Map< String , Animation > anim )
	{
		this.anim = anim;
	}

	public Animation getCurrAnim()
	{
		return currAnim;
	}

	public void setCurrAnim( Animation currAnim )
	{
		this.currAnim = currAnim;
	}
}
