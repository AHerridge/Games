import java.awt.Color;

public enum Block
{
	EMPTY( Color.white ) , HEAD( Color.green ) , BODY( Color.gray ) ,
	TAIL( Color.red ) , APPLE( Color.magenta );

	private Color color;

	Block( Color color )
	{
		this.color = color;
	}

	public Color getColor()
	{
		return color;
	}

}
