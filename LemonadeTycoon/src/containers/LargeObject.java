package containers;

import blocks.Block;

public interface LargeObject extends Object
{
	public static final int size = ( int ) ( Block.size * .75 );
}
