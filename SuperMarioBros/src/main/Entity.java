package main;

import java.awt.Rectangle;

public class Entity
{
	private String		name;

	private int			x , y , w = 1 , h = 1 , speed = 8;

	private double		vx , vy;

	private SpriteSheet	ss;

	private boolean		gravitied	= true , solid = true;

	public Entity( String name , int x , int y )
	{
		this.setName( name );
		this.x = x;
		this.y = y;
		ss = new SpriteSheet( name );
		ss.addNew( "idle" , 0 , 1 );
		ss.setCurrAnim( ss.getAnim().get( "idle" ) );
	}

	public void move( Level level )
	{
		if ( gravitied && !level.getBlocks()[ y / level.getTileS() + 1 ][ x / level.getTileS() ].isSolid() )
			vy += .25;

		Rectangle newX = new Rectangle( ( int ) ( x + vx * speed ) , y , level.getTileS() * w , level.getTileS() * h );

		Rectangle newY = new Rectangle( x , ( int ) ( y + vy * speed ) , level.getTileS() * w , level.getTileS() * h );

		boolean collX = false , collY = false;

		if ( !level.getBounds().contains( newX ) )
			collX = true;
		if ( !level.getBounds().contains( newY ) )
			collY = true;

		if ( solid && ( vx != 0 || vy != 0 ) )
		{
			for ( int row = ( newY.y / level.getTileS() - h * 2 ); row < ( newY.y / level.getTileS() + h * 2 ); row++ )
				for ( int col = ( newX.x / level.getTileS() - w * 2 ); col < ( newX.x / level.getTileS()
						+ w * 2 ); col++ )
					if ( row >= 0 && row < level.getMapH() && col >= 0 && col < level.getMapW() )
					{
						if ( level.getBlocks()[ row ][ col ].isSolid() && new Rectangle( col * level.getTileS() ,
								row * level.getTileS() , level.getTileS() , level.getTileS() ).intersects( newX ) )
						{
							collX = true;
							level.getBlocks()[ row ][ col ].collide( this , col , row , level );
						}
						if ( level.getBlocks()[ row ][ col ].isSolid() && new Rectangle( col * level.getTileS() ,
								row * level.getTileS() , level.getTileS() , level.getTileS() ).intersects( newY ) )
						{
							collY = true;
							level.getBlocks()[ row ][ col ].collide( this , col , row , level );
						}
					}
		}

		for ( int i = 0; i < level.getEntities().size(); i++ )
		{
			if ( level.getEntities().get( i ).isSolid() && new Rectangle( level.getEntities().get( i ).getX() ,
					level.getEntities().get( i ).getY() , level.getEntities().get( i ).getW() * level.getTileS() ,
					level.getEntities().get( i ).getH() * level.getTileS() ).intersects( newX ) )
			{
				collX = true;
				level.getEntities().get( i ).collide( this , level );
			}
			else if ( level.getEntities().get( i ).isSolid() && new Rectangle( level.getEntities().get( i ).getX() ,
					level.getEntities().get( i ).getY() , level.getEntities().get( i ).getW() * level.getTileS() ,
					level.getEntities().get( i ).getH() * level.getTileS() ).intersects( newY ) )
			{
				collY = true;
				level.getEntities().get( i ).collide( this , level );
			}
		}

		if ( !collX )
			x = newX.x;
		if ( !collY )
			y = newY.y;
	}

	public void collide( Entity e , Level level )
	{

	}

	public int getX()
	{
		return x;
	}

	public void setX( int x )
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY( int y )
	{
		this.y = y;
	}

	public double getVx()
	{
		return vx;
	}

	public void setVx( double vx )
	{
		this.vx = vx;
	}

	public double getVy()
	{
		return vy;
	}

	public void setVy( double vy )
	{
		this.vy = vy;
	}

	public int getW()
	{
		return w;
	}

	public void setW( int w )
	{
		this.w = w;
	}

	public int getH()
	{
		return h;
	}

	public void setH( int h )
	{
		this.h = h;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed( int speed )
	{
		this.speed = speed;
	}

	public SpriteSheet getSS()
	{
		return ss;
	}

	public void setSS( SpriteSheet ss )
	{
		this.ss = ss;
	}

	public boolean isGravitied()
	{
		return gravitied;
	}

	public void setGravitied( boolean gravitied )
	{
		this.gravitied = gravitied;
	}

	public boolean isSolid()
	{
		return solid;
	}

	public void setSolid( boolean solid )
	{
		this.solid = solid;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}
}
