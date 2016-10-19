import javax.swing.JFrame;

public class Test
{
	public static void main( String[] args )
	{
		GamePanel panel = new GamePanel();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frm.setSize( ( int ) ( 8.12 * 64 ) , ( int ) ( 8.48 * 64 ) );
		frm.setLocationRelativeTo( null );
		frm.setResizable( false );
		frm.add( panel );
		frm.setVisible( true );

		while ( true )
		{
			frm.repaint();
		}
	}
}
