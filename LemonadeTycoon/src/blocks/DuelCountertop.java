package blocks;

import java.awt.Rectangle;

import containers.LargeSlot;
import containers.Slot;
import containers.SmallSlot;

public class DuelCountertop extends Block
{
	public DuelCountertop()
	{
		super( 15 , new Slot[]
		{
				new LargeSlot( new Rectangle( 0 , ( int ) ( Block.size * .25 ) , LargeSlot.size , LargeSlot.size ) ) ,
				new SmallSlot( new Rectangle( ( int ) ( Block.size * .75 ) , 0 , SmallSlot.size , SmallSlot.size ) ) ,
				new SmallSlot( new Rectangle( ( int ) ( Block.size * .75 ) , ( int ) ( Block.size * .75 ) ,
						SmallSlot.size , SmallSlot.size ) )
		} );
	}
}
