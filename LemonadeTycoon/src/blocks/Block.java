package blocks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import containers.LargeSlot;
import containers.Slot;
import containers.SmallSlot;
import images.ImageLoader;
import shops.BlockShop;

public class Block
{
	public static final int	size	= 64;
	private int				price;
	private Slot[]			layout;
	private BufferedImage	image	= ImageLoader.load( getClass().getSimpleName() );

	public Block( int price , Slot[] layout )
	{
		this.setPrice( price );
		BlockShop.addBlock( this );
		this.layout = layout;
	}

	public Slot getSlot( int x , int y )
	{
		double divX = ( double ) x / size , divY = ( double ) y / size;

		if ( divX == 0 )
			x -= ( int ) ( ( divX - 1 ) * size );
		else
			x -= ( int ) divX * size;

		if ( divY == 0 )
			y -= ( int ) ( ( divY - 1 ) * size );
		else
			y -= ( int ) divY * size;

		for ( Slot slot : layout )
			if ( slot.getPosition().contains( x , y ) )
				return slot;
		return null;
	}

	public synchronized Slot findEmptyLargeSlot()
	{
		for ( Slot slot : layout )
			if ( slot instanceof LargeSlot && !slot.hasObject() )
				return slot;
		return null;
	}

	public synchronized Slot findEmptySmallSlot()
	{
		for ( Slot slot : layout )
			if ( slot instanceof SmallSlot && !slot.hasObject() )
				return slot;
		return null;
	}

	public void paint( Graphics g , int x , int y )
	{
		g.drawImage( image , x , y , null );
		for ( Slot slot : layout )
			slot.paint( g , x , y );
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice( int price )
	{
		this.price = price;
	}
}
