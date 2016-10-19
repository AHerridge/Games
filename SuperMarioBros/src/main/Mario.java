package main;

public class Mario extends Entity
{
	public Mario( String name , int x , int y )
	{
		super( name , x , y );
		this.getSS().addNew( "walkRight" , 0 , 1 );
		this.getSS().addNew( "walkLeft" , 3 , 4 );
		this.getSS().addNew( "jumpRight" , 1 , 2 );
		this.getSS().addNew( "jumpLeft" , 2 , 3 );
	}

	public void move( Level level )
	{
		super.move( level );
		if ( this.getVy() < 0 )
		{
			if ( this.getVx() >= 0 )
				this.getSS().setCurrAnim( this.getSS().getAnim().get( "jumpRight" ) );
			else if ( this.getVx() < 0 )
				this.getSS().setCurrAnim( this.getSS().getAnim().get( "jumpLeft" ) );
		}
		else if ( this.getVy() >= 0 )
		{
			if ( this.getVx() > 0 )
				this.getSS().setCurrAnim( this.getSS().getAnim().get( "walkRight" ) );
			else if ( this.getVx() < 0 )
				this.getSS().setCurrAnim( this.getSS().getAnim().get( "walkLeft" ) );
		}
	}
}
