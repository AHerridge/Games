package recipes;

import java.util.ArrayList;

import ingredients.Ingredients;

public class RecipeList
{
	private static ArrayList< Recipe > recipes = new ArrayList< Recipe >();

	public static void print()
	{
		for ( Recipe recipe : recipes )
		{
			System.out.println( recipe.getOutcome().getName() );
			System.out.println( "\tIngredients : " );
			for ( Ingredients i : recipe.getIngredients() )
				System.out.println( "\t\t" + i.name() );
		}
	}

	public synchronized static void addRecipe( Recipe r )
	{
		if ( recipes.indexOf( r ) == -1 )
			recipes.add( r );
	}

	public synchronized static void removeRecipe( Recipe r )
	{
		recipes.remove( r );
	}

	public ArrayList< Recipe > getRecipes()
	{
		return recipes;
	}
}
