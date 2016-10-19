package images;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageLoader
{
	private static Map< String , BufferedImage > images = new HashMap< String , BufferedImage >();

	public synchronized static BufferedImage load( String name )
	{
		if ( images.containsKey( name ) )
			return images.get( name );
		try
		{
			images.put( name , ImageIO.read( new File( name + ".png" ) ) );
			return images.get( name );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		return null;
	}
}
