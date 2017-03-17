import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by PJ on 3/16/2017.
 */
public class TabbedDisplay extends JPanel{

    public TabbedDisplay(RCMFunction rcmFunction1, RCMFunction rcmFunction2){
        super(new GridLayout(1,1));
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("RCM1",new RCM(rcmFunction1));
        tabbedPane.addTab("RCM2",new RCM(rcmFunction2));
        add(tabbedPane);
//        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//        setVisible(true);
//        repaint();
//        revalidate();
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RCMFunction rcmFunction1 = new RCMFunction("Library","0001", 500);
        RCMFunction rcmFunction2 = new RCMFunction("Park", "0002",500);

        //Add content to the window.
        ArrayList<RCMFunction> rcms = new ArrayList<RCMFunction>();
        rcms.add(rcmFunction1);
        rcms.add(rcmFunction2);
        RMOSFunction rmosFunction = new RMOSFunction(rcms);
        RMOS rmos = new RMOS(rmosFunction);
        rmos.pack();
        rmos.revalidate();

        frame.add(new TabbedDisplay(rcmFunction1,rcmFunction2));
        frame.repaint();
        frame.revalidate();
        frame.pack();
        frame.setVisible(true);
    }
}

