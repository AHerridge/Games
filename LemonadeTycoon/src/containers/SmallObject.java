package containers;

import blocks.Block;

public interface SmallObject extends Object
{
	public static final int size = ( int ) ( Block.size * .25 );
}
