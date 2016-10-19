import java.awt.event.KeyEvent;

public enum Direction
{
	UP( 0 , -1 , KeyEvent.VK_UP ) , DOWN( 0 , 1 , KeyEvent.VK_DOWN ) ,
	LEFT( -1 , 0 , KeyEvent.VK_LEFT ) , RIGHT( 1 , 0 , KeyEvent.VK_RIGHT );

	private int xShift , yShift , key;

	Direction( int xShift , int yShift , int key )
	{
		this.xShift = xShift;
		this.yShift = yShift;
		this.key = key;
	}

	public int getxShift()
	{
		return xShift;
	}

	public int getyShift()
	{
		return yShift;
	}

	public int getKey()
	{
		return key;
	}

	public Direction getOpposite()
	{
		if ( name().matches( "UP" ) )
			return DOWN;
		else if ( name().matches( "DOWN" ) )
			return UP;
		else if ( name().matches( "LEFT" ) )
			return RIGHT;
		else
			return LEFT;
	}

}
