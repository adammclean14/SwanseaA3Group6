public class timeTester{
    public static void main( String args[] ) {
    	JFrame win = new JFrame( "Timer" );
        win.addWindowListener(
            new WindowAdapter() {
                public void windowClosing( WindowEvent e ) {
                    System.exit( 0 );
                }
            }
        );
        timer l = new timer();
        Container content = win.getContentPane();
        content.setLayout( new BorderLayout() );
        content.add( "Center" , l );	
        //JButton b = new JButton();
        //content.add(b,BorderLayout.PAGE_START);
        win.pack();
        win.show();
        l.timerStart();
}
}