import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dante on 3/8/2017.
 */
public class RMOS extends JFrame {

    RMOSFunction mRMOS;

    //4 panels for main screen
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    //RCM Control menu items
    private JPanel RCMPanel;
    private JComboBox<String> RCMList;
//    private JButton RCMChangeButton;
//    private JLabel chooseRCM;
    private JLabel RCMLabel;
//    private JLabel RCMStatus;
    private JLabel RCMInformation;
    private JButton RCMTurnOn; //TODO: Add functionality for on and off
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
    private JPanel GraphPanel;

    //Statistic Menu
    private JPanel StatisticPanel;
//    private JComboBox pickRCM; //default should be showing for all RCMs
    private JTextArea statsList;
    private JButton updateStatsList;
    private JLabel statsListLabel;

    //Control Panel
    private JPanel ControlPanel;
    private JLabel addRCM;
    private JLabel insertID;
    private JTextField newRCMName;
    private JLabel newRCMLocation;
//    private JTextField RCMLocation;
    private JLabel insertCapacity;
    private JTextField newRCMCapacity;
    private JButton addRCMButton;
    private JLabel editItemLabel;
    private JComboBox<String> editItemBox;
    private JTextField editPriceBox;
    private JButton editPriceSubmit;
    private JLabel addNewItem;
    private JComboBox addNewItemName;
    private JLabel addNewItemPriceLabel;
    private JTextField addNewItemPrice;
    private JButton addNewItemButton;
    private JComboBox<String> deleteItemBox;
    private JButton deleteItemSubmit;
    private JLabel deleteItemLabel;
    private JLabel editItemPriceLabel;


    //TODO: Make a login screen
    public RMOS() {
        RMOSUI();
    }

    public void RMOSUI() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {
            e.printStackTrace();
        }

        //All of the
        mRMOS = new RMOSFunction();

        //break the screen into 4 blocked panels
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        //Constructors for content
        RCMPanel = new JPanel();
        GraphPanel = new JPanel();
        StatisticPanel = new JPanel();
        ControlPanel = new JPanel();

        //RCM info constructor
        RCMList = new JComboBox();
        RCMLabel = new JLabel("RCM");
        RCMWeight = new JLabel("Weight");
        RCMMoney = new JLabel("Money");
        RCMItems = new JLabel("Number of Items");
        RCMCapacity = new JLabel("Capacity");
        RCMLocation = new JLabel("Location");
        RCMChangeCapacity = new JLabel("Change Capacity or Money");
        RCMNewCapacity = new JTextField("Enter new value");
        RCMCapacityChangeError = new JLabel("No errors at this point");
//        RCMChangeButton = new JButton("Change RCM");
        RCMChangeCapacityButton = new JButton("Change Capacity");
        RCMChangeMoneyButton = new JButton("Change Money");
        RCMEmpty = new JButton("Empty");
//        chooseRCM = new JLabel("Choose RCM");
//        RCMStatus = new JLabel("Status");
        RCMInformation = new JLabel("RCM Information");
//        RCMStatus = new JLabel("status");
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
//        pickRCM = new JComboBox();
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

        RCMPanel.setBackground(Color.decode("#00BCD4"));
        GraphPanel.setBackground(Color.decode("#00BCD4"));
        StatisticPanel.setBackground(Color.decode("#00BCD4"));
        ControlPanel.setBackground(Color.decode("#B39DDB"));

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
        panel1.add(RCMPanel,constraints);

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
        panel2.add(GraphPanel,constraints);

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
        panel3.add(StatisticPanel,constraints);

        constraints.weightx = 0.15;
        constraints.weighty = 0.15;
        constraints.gridy = 2;
        constraints.gridx = 0;
        panel3.add(new JPanel(), constraints);

        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(ControlPanel);

