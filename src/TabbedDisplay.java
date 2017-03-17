import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by PJ on 3/16/2017.
 */
public class TabbedDisplay extends JTabbedPane{

//    public TabbedDisplay(){
//        RCMFunction rcmFunction1 = new RCMFunction("Library","0001", 500);
//        RCM rcm1 = new RCM(rcmFunction1);
//        RCMFunction rcmFunction2 = new RCMFunction("Park", "0002",500);
//        RCM rcm2 = new RCM(rcmFunction2);
//        ArrayList<RCMFunction> rcms = new ArrayList<RCMFunction>();
//        rcms.add(rcmFunction1);
//        rcms.add(rcmFunction2);
//        RMOSFunction rmosFunction = new RMOSFunction(rcms);
//        RMOS rmos = new RMOS(rmosFunction);
//        this.add("RCM1",rcm1);
//        this.add("RCM2",rcm2);
//        this.add("RMOS",rmos);
//        setVisible(true);
//        repaint();
//        revalidate();
//    }

    public static void main(String[] args){
       JTabbedPane tabbedDisplay = new TabbedDisplay();
        RCMFunction rcmFunction1 = new RCMFunction("Library","0001", 500);
        RCM rcm1 = new RCM(rcmFunction1);
        RCMFunction rcmFunction2 = new RCMFunction("Park", "0002",500);
        RCM rcm2 = new RCM(rcmFunction2);
        ArrayList<RCMFunction> rcms = new ArrayList<RCMFunction>();
        rcms.add(rcmFunction1);
        rcms.add(rcmFunction2);
        RMOSFunction rmosFunction = new RMOSFunction(rcms);
        RMOS rmos = new RMOS(rmosFunction);
        tabbedDisplay.add("RCM1",rcm1);
        tabbedDisplay.add("RCM2",rcm2);
        tabbedDisplay.add("RMOS",rmos);
//        setVisible(true);
//        repaint();
//        revalidate();

    }
}
