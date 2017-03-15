import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import sun.plugin.javascript.JSClassLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dante on 3/8/2017.
 */
public class RMOS extends JFrame {

    // initial breakup of 4 spaces for the RMOS display
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    //RCM Control menu items
    private JPanel RCMControl;
    private JComboBox RCMList;
    private JButton RCMChangeButton;
    private JLabel chooseRCM;
    private JLabel RCMLabel;
    private JLabel RCMStatus;
    private JLabel RCMInformation;
    private JButton RCMTurnOn;
    private JButton RCMTurnOff;
    private JLabel RCMWeight;
    private JLabel RCMItems;
    private JLabel RCMCapacity;
    private JButton RCMEmpty;
    private JLabel RCMMoney;
    private JLabel RCMLocation;
    private JLabel RCMChangeCapacity; //TODO: Make sure that when we change this we check that it is a number and that the weight is not greater
    private JTextField RCMNewCapacity;
    private JButton RCMChangeCapacityButton;
    private JLabel RCMCapacityChangeError;
    private JButton RCMChangeMoneyButton;

    //modify menu items
    private JPanel modifyMenu;


    private JPanel statisticMenu;
    private JComboBox pickRCM; //default should be showing for all RCMs
    private JTextArea statsList;
    private JButton updateStatsList;
    private JLabel statsListLabel;


    private JPanel quickMenu;
    private JLabel addRCM;
    private JLabel insertID;
    private JTextField newRCMName;
    private JLabel insertCapacity;
    private JTextField newRCMCapacity;
    private JButton addRCMButton;
    private JLabel editItemLabel;
    private JComboBox editItemBox;
    private JTextField editPriceBox;
    private JButton editPriceSubmit;
    private JLabel addNewItem;
    private JComboBox addNewItemName;
    private JLabel addNewItemPriceLabel;
    private JTextField addNewItemPrice;
    private JButton addNewItemButton;
    private JComboBox deleteItemBox;
    private JButton deleteItemSubmit;
    private JLabel deleteItemLabel;
    private JLabel editItemPriceLabel;


    public RMOS() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {
            e.printStackTrace();
        }

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        RCMControl = new JPanel();
        modifyMenu = new JPanel();
        statisticMenu = new JPanel();
        quickMenu = new JPanel();


        RCMList = new JComboBox();
        RCMLabel = new JLabel("RCM");
        RCMWeight = new JLabel("Weight");
        RCMMoney = new JLabel("Money");
        RCMItems = new JLabel("Number of Items");
        RCMCapacity = new JLabel("Capacity");
        RCMLocation = new JLabel("Location");
        RCMChangeCapacity = new JLabel("Change Capacity or Money");
        RCMNewCapacity = new JTextField("Enter New Value");
        RCMCapacityChangeError = new JLabel("No errors at this point");
        RCMChangeButton = new JButton("Change RCM");
        RCMChangeCapacityButton = new JButton("Change Capacity");
        RCMChangeMoneyButton = new JButton("Change Money");
        RCMEmpty = new JButton("Empty");
        chooseRCM = new JLabel("Choose RCM");
        RCMStatus = new JLabel("Status");
        RCMInformation = new JLabel("RCM Information");
        RCMStatus = new JLabel("status");
        RCMTurnOn = new JButton("Turn On");
        RCMTurnOff = new JButton("Turn Off");

        addRCM = new JLabel("Add RCM");
        insertID = new JLabel("Insert New RCM ID");
        newRCMName = new JTextField();
        insertCapacity = new JLabel("Insert New RCM Capacity");
        newRCMCapacity = new JTextField();
        addRCMButton = new JButton("Add RCM");
        editItemLabel = new JLabel("Edit Item");
        editItemBox = new JComboBox();
        editItemLabel = new JLabel("Edited Item Price");
        editItemPriceLabel = new JLabel("Edit Price");
        editPriceBox = new JTextField();
        editPriceSubmit = new JButton("Submit");
        addNewItem = new JLabel("Add an Item");
        addNewItemName = new JComboBox();
        addNewItemPriceLabel = new JLabel("Item Price Per Pound");
        addNewItemPrice = new JTextField();
        addNewItemButton = new JButton("Add New Item");
        deleteItemLabel = new JLabel("Delete an Item");
        deleteItemBox = new JComboBox();
        deleteItemSubmit = new JButton("Delete Item");

