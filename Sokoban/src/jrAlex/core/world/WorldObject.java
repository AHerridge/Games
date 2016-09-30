package jrAlex.core.world;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import jrAlex.core.math.vector.Vector2D;

public abstract class WorldObject
{
	protected final World		world;
	protected final Vector2D	pos;
	protected final int			width, height;

	public WorldObject(World world, int x, int y, int w, int h)
	{
		this.world = world;
		pos = new Vector2D(x, y);
		width = w;
		height = h;
	}

	public abstract void update(int delta);

	public abstract void render(Graphics2D g2d);

	public Rectangle getCollider()
	{
		return new Rectangle((int) pos.getValue(Vector2D.X), (int) pos.getValue(Vector2D.Y), width, height);
	}

	public String toString()
	{
		return "Pos: " + pos.toString();
	}
}
