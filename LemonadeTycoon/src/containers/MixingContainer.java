package containers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import drinks.Drink;
import drinks.DrinkRecipes;
import images.ImageLoader;
import ingredients.Ingredients;
import shops.ContainerShop;

public abstract class MixingContainer implements LargeObject
{
	private int							capacity , speed , price;
	private ArrayList< Ingredients >	ingredients	= new ArrayList< Ingredients >();
	private boolean						full;
	private BufferedImage				image		= ImageLoader.load( getClass().getSimpleName() );
	private Drink						drink;

	protected MixingContainer( int capacity , int speed , int price )
	{
		this.capacity = capacity;
		this.speed = speed;
		this.setPrice( price );
		ContainerShop.addContainer( this );
	}

	public void mix( int elapsed )
	{
		if ( !full && ingredients.size() > 0 && elapsed >= speed )
		{
			for ( DrinkRecipes recipe : DrinkRecipes.values() )
				if ( ingredients.containsAll( Arrays.asList( recipe.getRequiredIngredients() ) ) )
				{
					drink = new Drink( recipe , ingredients );
					empty();
				}
		}
	}

	public Drink takeDrink()
	{
		full = false;
		Drink drink = this.drink;
		this.drink = null;
		return drink;
	}

	public Drink getDrink()
	{
		return drink;
	}

	public boolean hasDrink()
	{
		return drink != null;
	}

	public void paint( Graphics g , int x , int y )
	{
		g.drawImage( image , x , y , LargeObject.size , LargeObject.size , null );
		for ( Ingredients i : ingredients )
			g.drawImage( i.getImage() , x , y , LargeObject.size , LargeObject.size , null );
		if ( hasDrink() )
			drink.paint( g , x + LargeObject.size / 3 , y + LargeObject.size / 3 );
	}

	public void empty()
	{
		ingredients = new ArrayList< Ingredients >();
	}

	public synchronized boolean addIngredient( Ingredients i )
	{
		if ( i != null && !full )
		{
			ingredients.add( i );
			return true;
		}
		if ( full )
			System.err.println( "Container is full!" );
		return false;
	}

	public synchronized void removeIngredient( Ingredients i )
	{
		ingredients.remove( i );
	}

	public boolean isFull()
	{
		return ingredients.size() + 1 > capacity || drink != null;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public int getSpeed()
	{
		return speed;
	}

	public ArrayList< Ingredients > getIngredients()
	{
		return ingredients;
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public void setDrink( Drink drink )
	{
		this.drink = drink;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice( int price )
	{
		this.price = price;
	}
}
