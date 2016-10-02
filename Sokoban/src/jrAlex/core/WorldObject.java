package jrAlex.core;

import java.awt.Graphics2D;

public class WorldObject
{
	protected ObjectType	type;
	protected Direction		dir;

	public WorldObject(ObjectType type)
	{
		super();
		this.type = type;
		this.dir = Direction.NORTH;
	}

	public void render(Graphics2D g, int col, int row, int scale)
	{
		g.drawImage(Images.getImage((type.dirImages) ? type.name() + "_" + dir.name() : type.name()), col * scale,
				row * scale, scale, scale, null);
	}

	public boolean isMoveable()
	{
		return type.moveable;
	}

	public void changeDir(Direction dir)
	{
		if (this.dir != dir)
			this.dir = dir;
	}
}
