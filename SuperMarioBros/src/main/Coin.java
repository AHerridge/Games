package main;

public class Coin extends Entity
{
	public Coin( int x , int y )
	{
		super( "Coin" , x , y );
	}

	public void collide( Entity e , Level level )
	{
		level.getEntities().remove( level.getEntities().indexOf( this ) );
	}
}
