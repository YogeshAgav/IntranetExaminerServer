import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PerformanceDetails extends JFrame
{
    static protected JPanel allLabelPane = new JPanel();
    protected JPanel  contentPane = null;
    static protected JScrollPane labelScrollPane = null ;
	
    PerformanceDetails()
    {
       
		super(" The Performance Detail of All Clients ");
        SocketThread.watchingPerformance = 0;
		Dimension aDimension= Toolkit.getDefaultToolkit().getScreenSize();
        
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
            	SocketThread.watchingPerformance = 1 ;
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
        
		setSize(450,450);
        allLabelPane.setBorder( BorderFactory.createLoweredBevelBorder() );
        allLabelPane.setLayout( new GridLayout(0,1) );
        
        // the Scroll Pane Related..........
        labelScrollPane = new JScrollPane(allLabelPane,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        labelScrollPane.setPreferredSize(new Dimension(440,440));
        labelScrollPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5) );
		
        //Lay out the content pane.
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        contentPane.add(labelScrollPane ,BorderLayout.CENTER );
        
        setVisible(true);
    }
}
