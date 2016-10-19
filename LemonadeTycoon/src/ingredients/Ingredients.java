package ingredients;

import java.awt.image.BufferedImage;

import images.ImageLoader;
import shops.IngredientShop;

public enum Ingredients
{
	LEMON( .70 , -2 , 0 , .5 ) , SUGAR( .25 , 5 , 0 , .25 ) , ICE( .20 , 0 , -1 , .5 ) , WATER( .125 , 0 , 0 , 1 );

	private double			price , sweetness , coldness , servings;
	private BufferedImage	image	= ImageLoader.load( name() );

	Ingredients( double price , double sweetness , double coldness , double servings )
	{
		this.setPrice( price );
		this.sweetness = sweetness;
		this.coldness = coldness;
		this.servings = servings;
		IngredientShop.addIngredient( this );
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice( double price )
	{
		this.price = price;
	}

	public double getSweetness()
	{
		return sweetness;
	}

	public double getColdness()
	{
		return coldness;
	}

	public double getServings()
	{
		return servings;
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public void setImage( BufferedImage image )
	{
		this.image = image;
	}
}
