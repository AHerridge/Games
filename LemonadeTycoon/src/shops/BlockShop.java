package shops;

import java.util.HashMap;
import java.util.Map;

import blocks.Block;
import player.Player;

public class BlockShop
{
	private static Map< Block , Integer > catalog = new HashMap< Block , Integer >();

	public static void addBlock( Block block )
	{
		catalog.putIfAbsent( block , ( int ) ( block.getPrice() + block.getPrice() * .25 ) );
	}

	public static void removeBlock( Block block )
	{
		catalog.remove( block );
	}

	public static int getPrice( Block block )
	{
		return catalog.get( block );
	}

	public static void setPrice( Block block , int price )
	{
		catalog.put( block , price );
	}

	public static Block buy( Block block , Player player )
	{
		if ( player.setMoney( player.getMoney() - catalog.get( block ) ) )
			return block;
		return null;
	}
}
