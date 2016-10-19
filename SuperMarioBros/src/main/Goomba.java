package main;

public class Goomba extends Entity
{
	public Goomba( String name , int x , int y )
	{
		super( name , x , y );
	}

	public void collide( Entity e , Level level )
	{
		level.getEntities().remove( level.getEntities().indexOf( this ) );
	}
}
