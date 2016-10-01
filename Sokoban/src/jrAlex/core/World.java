package jrAlex.core;

import java.util.ArrayList;

import com.google.gson.Gson;

public class World
{
	protected ArrayList<WorldObject>	objects;
	protected int						scale;

	public World(int scale)
	{
		this.scale = scale;
		this.objects = new ArrayList<>();
	}

	public World(int scale, ArrayList<WorldObject> objects)
	{
		this(scale);
		this.objects = objects;
	}

	public void addObject(WorldObject wo)
	{
		objects.add(wo);
	}

	public ArrayList<WorldObject> getObjects()
	{
		return objects;
	}

	public static World load(String fileName)
	{
		World world = new World(64);
		Gson gson = new Gson();
		
		return world;
	}
}
