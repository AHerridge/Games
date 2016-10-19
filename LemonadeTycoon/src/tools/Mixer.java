package tools;
import containers.MixingContainer;

public abstract class Mixer
{
	protected MixingContainer	container;
	protected double			speedMulti;

	public Mixer( MixingContainer container , double speedMulti )
	{
		this.container = container;
		this.speedMulti = speedMulti;
	}
}