        //---------------------------------------------------------------------
        RCMPanel.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0,50,0,50);
        RCMPanel.add(RCMList,constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridy = 1;
//        RCMPanel.add(RCMChangeButton,constraints);

        constraints.insets = new Insets(0,200,0,0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        RCMLabel.setFont(new Font("RCM",Font.BOLD,30));
        RCMPanel.add(RCMLabel,constraints);

        constraints.insets = new Insets(0,50,0,50);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        RCMPanel.add(RCMTurnOn, constraints);

        constraints.insets = new Insets(0,25,0,25);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 3;
        RCMPanel.add(RCMTurnOff, constraints);

        RCMInformation.setFont(new Font("RCM Information",Font.BOLD,20));
        constraints.gridx = 0;
        constraints.gridy = 4;
        RCMPanel.add(RCMInformation,constraints);

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 5;
        RCMPanel.add(RCMWeight,constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        RCMPanel.add(RCMEmpty,constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        RCMPanel.add(RCMCapacity,constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        RCMPanel.add(RCMItems,constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        RCMPanel.add(RCMMoney,constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        RCMPanel.add(RCMLocation,constraints);

//        RCMChangeCapacity.setFont(new Font("Change Capacity",Font.BOLD,15));
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.CENTER;
        RCMPanel.add(RCMChangeCapacity,constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        RCMPanel.add(RCMNewCapacity,constraints);

        constraints.gridx = 1;
        constraints.gridy = 10;
        RCMPanel.add(RCMCapacityChangeError,constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0,50,0,50);
        constraints.gridx = 0;
        constraints.gridy = 11;
        RCMPanel.add(RCMChangeCapacityButton,constraints);

        constraints.insets = new Insets(0,25,0,25);
        constraints.gridx = 1;
        constraints.gridy = 11;
        RCMPanel.add(RCMChangeMoneyButton,constraints);

        //---------------------------------------------------------

//        constraints = new GridBagConstraints();

        ControlPanel.setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        addRCM.setFont(new Font("Add RCM",Font.BOLD,20));
        ControlPanel.add(addRCM,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        ControlPanel.add(insertID,constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        ControlPanel.add(newRCMName,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        ControlPanel.add(insertCapacity,constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        ControlPanel.add(newRCMCapacity,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        ControlPanel.add(addRCMButton,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        editItemLabel.setFont(new Font("Edit Item",Font.BOLD,20));
        ControlPanel.add(editItemLabel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        ControlPanel.add(editItemBox,constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        ControlPanel.add(editItemPriceLabel,constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        ControlPanel.add(editPriceBox,constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        ControlPanel.add(editPriceSubmit,constraints);

        addNewItem.setFont(new Font("Add New Item",Font.BOLD,20));
        constraints.gridx = 0;
        constraints.gridy = 8;
        ControlPanel.add(addNewItem,constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        ControlPanel.add(addNewItemName,constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        ControlPanel.add(addNewItemPriceLabel,constraints);

        constraints.gridx = 1;
        constraints.gridy = 10;
        ControlPanel.add(addNewItemPrice,constraints);

        constraints.gridx = 0;
        constraints.gridy = 11;
        ControlPanel.add(addNewItemButton,constraints);

        deleteItemLabel.setFont(new Font("Delete An Item",Font.BOLD,20));
        constraints.gridx = 0;
        constraints.gridy = 12;
        ControlPanel.add(deleteItemLabel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 13;
        ControlPanel.add(deleteItemBox,constraints);

        constraints.gridx = 0;
        constraints.gridy = 14;
        ControlPanel.add(deleteItemSubmit,constraints);

        //---------------------------------------------------------
        StatisticPanel.setLayout(new GridBagLayout());

        constraints.insets = new Insets(0,170,0,0);
        statsListLabel.setFont(new Font("Statistics",Font.BOLD,30));
        constraints.gridx = 0;
        constraints.gridy = 0;
        StatisticPanel.add(statsListLabel,constraints);

        constraints.insets = new Insets(0,25,0,25);
        constraints.gridx = 0;
        constraints.gridy = 1;
//        StatisticPanel.add(pickRCM,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 2;
        StatisticPanel.add(statsList,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        StatisticPanel.add(updateStatsList,constraints);

        //--------------------------------------------------------

        Comparable key = new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

        GraphPanel.setLayout(new BorderLayout());
        DefaultPieDataset data = new DefaultPieDataset();
        data.insertValue(0,key,1.2);

        PiePlot pie = new PiePlot(data);
        ChartPanel chart = new ChartPanel(new JFreeChart(pie));
        constraints.gridx = 0;
        constraints.gridy = 0;
        GraphPanel.add(chart);

        //TODO: Implement these action listeners

        RCMList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRCMInformation(RCMList.getSelectedIndex());
            }
        });

//        RCMChangeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateRCMInformation(RCMList.getSelectedIndex());
//            }
//        });

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
                emptyRCM(RCMList.getSelectedIndex());
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
                String string = RCMNewCapacity.getText();
                editRCMCapacity(RCMList.getSelectedIndex(),Double.parseDouble(string));
                updateRCMInformation(RCMList.getSelectedIndex());
            }
        });

        updateStatsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRMOS.setupStatistics("2");
                updateRCMComboBoxes();
            }
        });

        addRCMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                mRMOS.addRCM(new RCMFunction());
                mRMOS.test();
            }
        });

        addNewItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRMOS.addItem((String)addNewItemName.getSelectedItem());
                updateItemComboBoxes();
            }
        });

        editPriceSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRMOS.editItemPrice(editItemBox.getSelectedIndex());
            }
        });

        deleteItemSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRMOS.deleteItem(deleteItemBox.getSelectedIndex());
                updateItemComboBoxes();
            }
        });

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //functions to update UI-----------------------------------
    public void updateRCMComboBoxes() {
        for(int i = RCMList.getItemCount();i<mRMOS.getRCMNumber();i++) {
            RCMList.addItem(mRMOS.getRCMName(i));
        }
    }

    public void updateRCMInformation(int i) {
        RCMWeight.setText("Weight: " + mRMOS.getRCMWeight(i));
        RCMCapacity.setText("Capacity: " + mRMOS.getRCMCapacity(i));
        RCMLabel.setText("RCM: " + mRMOS.getRCMID(i));
        RCMLocation.setText("Location: " + mRMOS.getRCMLocation(i));
        RCMItems.setText("Items: " + mRMOS.getNumberOfItems(i));
        RCMMoney.setText("Money: " + mRMOS.getRCMMoney(i));
    }

    public void updateItemComboBoxes() {
        for(int i=0;i<mRMOS.getActiveItemNumber();i++) {
            deleteItemBox.addItem(mRMOS.getActiveItemName(i));
            editItemBox.addItem(mRMOS.getActiveItemName(i));
        }
    }

    //function to add/edit RCMs------------------------------------
    public void addRCM(RCMFunction rcm) {
        mRMOS.addRCM(rcm);
        updateRCMComboBoxes();
    }

//    public void editRCMMoney(USMoney money,) {
////        mRMOS.change
//    }

    public void editRCMCapacity(int index, double capacity){
        mRMOS.editRCMCapacity(index,capacity);
    }

    public void emptyRCM(int index) {
        mRMOS.empty(index);
    }
//functions to add/edit items


//-------------------------------------------------
    public static void main(String[] args) {
        RMOS rmos = new RMOS();
        rmos.addRCM(new RCMFunction("Las Vegas, CA", "0001", 10));
        rmos.addRCM(new RCMFunction("San Jose, CA", "0002", 12));
    }
}