        statsListLabel = new JLabel("Statistics");
        pickRCM = new JComboBox();
        statsList = new JTextArea();
        updateStatsList = new JButton("Stats");

        Container container = getContentPane();
        container.setLayout(new GridLayout(1,4,20,0));
        container.setBackground(Color.decode("#B388FF"));

        panel1.setLayout(new GridBagLayout());
        panel1.setBackground(Color.decode("#B388FF"));
        panel2.setLayout(new GridBagLayout());
        panel2.setBackground(Color.decode("#B388FF"));
        panel3.setLayout(new GridBagLayout());
        panel3.setOpaque(true);
        panel3.setBackground(Color.decode("#B388FF"));
        panel4.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        RCMControl.setBackground(Color.decode("#00BCD4"));
        modifyMenu.setBackground(Color.decode("#00BCD4"));
        statisticMenu.setBackground(Color.decode("#00BCD4"));
        quickMenu.setBackground(Color.decode("#B39DDB"));

        // Set up the size of panel 1 with constraints
        constraints.weightx = 0.15;
        constraints.weighty = 0.15;
        constraints.gridy = 0;
        constraints.gridx = 0;
        panel1.add(new JPanel(), constraints);

        constraints.ipadx = 300;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.7;
        constraints.weightx = 0.7;
        panel1.add(RCMControl,constraints);

        constraints.ipadx = 0;
        constraints.weightx = 0.15;
        constraints.weighty = 0.15;
        constraints.gridy = 2;
        constraints.gridx = 0;
        panel1.add(new JPanel(), constraints);

        // setting up panel 2 with constrains
        constraints.weightx = 0.15;
        constraints.weighty = 0.15;
        constraints.gridy = 0;
        constraints.gridx = 0;
        panel2.add(new JPanel(), constraints);

        constraints.ipadx = 300;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.7;
        constraints.weightx = 0.7;
        panel2.add(modifyMenu,constraints);

        constraints.ipadx = 0;
        constraints.weightx = 0.15;
        constraints.weighty = 0.15;
        constraints.gridy = 2;
        constraints.gridx = 0;
        panel2.add(new JPanel(), constraints);

        // setting up panel3 with constraints
        constraints.weightx = 0.15;
        constraints.weighty = 0.15;
        constraints.gridy = 0;
        constraints.gridx = 0;
        panel3.add(new JPanel(), constraints);

        constraints.ipadx = 300;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.7;
        constraints.weightx = 0.7;
        panel3.add(statisticMenu,constraints);

        constraints.weightx = 0.15;
        constraints.weighty = 0.15;
        constraints.gridy = 2;
        constraints.gridx = 0;
        panel3.add(new JPanel(), constraints);

        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(quickMenu);

