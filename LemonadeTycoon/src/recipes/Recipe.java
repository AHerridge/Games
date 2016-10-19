package recipes;

import java.util.ArrayList;

import drinks.Drink;
import ingredients.Ingredients;

public class Recipe
{
	private ArrayList< Ingredients >	ingredients;
	private Drink			outcome;

	public Recipe( Drink outcome )
	{
		this.outcome = outcome;
		ingredients = outcome.getIngredients();
	}

	public Drink getOutcome()
	{
		return outcome;
	}

	public ArrayList< Ingredients > getIngredients()
	{
		return ingredients;
	}
}
