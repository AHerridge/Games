package containers;

import java.awt.Rectangle;

import blocks.Block;

public class SmallSlot extends Slot
{
	public static final int size = ( int ) ( Block.size * SmallObject.size );

	public SmallSlot( Rectangle positition )
	{
		super( positition );
	}

	public boolean placeObject( Object object )
	{
		if ( this.getObject() == null && object instanceof SmallObject )
		{
			this.setObject( object );
			return true;
		}
		return false;
	}
}
