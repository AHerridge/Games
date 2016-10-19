package player;

public class Player
{
	private double money = 100;

	public double getMoney()
	{
		return money;
	}

	public synchronized boolean setMoney( double money )
	{
		if ( money >= 0 )
		{
			this.money = money;
			System.out.println( money );
			return true;
		}
		System.err.println( "Insufficient Funds" );
		return false;
	}

}
