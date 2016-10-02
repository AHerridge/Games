package jrAlex.core;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class WorldObject
{
	protected Rectangle	bounds;
	protected World		world;
	protected String	imageName;

	public WorldObject(int col, int row, int width, int height, World world)
	{
		super();
		bounds = new Rectangle(col, row, width, height);
		this.world = world;
	}

	public void update(int delta)
	{

	}

	public void render(Graphics2D g, int scale)
	{
		g.drawImage(Images.getImage(imageName), bounds.x * scale, bounds.y * scale, bounds.width * scale,
				bounds.height * scale, null);
	}

	public Rectangle getBounds()
	{
		return bounds;
	}
}
