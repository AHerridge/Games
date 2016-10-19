package shops;

import java.util.HashMap;
import java.util.Map;

import ingredients.Ingredients;
import player.Player;

public class IngredientShop
{
	private static Map< Ingredients , Double > catalog = new HashMap< Ingredients , Double >();

	public static void addIngredient( Ingredients i )
	{
		catalog.putIfAbsent( i , i.getPrice() + i.getPrice() * .25 );
	}

	public static void removeIngredient( Ingredients i )
	{
		catalog.remove( i );
	}

	public static double getPrice( Ingredients i )
	{
		return catalog.get( i );
	}

	public static void setPrice( Ingredients i , double price )
	{
		catalog.put( i , price );
	}

	public static Ingredients buy( Ingredients i , Player player )
	{
		if ( i != null )
			if ( player.setMoney( player.getMoney() - catalog.get( i ) ) )
				return i;
		return null;
	}
}
