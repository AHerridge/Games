import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import blocks.Block;
import blocks.DuelCountertop;
import containers.BasicContainer;
import containers.LargeSlot;
import containers.MixingContainer;
import containers.Slot;
import containers.SmallSlot;
import drinks.Drink;
import ingredients.Ingredients;
import player.Player;
import recipes.RecipeList;
import shops.BlockShop;
import shops.ContainerShop;
import shops.IngredientShop;

@SuppressWarnings( "serial" )
public class GamePanel extends JPanel implements MouseListener , KeyListener
{
	private Block[][]	blocks				= new Block[6][8];
	private int			tileSize			= 64;
	private Ingredients	selectedIngredient	= null;
	private Block		selectedBlock;
	private Slot		selectedSlot;
	private Player		player				= new Player();

	public GamePanel()
	{
		this.setFocusable( true );
		this.addMouseListener( this );
		this.addKeyListener( this );
	}

	public void paint( Graphics g )
	{
		g.setColor( Color.gray );
		g.fillRect( 0 , 0 , blocks[ 0 ].length * tileSize + 2 , 2 * tileSize );
		for ( int row = 0; row < blocks.length; row++ )
			for ( int col = 0; col < blocks[ 0 ].length; col++ )
			{
				g.drawRect( col * tileSize , row * tileSize + 2 * tileSize , tileSize , tileSize );
				if ( blocks[ row ][ col ] != null )
					blocks[ row ][ col ].paint( g , col * tileSize , row * tileSize + 2 * tileSize );
			}
		g.setColor( Color.white );
		g.drawString( "$" + player.getMoney() , getWidth() / 2 , 50 );
	}

	@Override
	public void mouseClicked( MouseEvent arg0 )
	{

	}

	@Override
	public void mouseEntered( MouseEvent arg0 )
	{

	}

	@Override
	public void mouseExited( MouseEvent arg0 )
	{

	}

	@Override
	public void mousePressed( MouseEvent e )
	{
		if ( new Rectangle( 0 , tileSize * 2 , tileSize * blocks[ 0 ].length , tileSize * blocks.length )
				.contains( e.getPoint() ) )
		{
			selectedBlock = blocks[ ( e.getPoint().y - 2 * tileSize ) / tileSize ][ e.getPoint().x / tileSize ];
			if ( selectedBlock == null )
				blocks[ ( e.getPoint().y - 2 * tileSize ) / tileSize ][ e.getPoint().x / tileSize ] = BlockShop
						.buy( new DuelCountertop() , player );
			else if ( selectedBlock.getSlot( e.getPoint().x , e.getPoint().y ) != null )
			{
				selectedSlot = selectedBlock.getSlot( e.getPoint().x , e.getPoint().y );
				if ( !selectedSlot.hasObject() )
				{
					if ( selectedSlot instanceof LargeSlot )
						selectedBlock.getSlot( e.getPoint().x , e.getPoint().y )
								.placeObject( ContainerShop.buy( new BasicContainer() , player ) );
				}
				else if ( ( selectedSlot.getObject() instanceof MixingContainer ) )
				{
					if ( ! ( ( MixingContainer ) selectedSlot.getObject() ).isFull() )
					{
						if ( selectedIngredient != null )
							( ( MixingContainer ) selectedSlot.getObject() )
									.addIngredient( IngredientShop.buy( selectedIngredient , player ) );
					}
					else if ( selectedBlock.findEmptySmallSlot() != null )
						if ( ( ( MixingContainer ) selectedSlot.getObject() ).isFull()
								&& ( ( MixingContainer ) selectedSlot.getObject() ).hasDrink() )
							selectedBlock.findEmptySmallSlot()
									.setObject( ( ( ( MixingContainer ) selectedSlot.getObject() ).takeDrink() ) );
				}
				else if ( selectedSlot instanceof SmallSlot && selectedSlot.getObject() instanceof Drink )
				{
					( ( Drink ) selectedSlot.takeObject() ).sell( player );
				}
			}
			else
				System.out.println( "Empty Space" );
		}
	}

	@Override
	public void mouseReleased( MouseEvent arg0 )
	{

	}

	@Override
	public void keyPressed( KeyEvent e )
	{
		if ( e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_3
				|| e.getKeyCode() == KeyEvent.VK_4 )
			selectedIngredient = Ingredients.values()[ Integer.parseInt( e.getKeyChar() + "" ) - 1 ];
		else if ( e.getKeyCode() == KeyEvent.VK_0 )
			selectedIngredient = null;
		else if ( e.getKeyCode() == KeyEvent.VK_M )
		{
			if ( selectedSlot.getObject() instanceof MixingContainer )
				( ( MixingContainer ) selectedSlot.getObject() ).mix( 100 );
		}
		else if ( e.getKeyCode() == KeyEvent.VK_P )
			RecipeList.print();
	}

	@Override
	public void keyReleased( KeyEvent arg0 )
	{

	}

	@Override
	public void keyTyped( KeyEvent arg0 )
	{

	}
}
