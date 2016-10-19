package blocks;

import java.awt.Rectangle;

import containers.LargeSlot;
import containers.Slot;
import containers.SmallSlot;

public class BasicCountertop extends Block
{
	public BasicCountertop()
	{
		super( 10 , new Slot[]
		{
				new LargeSlot( new Rectangle( 0 , ( int ) ( Block.size * .25 ) , LargeSlot.size , LargeSlot.size ) ) ,
				new SmallSlot( new Rectangle( ( int ) ( Block.size * .75 ) , 0 , SmallSlot.size , SmallSlot.size ) )
		} );
	}
}
