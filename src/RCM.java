
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;

/**
 * Class RCM
 * Created by pjaffurs on 3/8/2017.
 */
public class RCM extends JFrame{
    //Counters and operation variables
    private USMoney tmpMoney;
    private int glassBottleCounter = 0;
    private int aluminumCanCounter = 0;

    //RCM Method driver
    private RCMFunction mRCM;

    //ArrayList to store items to be recycled
    //TODO: use arraylist of recyclables to build UI as used
    protected ArrayList<RecyclableItem> list = new ArrayList<>();

    //GUI Components
    private JPanel headerPanel;
    private JPanel optionPanel;
    private JPanel entryPane;
    private JPanel outputPane;
    private JPanel recyclePanel;
    private JPanel moneyPanel;
    private JPanel dataPanel;

    private JButton recycleButton;
    private JButton b1, b2, b3, b4, b5 ,b6; //object option buttons

    private JLabel title;
    private JLabel moneyLabel;
    private JLabel counterLabel1, counterLabel2, counterLabel3, counterLabel4, counterLabel5, counterLabel6;
    private JLabel dataLabel1, dataLabel2, dataLabel3, dataLabel4, dataLabel5, dataLabel6;


    /////////////////////// CONSTRUCTOR /////////////////////////
    public RCM(String location, String ID, int capacity){
        super("RCM");

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        mRCM = new RCMFunction(location, ID, capacity);
        tmpMoney = new USMoney(0,0);
        Font titleFont = new Font("Title", Font.PLAIN, 20);
        Font entryFont = new Font("Entry", Font.PLAIN, 30);

        //Initializing button status for single recycling
        recycleButton = new JButton();
        recycleButton.setEnabled(false);

        //Initializing labels for multiple recycling
        counterLabel1 = new JLabel("0");
        counterLabel2 = new JLabel("0");
        counterLabel3 = new JLabel("0");
        counterLabel4 = new JLabel("0");
        counterLabel5 = new JLabel("0");
        counterLabel6 = new JLabel("0");

        /* HEADER PANEL */
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel, BorderLayout.NORTH);

        title = new JLabel("RCM " + ID + ", " + location);
        title.setFont(titleFont);
        headerPanel.add(title);
        optionPanel = new JPanel();
        optionPanel.setBorder(BorderFactory.createTitledBorder(null,"Select Your Recycling Option",
                TitledBorder.CENTER, TitledBorder.TOP, titleFont));
        headerPanel.add(optionPanel, BorderLayout.SOUTH);

        JRadioButton single = new JRadioButton("Recycle One Item");
        single.setSelected(true);
        single.setFont(entryFont);
        single.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recycleButton.setEnabled(false);

            }
        });

        JRadioButton multiple = new JRadioButton("Recycle Multiple Items");
        multiple.setFont(entryFont);
        multiple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recycleButton.setEnabled(true);
            }
        });

        //button grouping
        ButtonGroup bg = new ButtonGroup();
        bg.add(single);
        bg.add(multiple);
        optionPanel.add(single);
        optionPanel.add(multiple);

        /* ENTRY PANEL */
        entryPane = new JPanel();
        entryPane.setLayout(new GridLayout(3, 2, 200, 200));
        entryPane.setPreferredSize(new Dimension(958, 1080));
        entryPane.setBackground(Color.decode("#00BCD4"));
        entryPane.setBorder(BorderFactory.createTitledBorder(null,"Select Your Item(s)",
                TitledBorder.CENTER, TitledBorder.TOP, titleFont));

        entryPane.setFont(entryFont);
        container.add(entryPane, BorderLayout.WEST);

        //Object buttons in Entry
        //TODO: make buttons function, need to increment variables per recyclable type
        b1 = new JButton("Glass Bottle");
        b1.setFont(entryFont);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(single.isSelected()){
                    mRCM.recycle();
                }
                else{
                    glassBottleCounter++;
                    counterLabel1.setText(String.valueOf(glassBottleCounter));
                    mRCM.recycle();
                }
            }
        });
        entryPane.add(b1);

        b2 = new JButton("Aluminum Can");
        b2.setFont(entryFont);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(single.isSelected()){

                }
                else{
                    aluminumCanCounter++;
                    counterLabel2.setText(String.valueOf(aluminumCanCounter));
                }
            }
        });
        entryPane.add(b2);

        b3 = new JButton("");
        b3.setFont(entryFont);
        b3.setVisible(false);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        entryPane.add(b3);

        b4 = new JButton("Cardboard");
        b4.setFont(entryFont);
        b4.setVisible(false);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        entryPane.add(b4);

        b5 = new JButton("Plastic Bottle");
        b5.setFont(entryFont);
        b5.setVisible(false);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        entryPane.add(b5);

        b6 = new JButton("Wood");
        b6.setFont(entryFont);
        b6.setVisible(false);
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        entryPane.add(b6);

        /* OUTPUT PANEL */
        outputPane = new JPanel();
        outputPane.setLayout(new BorderLayout());
        outputPane.setPreferredSize(new Dimension(960, 1080));
        outputPane.setBackground(Color.decode("#00BCD4"));
        outputPane.setBorder(BorderFactory.createTitledBorder(null,"Session Information",
                TitledBorder.CENTER, TitledBorder.TOP, titleFont));
        container.add(outputPane, BorderLayout.EAST);

        //Money subpanel
        moneyPanel = new JPanel();
        moneyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        moneyPanel.setBackground(Color.decode("#00BCD4"));
        moneyLabel = new JLabel(tmpMoney.toString());
        Font moneyFont = new Font("Money", Font.BOLD, 45);
        moneyLabel.setFont(moneyFont);
        moneyLabel.setForeground(Color.YELLOW);
        moneyPanel.add(moneyLabel);
        outputPane.add(moneyPanel, BorderLayout.NORTH);

        //Data subpanel
        //TODO: set counters for numerical label fields
        dataPanel = new JPanel();
        dataPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dataPanel.setLayout(new GridLayout(6, 2));
        dataPanel.add(new JLabel("Glass Bottles:"));
        dataPanel.add(counterLabel1);
        dataPanel.add(new JLabel("Aluminum Cans:"));
        dataPanel.add(counterLabel2);
        dataPanel.add(new JLabel("Paper:"));
        dataPanel.add(counterLabel3);
        dataPanel.add(new JLabel("Cardboard:"));
        dataPanel.add(counterLabel4);
        dataPanel.add(new JLabel("Plastic Bottles:"));
        dataPanel.add(counterLabel5);
        dataPanel.add(new JLabel("Wood:"));
        dataPanel.add(counterLabel6);


        outputPane.add(dataPanel, BorderLayout.CENTER);

        //Recycle subpanel
        recyclePanel = new JPanel();
        recyclePanel.setBackground(Color.decode("#00BCD4"));
        recyclePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outputPane.add(recyclePanel, BorderLayout.SOUTH);

        //Main button code for recycle subpanel
        recycleButton.setText("RECYCLE");
        Font recycleFont = new Font("Recycle",Font.BOLD, 40);
        recycleButton.setFont(recycleFont);
        recycleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mRCM.recycle();
            }
        });
        recyclePanel.add(recycleButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    ////////////////////// OPERATION METHODS //////////////////////
    //public void update()
    ////////////////////////// MAIN ////////////////////////////
    public static void main(String[] args){
        new RCM("Library","0",100);
    }
}