package RCM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class RCM
 * Created by PJ on 3/8/2017.
 */
public class RCM extends JFrame{
    private int maxWeight;
    private int currentWeight;
    private int maxMoney;
    private int currentMoney;
    private String place;
    private String ID;

    private JPanel entryPane, outputPane;
    private JButton recycleButton;
    private JLabel transactionMoney;
    public RCM(){
        super("RCM");

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        /* ENTRY PANEL */
        entryPane = new JPanel();
        entryPane.setLayout(new GridLayout(3, 2));
        entryPane.setPreferredSize(new Dimension(400, 500));
        entryPane.setBackground(Color.BLUE);
        container.add(entryPane, BorderLayout.WEST);

        //Main button code for entry panel
        recycleButton = new JButton("Recycle");
        recycleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });

        //Object buttons
        JButton b1 = new JButton("Glass Bottle");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        entryPane.add(b1);
        JButton b2 = new JButton("Aluminum Can");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        entryPane.add(b2);
        JButton b3 = new JButton("Paper");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        entryPane.add(b3);

        /* OUTPUT PANEL */
        outputPane = new JPanel();
        outputPane.setPreferredSize(new Dimension(400, 500));
        outputPane.setBackground(Color.GREEN);
        container.add(outputPane, BorderLayout.EAST);

        //Labels

        setLocationRelativeTo(null);
        setVisible(true);
    }
    //////////////// GETTERS AND SETTERS //////////////////////
    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    ////////////////////// OPERATION METHODS //////////////////////
    public void empty(){
        currentWeight = 0;
    }

    public void refillMoney(){

    }
    ////////////////////////// MAIN ////////////////////////////
    public static void main(String[] args){
        new RCM();
    }
}