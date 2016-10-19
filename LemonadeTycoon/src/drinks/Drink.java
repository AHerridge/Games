package drinks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import containers.SmallObject;
import images.ImageLoader;
import ingredients.Ingredients;
import player.Player;
import recipes.Recipe;
import recipes.RecipeList;

public class Drink implements SmallObject
{
	private double						sweetness	= 5 , coldness = 5 , servings , suggestedPrice , price;
	private static double				margin		= 2;
	private BufferedImage				image		= ImageLoader.load( getClass().getSimpleName() );
	private DrinkRecipes				baseRecipe;
	private ArrayList< Ingredients >	ingredients;
	private String						name;

	public Drink( DrinkRecipes recipe , ArrayList< Ingredients > ingredients )
	{
		baseRecipe = recipe;
		this.ingredients = ingredients;
		name = recipe.name();

		for ( Ingredients i : ingredients )
		{
			setSweetness( getSweetness() + i.getSweetness() );
			setColdness( getColdness() + i.getColdness() );
			setServings( getServings() + i.getServings() );
			setSuggestedPrice( getSuggestedPrice() + i.getPrice() );
		}
		setSuggestedPrice( getSuggestedPrice() / servings );
		price = suggestedPrice * margin;

		RecipeList.addRecipe( new Recipe( this ) );
	}

	public void paint( Graphics g , int x , int y )
	{
		g.drawImage( image , x , y , SmallObject.size , SmallObject.size , null );
		for ( Ingredients i : ingredients )
			g.drawImage( i.getImage() , x , y , SmallObject.size , SmallObject.size , null );
	}

	public void sell( Player player )
	{
		player.setMoney( player.getMoney() + price * servings );
		servings = 0;
	}

	public void getInfo()
	{
		System.out.println( name + " : Sweetness " + sweetness + " Coldness " + coldness + " Servings " + servings
				+ " Price Per Unit $" + suggestedPrice );
	}

	public double getSweetness()
	{
		return sweetness;
	}

	public void setSweetness( double sweetness )
	{
		if ( sweetness >= 0 && sweetness <= 10 )
			this.sweetness = sweetness;
	}

	public double getColdness()
	{
		return coldness;
	}

	public void setColdness( double coldness )
	{
		if ( coldness >= 0 && coldness <= 10 )
			this.coldness = coldness;
	}

	public String getName()
	{
		return name;
	}

	public ArrayList< Ingredients > getIngredients()
	{
		return ingredients;
	}

	public double getServings()
	{
		return servings;
	}

	public DrinkRecipes getBaseRecipe()
	{
		return baseRecipe;
	}

	public void setServings( double servings )
	{
		this.servings = servings;
	}

	public double getSuggestedPrice()
	{
		return suggestedPrice;
	}

	public void setSuggestedPrice( double suggestedPrice )
	{
		this.suggestedPrice = suggestedPrice;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice( double price )
	{
		this.price = price;
	}
}
