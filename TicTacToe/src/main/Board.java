package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings( "serial" )
class Board extends JPanel
{

	private Slot[] slots = new Slot[]
	{ new Slot() , new Slot() , new Slot() , new Slot() , new Slot() ,
			new Slot() , new Slot() , new Slot() , new Slot() };

	private boolean turn = false , over = false;

	private String p1Name , p2Name , winner;

	private int animation = 0 , lastMove = 0 , textSize = 150 , timer , xWins ,
			oWins;

	Board( String _p1Name , String _p2Name )
	{

		p1Name = _p1Name;
		p2Name = _p2Name;

		setFocusable( true );

		setLayout( new GridLayout( 3 , 3 ) );

		for ( int i = 0; i < slots.length; i++ )
		{
			add( slots[i] );
			slots[i].setFont( new Font( "Arial" , Font.PLAIN , textSize ) );
			slots[i].setHorizontalAlignment( SwingConstants.CENTER );
			slots[i].setBackground( Color.white );
			slots[i].setOpaque( true );

			final int iCopy = i;

			slots[i].addMouseListener( new MouseListener()
			{

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
				public void mousePressed( MouseEvent arg0 )
				{
					if ( !over )
						if ( !slots[iCopy].used )
						{
							if ( turn )
							{
								slots[iCopy].setText( "X" );
								slots[iCopy].setBackground( Color.LIGHT_GRAY );
							}
							else
							{
								slots[iCopy].setText( "O" );
								slots[iCopy].setBackground( Color.CYAN );
							}
							slots[iCopy].setOpaque( true );
							slots[iCopy].used = true;
							slots[lastMove].setForeground( Color.white );
							lastMove = iCopy;
							turn = !turn;
							check();
						}
				}

				@Override
				public void mouseReleased( MouseEvent arg0 )
				{

				}

			} );
		}

		addKeyListener( new KeyListener()
		{
			@Override
			public void keyPressed( KeyEvent e )
			{
				if ( over )
					if ( e.getKeyChar() == 'n' )
						timer = 60 * 5;
					else if ( e.getKeyChar() == 'm' )
					{
						System.out.println( "\nScores are as follows :" );
						System.out.println( "\t" + p1Name + " has won : "
								+ oWins + " round(s)!" );
						System.out.println( "\t" + p2Name + " has won : "
								+ xWins + " round(s)!" );
					}
			}

			@Override
			public void keyReleased( KeyEvent e )
			{

			}

			@Override
			public void keyTyped( KeyEvent e )
			{

			}

		} );

		setVisible( true );
	}

	public void paint( Graphics g )
	{
		super.paint( g );
		slots[lastMove].setForeground( Color.red );
		g.drawRect( 0 , 0 , getWidth() - 1 , getHeight() - 1 );
		for ( int i = 1; i < 3; i++ )
		{
			g.drawLine( 0 , getHeight() / 3 * i , getWidth() ,
					getHeight() / 3 * i );
			g.drawLine( getWidth() / 3 * i , 0 , getWidth() / 3 * i ,
					getHeight() );
		}

		// Play game over animation
		if ( over )
		{
			grabFocus();

			g.setColor( Color.black );
			g.fillRect( 0 , 0 , getWidth() , animation );

			if ( animation < getHeight() )
				animation += 6;
			else
			{
				g.setFont( new Font( "Arial" , Font.PLAIN , textSize / 5 ) );
				if ( !winner.matches( "The game is a CAT! " ) )
				{
					Color[] colors = new Color[]
					{ Color.red , Color.cyan , Color.yellow , Color.LIGHT_GRAY ,
							Color.magenta , Color.ORANGE , Color.pink ,
							Color.white };
					Random random = new Random();
					g.setColor( colors[random.nextInt( colors.length )] );
					g.drawLine( random.nextInt( getWidth() ) ,
							random.nextInt( getHeight() ) ,
							random.nextInt( getWidth() ) ,
							random.nextInt( getHeight() ) );
				}
				else
					g.setColor( Color.white );

				g.drawString( winner ,
						getWidth() / 2 - ( winner.length() * textSize / 25 ) ,
						getHeight() / 2 );

				g.drawString( "Press n to skip or m to print out scores." ,
						getWidth() / 2 - ( 42 * textSize / 25 ) ,
						textSize / 3 );
				timer++;

				if ( timer >= 60 * 5 )
					reset();
			}
		}
	}

	private void check()
	{
		int[][] winComb = new int[][]
		{
				{ 0 , 1 , 2 } ,
				{ 3 , 4 , 5 } ,
				{ 6 , 7 , 8 } ,
				{ 0 , 3 , 6 } ,
				{ 1 , 4 , 7 } ,
				{ 2 , 5 , 8 } ,
				{ 0 , 4 , 8 } ,
				{ 2 , 4 , 6 } };

		// Check CAT
		boolean foundEmpty = false;
		for ( int i = 0; i < 9 && !foundEmpty; i++ )
			if ( slots[i].getText().isEmpty() )
				foundEmpty = true;

		if ( !foundEmpty )
			winner = ( "The game is a CAT! " );

		// Check for win
		for ( int[] comb : winComb )
			if ( slots[comb[0]].getText().equals( "O" )
					&& slots[comb[1]].getText().equals( "O" )
					&& slots[comb[2]].getText().equals( "O" ) )
			{
				winner = ( p1Name + " is the winner!" );
				oWins++;
			}
			else if ( slots[comb[0]].getText().equals( "X" )
					&& slots[comb[1]].getText().equals( "X" )
					&& slots[comb[2]].getText().equals( "X" ) )
			{
				winner = ( p2Name + " is the winner!" );
				xWins++;
			}

		if ( winner != null && !winner.isEmpty() )
			over = true;
	}

	private void reset()
	{
		for ( Slot slot : slots )
		{
			slot.setText( "" );
			slot.used = false;
			slot.setBackground( Color.white );
		}

		lastMove = 0;
		timer = 0;
		animation = 0;
		over = false;
		winner = "";
	}

	private class Slot extends JLabel
	{
		private boolean used = false;
	}

}
