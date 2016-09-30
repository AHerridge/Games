package jrAlex.core.math.vector;

/**
 * Created on 9/13/2016.
 */

public class Vector
{
	protected double[] values;

	public Vector(double[] values)
	{
		this.values = values;
	}

	public Vector(int size)
	{
		values = new double[size];
	}

	public void add(Vector b)
	{
		if (isSameSize(b))
		{
			Vector answer = new Vector(new double[values.length]);
			for (int i = 0; i < values.length; i++)
				answer.values[i] = this.values[i] + b.values[i];
			values = answer.values;
		}
	}

	public void sub(Vector b)
	{
		if (isSameSize(b))
		{
			Vector answer = new Vector(new double[values.length]);
			for (int i = 0; i < values.length; i++)
				answer.values[i] = this.values[i] - b.values[i];
			values = answer.values;
		}
	}

	public void mult(Vector b)
	{
		if (isSameSize(b))
		{
			Vector answer = new Vector(new double[values.length]);
			for (int i = 0; i < values.length; i++)
				answer.values[i] = this.values[i] * b.values[i];
			values = answer.values;
		}
	}

	public void mult(double scalar)
	{
		Vector answer = new Vector(new double[values.length]);
		for (int i = 0; i < values.length; i++)
			answer.values[i] = this.values[i] * scalar;
		values = answer.values;
	}

	public double getValue(int index)
	{
		return values[index];
	}

	public double setValue(int index, double value)
	{
		return values[index] = value;
	}

	protected boolean isSameSize(Vector b)
	{
		if (values.length == b.values.length)
			return true;
		System.err.println("Vectors are NOT the same size");
		return false;
	}

	public String toString()
	{
		String desc = "";
		for (double value : values)
			desc += value + ", ";
		return desc;
	}
}
