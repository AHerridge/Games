package jrAlex.core;

public enum ObjectType
{
	WALL(false), CRATE(true), PLAYER(true, true);

	public boolean moveable, dirImages;

	private ObjectType(boolean moveable)
	{
		this.moveable = moveable;
		this.dirImages = false;
	}

	private ObjectType(boolean moveable, boolean dirImages)
	{
		this(moveable);
		this.dirImages = dirImages;
	}

	public static ObjectType getTypeOf(int i)
	{
		if (i < ObjectType.values().length && i >= 0)
			return ObjectType.values()[i];
		return null;
	}
	
	public static int getIndexOf(ObjectType type)
	{
		for(int i = 0; i < ObjectType.values().length; i++)
			if(ObjectType.values()[i] == type)
				return i;
		return -1;
	}
}
