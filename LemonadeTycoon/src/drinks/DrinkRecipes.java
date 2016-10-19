package drinks;

import ingredients.Ingredients;

public enum DrinkRecipes
{
	LEMONADE( new Ingredients[]
	{
			Ingredients.LEMON , Ingredients.WATER
	} ) , ICEWATER( new Ingredients[]
	{
			Ingredients.WATER , Ingredients.ICE
	} ) , WATER( new Ingredients[]
	{
			Ingredients.WATER
	} ) , LEMONJUICE( new Ingredients[]
	{
			Ingredients.LEMON
	} );

	private Ingredients[] requiredIngredients;

	DrinkRecipes( Ingredients[] requiredIngredients )
	{
		this.requiredIngredients = requiredIngredients;
	}

	public Ingredients[] getRequiredIngredients()
	{
		return requiredIngredients;
	}

	public void setRequiredIngredients( Ingredients[] requiredIngredients )
	{
		this.requiredIngredients = requiredIngredients;
	}
}
