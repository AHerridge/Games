package shops;

import java.util.HashMap;
import java.util.Map;

import containers.MixingContainer;
import player.Player;

public class ContainerShop
{
	private static Map< MixingContainer , Integer > catalog = new HashMap< MixingContainer , Integer >();

	public static void addContainer( MixingContainer container )
	{
		catalog.putIfAbsent( container , ( int ) ( container.getPrice() + container.getPrice() * .25 ) );
	}

	public static void removeContainer( MixingContainer container )
	{
		catalog.remove( container );
	}

	public static int getPrice( MixingContainer container )
	{
		return catalog.get( container );
	}

	public static void setPrice( MixingContainer container , int price )
	{
		catalog.put( container , price );
	}

	public static MixingContainer buy( MixingContainer container , Player player )
	{
		if ( player.setMoney( player.getMoney() - catalog.get( container ) ) )
			return container;
		return null;
	}
}
