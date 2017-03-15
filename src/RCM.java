
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class RCM
 * Created by PJ on 3/8/2017.
 */
public class RCM extends JFrame{
    //Counters and counter labels
    private int glassBottleCounter = 0;
    private int aluminumCanCounter = 0;

    private JLabel glassBottleLabel;
    private JLabel aluminumCanLabel;

    //GUI Components
    private JPanel entryPane;
    private JPanel outputPane;
    private JPanel recyclePanel;
    private JPanel moneyPanel;
    private JPanel dataPanel;
    private JPanel optionPanel;
    private JButton recycleButton;
    private JButton b1, b2, b3, b4, b5 ,b6; //object option buttons
    private JLabel moneyLabel;
    public RCM(){
        super("RCM");

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // screen = Toolkit.getDefaultToolkit().getScreenSize();
        //setSize(screen);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        //Initializing button status for single recycling
        recycleButton = new JButton();
        recycleButton.setEnabled(false);

        //Initializing labels for multiple recycling
        glassBottleLabel = new JLabel("0");
        aluminumCanLabel = new JLabel("0");

        /* OPTION PANEL */
        optionPanel = new JPanel();
        optionPanel.setBorder(BorderFactory.createTitledBorder("Select Your Recycling Option"));
        container.add(optionPanel, BorderLayout.NORTH);
        JRadioButton single = new JRadioButton("Recycle One Item");
        single.setSelected(true);
        single.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recycleButton.setEnabled(false);

            }
        });

        JRadioButton multiple = new JRadioButton("Recycle Multiple Items");
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
        entryPane.setBorder(BorderFactory.createTitledBorder("Select Your Item(s)"));
        container.add(entryPane, BorderLayout.WEST);

        //Object buttons in Entry
        //TODO: make buttons function, need to increment variables per recyclable type
        b1 = new JButton("Glass Bottle");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(single.isSelected()){
                    //recycle single item
                }
                else{
                    glassBottleCounter++;
                    glassBottleLabel.setText(String.valueOf(glassBottleCounter));
                }
            }
        });
        entryPane.add(b1);

        b2 = new JButton("Aluminum Can");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(single.isSelected()){

                }
                else{
                    aluminumCanCounter++;
                    aluminumCanLabel.setText(String.valueOf(aluminumCanCounter));
                }
            }
        });
        entryPane.add(b2);

        b3 = new JButton("");
        b3.setVisible(false);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        entryPane.add(b3);

        b4 = new JButton("Cardboard");
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        entryPane.add(b4);

        b5 = new JButton("Plastic Bottle");
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        entryPane.add(b5);

        b6 = new JButton("Wood");
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
        container.add(outputPane, BorderLayout.EAST);

        //Money subpanel
        moneyPanel = new JPanel();
        moneyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        moneyPanel.setBackground(Color.decode("#00BCD4"));
        moneyLabel = new JLabel("$0.00");
        Font moneyFont = new Font("Money", Font.BOLD, 30);
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
        dataPanel.add(glassBottleLabel);
        dataPanel.add(new JLabel("Aluminum Cans:"));
        dataPanel.add(aluminumCanLabel);
        dataPanel.add(new JLabel("Paper:"));
        dataPanel.add(new JLabel("0"));
        dataPanel.add(new JLabel("Cardboard:"));
        dataPanel.add(new JLabel("0"));
        dataPanel.add(new JLabel("Plastic Bottles:"));
        dataPanel.add(new JLabel("0"));
        dataPanel.add(new JLabel("Wood:"));
        dataPanel.add(new JLabel("0"));


        outputPane.add(dataPanel, BorderLayout.CENTER);

        //Recycle subpanel
        recyclePanel = new JPanel();
        recyclePanel.setBackground(Color.decode("#00BCD4"));
        recyclePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outputPane.add(recyclePanel, BorderLayout.SOUTH);

        //Main button code for recycle subpanel
        recycleButton.setText("RECYCLE");
        recycleButton.setPreferredSize(new Dimension(150, 50));
        recycleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });
        recyclePanel.add(recycleButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    ////////////////////// OPERATION METHODS //////////////////////

    ////////////////////////// MAIN ////////////////////////////
    public static void main(String[] args){
        new RCM();
    }
}