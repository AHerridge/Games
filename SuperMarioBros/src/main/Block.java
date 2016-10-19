package main;

public enum Block
{
	Empty
	{
		public boolean isSolid()
		{
			return false;
		}
	} ,
	Brick , MysBox
	{
		public void collide( Entity e , int collCol , int collRow , Level level )
		{
			level.getBlocks()[ collRow ][ collCol ] = Brick;
			level.getEntities().add( new Coin( collCol * level.getTileS() , ( collRow - 1 ) * level.getTileS() ) );
		}
	};

	private SpriteSheet ss;

	Block()
	{
		ss = new SpriteSheet( name() );
		ss.addNew( "idle" , 0 , 1 );
		ss.setCurrAnim( ss.getAnim().get( "idle" ) );
	}

	public void collide( Entity e , int collCol , int collRow , Level level )
	{
		if ( e.isGravitied() && isSolid() )
			e.setVy( 0 );
	}

	public boolean isSolid()
	{
		return true;
	}

	public SpriteSheet getSS()
	{
		return ss;
	}

	public void setSS( SpriteSheet ss )
	{
		this.ss = ss;
	}

}
