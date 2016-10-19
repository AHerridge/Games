package containers;

import java.awt.Rectangle;

public class LargeSlot extends Slot
{
	public static final int size = LargeObject.size;

	public LargeSlot( Rectangle positition )
	{
		super( positition );
	}

	public boolean placeObject( Object object )
	{
		if ( this.getObject() == null && object instanceof LargeObject )
		{
			this.setObject( object );
			return true;
		}
		return false;
	}
}
