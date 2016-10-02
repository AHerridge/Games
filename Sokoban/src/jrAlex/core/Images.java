package jrAlex.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public final class Images
{
	private static final Map<String, BufferedImage> map = new HashMap<>();

	public static BufferedImage getImage(String name)
	{
		if(!map.containsKey(name))
			map.put(name, loadImage(name));
		return map.get(name);
	}

	private static BufferedImage loadImage(String name)
	{
		try
		{
			return ImageIO.read(new File("./res/images/" + name + ".png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