        //---------------------------------------------------------------------
        RCMControl.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0,50,0,50);
        RCMControl.add(RCMList,constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridy = 1;
        RCMControl.add(RCMChangeButton,constraints);

        constraints.insets = new Insets(0,200,0,0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        RCMLabel.setFont(new Font("RCM",Font.BOLD,30));
        RCMControl.add(RCMLabel,constraints);

        constraints.insets = new Insets(0,50,0,50);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        RCMControl.add(RCMTurnOn, constraints);

        constraints.insets = new Insets(0,25,0,25);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 3;
        RCMControl.add(RCMTurnOff, constraints);

        RCMInformation.setFont(new Font("RCM Information",Font.BOLD,20));
        constraints.gridx = 0;
        constraints.gridy = 4;
        RCMControl.add(RCMInformation,constraints);

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 5;
        RCMControl.add(RCMWeight,constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        RCMControl.add(RCMEmpty,constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        RCMControl.add(RCMCapacity,constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        RCMControl.add(RCMItems,constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        RCMControl.add(RCMMoney,constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        RCMControl.add(RCMLocation,constraints);

        RCMChangeCapacity.setFont(new Font("Change Capacity",Font.BOLD,15));
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.CENTER;
        RCMControl.add(RCMChangeCapacity,constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        RCMControl.add(RCMNewCapacity,constraints);

        constraints.gridx = 1;
        constraints.gridy = 10;
        RCMControl.add(RCMCapacityChangeError,constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0,50,0,50);
        constraints.gridx = 0;
        constraints.gridy = 11;
        RCMControl.add(RCMChangeCapacityButton,constraints);

        constraints.insets = new Insets(0,25,0,25);
        constraints.gridx = 1;
        constraints.gridy = 11;
        RCMControl.add(RCMChangeMoneyButton,constraints);

        //---------------------------------------------------------

//        constraints = new GridBagConstraints();

        quickMenu.setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        addRCM.setFont(new Font("Add RCM",Font.BOLD,20));
        quickMenu.add(addRCM,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        quickMenu.add(insertID,constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        quickMenu.add(newRCMName,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        quickMenu.add(insertCapacity,constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        quickMenu.add(newRCMCapacity,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        quickMenu.add(addRCMButton,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        editItemLabel.setFont(new Font("Edit Item",Font.BOLD,20));
        quickMenu.add(editItemLabel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        quickMenu.add(editItemBox,constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        quickMenu.add(editItemPriceLabel,constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        quickMenu.add(editPriceBox,constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        quickMenu.add(editPriceSubmit,constraints);

        addNewItem.setFont(new Font("Add New Item",Font.BOLD,20));
        constraints.gridx = 0;
        constraints.gridy = 8;
        quickMenu.add(addNewItem,constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        quickMenu.add(addNewItemName,constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        quickMenu.add(addNewItemPriceLabel,constraints);

        constraints.gridx = 1;
        constraints.gridy = 10;
        quickMenu.add(addNewItemPrice,constraints);

        constraints.gridx = 0;
        constraints.gridy = 11;
        quickMenu.add(addNewItemButton,constraints);

        deleteItemLabel.setFont(new Font("Delete An Item",Font.BOLD,20));
        constraints.gridx = 0;
        constraints.gridy = 12;
        quickMenu.add(deleteItemLabel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 13;
        quickMenu.add(deleteItemBox,constraints);

        constraints.gridx = 0;
        constraints.gridy = 14;
        quickMenu.add(deleteItemSubmit,constraints);

        //---------------------------------------------------------
        statisticMenu.setLayout(new GridBagLayout());

        constraints.insets = new Insets(0,170,0,0);
        statsListLabel.setFont(new Font("Statistics",Font.BOLD,30));
        constraints.gridx = 0;
        constraints.gridy = 0;
        statisticMenu.add(statsListLabel,constraints);

        constraints.insets = new Insets(0,25,0,25);
        constraints.gridx = 0;
        constraints.gridy = 1;
        statisticMenu.add(pickRCM,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 2;
        statisticMenu.add(statsList,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        statisticMenu.add(updateStatsList,constraints);

        //--------------------------------------------------------

        Comparable key = new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

        modifyMenu.setLayout(new BorderLayout());
        DefaultPieDataset data = new DefaultPieDataset();
        data.insertValue(0,key,1.2);

        PiePlot pie = new PiePlot(data);
        ChartPanel chart = new ChartPanel(new JFreeChart(pie));
        constraints.gridx = 0;
        constraints.gridy = 0;
        modifyMenu.add(chart);




        //TODO: Implement these action listeners

        RCMChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        RCMTurnOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        RCMTurnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        RCMEmpty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        RCMChangeMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        RCMChangeCapacityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        updateStatsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        addRCMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        addNewItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        editPriceSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteItemSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });




        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateRCMComboBars() {

    }

    public void updateItemComboBars() {

    }

    public void getStatistics(String RCMId) {

    }

    public void updateRCMList() {

    }


    public static void main(String[] args) {
        RMOS rmos = new RMOS();
    }
}
