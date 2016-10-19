package containers;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Slot
{
	private Rectangle	position;
	private Object		object;

	public abstract boolean placeObject( Object object );

	public Slot( Rectangle positition )
	{
		this.position = positition;
	}

	public boolean hasObject()
	{
		return object != null;
	}

	public void paint( Graphics g , int x , int y )
	{
		if ( object != null )
			object.paint( g , x + position.x , y + position.y );
	}

	public Object getObject()
	{
		return object;
	}

	public Object takeObject()
	{
		Object object = this.object;
		this.object = null;
		return object;
	}

	public Rectangle getPosition()
	{
		return position;
	}

	public void setPosition( Rectangle position )
	{
		this.position = position;
	}

	public void setObject( Object object )
	{
		this.object = object;
	}
}
