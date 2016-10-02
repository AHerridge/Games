package jrAlex.core;

public enum Direction
{
	NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

	public int colShift, rowShift;

	private Direction(int colShift, int rowShift)
	{
		this.colShift = colShift;
		this.rowShift = rowShift;
	}
}
