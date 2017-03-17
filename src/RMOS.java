import com.sun.security.auth.module.JndiLoginModule;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Dante on 3/8/2017.
 */
public class RMOS extends JFrame {

    RMOSFunction mRMOS;
    Container container;

    //4 panels for main screen
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    //RCM Control menu items
    private JPanel RCMPanel;
    private JComboBox<String> RCMList;
    private JLabel RCMLabel;
    private JLabel RCMInformation;
    private JButton RCMTurnOn; //TODO: Add functionality for on and off
    private JButton RCMTurnOff;
    private JLabel RCMOffAlert;
    private JLabel RCMWeight;
    private JLabel RCMItems;
    private JLabel RCMCapacity;
    private JButton RCMEmpty;
    private JLabel RCMMoney;
    private JLabel RCMLocation;
    private JLabel RCMChangeCapacity; //TODO: Make sure that when we change this we check that it is a number and that the weight is not greater
    private JTextField RCMChangeMoneyDollars;
    private JTextField RCMChangeMoneyCents;
    private JLabel RCMCapacityChangeError;
    private JButton RCMChangeMoneyButton;

    //modify menu items
    private JPanel GraphPanel;

    //Statistic Menu
    private JPanel StatisticPanel;
    private JTextArea statsList;
    private JComboBox<String> pickStat;
    private JLabel statsListLabel;
    private ArrayList<JLabel> itemPrices;
    private JLabel itemPrice1;
    private JLabel itemPrice2;
    private JLabel itemPrice3;
    private JLabel itemPrice4;
    private JLabel itemPrice5;
    private JLabel itemPrice6;

    //Control Panel
    private JPanel ControlPanel;
    private JLabel addRCM;
    private JLabel insertID;
    private JTextField newRCMName;
    private JLabel locationRCM;
    private JTextField locationFieldRCM;
    private JLabel insertCapacity;
    private JTextField newRCMCapacity;
    private JButton addRCMButton;
    private JLabel editItemLabel;
    private JComboBox<String> editItemBox;
    private JTextField editItemPriceDollars;
    private JTextField editItemPriceCents;
    private JButton editPriceSubmit;
    private JLabel addNewItem;
    private JTextField addNewItemName;
    private JLabel addNewItemPriceLabel;
    private JTextField addNewItemPriceDollars;
    private JTextField addNewItemPriceCents;
    private JButton addNewItemButton;
    private JComboBox<String> deleteItemBox;
    private JButton deleteItemSubmit;
    private JLabel deleteItemLabel;
    private JLabel editItemPriceLabel;

    //TODO: Make a login screen


    public class LoginFrame extends JFrame {
        //Log in screen
        private JTextField username;
        private JPasswordField password;
        private JButton loginButton;
        private JPanel loginBox;
        private Container loginScreen;

        public LoginFrame() {
            username = new JTextField();
            password = new JPasswordField();
            loginButton = new JButton("Submit");
            loginBox = new JPanel();
            loginScreen = getContentPane();
            container.setVisible(false);

            loginScreen.setLayout(new FlowLayout());
            loginBox.setLayout(new BoxLayout(loginBox, BoxLayout.Y_AXIS));
            loginBox.add(loginButton, JButton.CENTER);
            loginScreen.add(loginBox);
            password.setPreferredSize(new Dimension(200, 20));
            loginBox.add(password, JLabel.CENTER);
            username.setPreferredSize(new Dimension(200, 20));
            loginBox.add(username, JLabel.CENTER);
            loginScreen.setSize(new Dimension(200, 20));
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (username.getText().equals("admin") && (String.copyValueOf(password.getPassword()).equals("test"))) {
                        container.setVisible(true);
                        container.revalidate();
                        container.repaint();
                        dispose();
                    }
                }
            });
            pack();
        }
    }


    public RMOS(RMOSFunction rmosFunction) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        container = new Container();
        add(container);

        LoginFrame login = new LoginFrame();
        login.setVisible(true);

        //All of the
        mRMOS = rmosFunction;

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
        RCMLocation = new JLabel();
        RCMChangeCapacity = new JLabel("Change Money");
        RCMChangeMoneyDollars = new JTextField("Dollar");
        RCMChangeMoneyCents = new JTextField("Cents");
        RCMCapacityChangeError = new JLabel("No errors at this point");
        RCMChangeMoneyButton = new JButton("Change Money");
        RCMEmpty = new JButton("Empty");
        RCMInformation = new JLabel("RCM Information");
        RCMTurnOn = new JButton("Turn On");
        RCMTurnOff = new JButton("Turn Off");
        RCMOffAlert = new JLabel("RCM Is Off");


        addRCM = new JLabel("Add RCM");
        insertID = new JLabel("Insert New RCM ID");
        newRCMName = new JTextField();
        insertCapacity = new JLabel("Insert New RCM Capacity");
        newRCMCapacity = new JTextField();
        locationRCM = new JLabel("Insert Location");
        locationFieldRCM = new JTextField();
        addRCMButton = new JButton("Add RCM");
        editItemLabel = new JLabel("Edit Item");
        editItemBox = new JComboBox();
        editItemLabel = new JLabel("Edited Item Price");
        editItemPriceLabel = new JLabel("Edit Price");
        editItemPriceDollars = new JTextField("Dollars");
        editItemPriceCents = new JTextField("Cents");
        editPriceSubmit = new JButton("Edit Price");
        addNewItem = new JLabel("Add an Item");
        addNewItemName = new JTextField();
        addNewItemPriceLabel = new JLabel("Item Price Per Pound");
        addNewItemPriceDollars = new JTextField("Dollars");
        addNewItemPriceCents = new JTextField("Cents");
        addNewItemButton = new JButton("Add New Item");
        deleteItemLabel = new JLabel("Delete an Item");
        deleteItemBox = new JComboBox();
        deleteItemSubmit = new JButton("Delete Item");

        statsListLabel = new JLabel("Statistics");
        itemPrices = new ArrayList<JLabel>();
        itemPrice1 = new JLabel("item");
        itemPrice2 = new JLabel("item");
        itemPrice3 = new JLabel("item");
        itemPrice4 = new JLabel("item");
        itemPrice5 = new JLabel("item");
        itemPrice6 = new JLabel("item");
        itemPrices.add(itemPrice1);
        itemPrices.add(itemPrice2);
        itemPrices.add(itemPrice3);
        itemPrices.add(itemPrice4);
        itemPrices.add(itemPrice5);
        itemPrices.add(itemPrice6);
        for (int i = 0; i < 6; i++) {
            itemPrices.get(i).setVisible(false);
        }
        pickStat = new JComboBox<String>();
        statsList = new JTextArea();
//        updateStatsList = new JButton("Stats");

        //TODO: Does this work?
//        container = getContentPane();
        container.setLayout(new GridLayout(1, 4, 20, 0));
//        container.setBackground(Color.decode("#B388FF"));

        panel1.setLayout(new GridBagLayout());
//        panel1.setBackground(Color.decode("#B388FF"));
        panel2.setLayout(new GridBagLayout());
//        panel2.setBackground(Color.decode("#B388FF"));
        panel3.setLayout(new GridBagLayout());
        panel3.setOpaque(true);
//        panel3.setBackground(Color.decode("#B388FF"));
        panel4.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        RCMPanel.setBackground(Color.decode("#A9A9A9"));
        GraphPanel.setBackground(Color.decode("#A9A9A9"));
        StatisticPanel.setBackground(Color.decode("#A9A9A9"));
        ControlPanel.setBackground(Color.decode("#A9A9A9"));

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
        panel1.add(RCMPanel, constraints);

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
        panel2.add(GraphPanel, constraints);

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
        panel3.add(StatisticPanel, constraints);

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

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 25, 0, 25);
        RCMPanel.add(RCMList, constraints);

//        constraints.insets = new Insets(0,,0,0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        RCMLabel.setFont(new Font("RCM", Font.BOLD, 30));
        RCMPanel.add(RCMLabel, constraints);

        RCMOffAlert.setForeground(Color.red);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        RCMOffAlert.setFont(new Font("RCM Is Off", Font.BOLD, 20));
        RCMPanel.add(RCMOffAlert, constraints);

        constraints.insets = new Insets(0, 50, 0, 50);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        RCMPanel.add(RCMTurnOn, constraints);

        constraints.insets = new Insets(0, 25, 0, 25);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 3;
        RCMPanel.add(RCMTurnOff, constraints);

        RCMInformation.setFont(new Font("RCM Information", Font.BOLD, 20));
        constraints.gridx = 0;
        constraints.gridy = 4;
        RCMPanel.add(RCMInformation, constraints);

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 5;
        RCMPanel.add(RCMWeight, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        RCMPanel.add(RCMEmpty, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        RCMPanel.add(RCMCapacity, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        RCMPanel.add(RCMItems, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        RCMPanel.add(RCMMoney, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        RCMPanel.add(RCMLocation, constraints);

        RCMChangeCapacity.setFont(new Font("Change Money", Font.BOLD, 20));
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.CENTER;
        RCMPanel.add(RCMChangeCapacity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        RCMPanel.add(RCMChangeMoneyDollars, constraints);

        constraints.gridx = 1;
        constraints.gridy = 10;
        RCMPanel.add(RCMCapacityChangeError, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 25, 0, 25);
        constraints.gridx = 0;
        constraints.gridy = 11;
        RCMPanel.add(RCMChangeMoneyCents, constraints);

        constraints.insets = new Insets(0, 25, 0, 25);
        constraints.gridx = 1;
        constraints.gridy = 11;
        RCMPanel.add(RCMChangeMoneyButton, constraints);

        //---------------------------------------------------------

//        constraints = new GridBagConstraints();

        ControlPanel.setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        addRCM.setFont(new Font("Add RCM", Font.BOLD, 20));
        ControlPanel.add(addRCM, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        ControlPanel.add(insertID, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        ControlPanel.add(newRCMName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        ControlPanel.add(insertCapacity, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        ControlPanel.add(newRCMCapacity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        ControlPanel.add(locationRCM, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        ControlPanel.add(locationFieldRCM, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        ControlPanel.add(addRCMButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        editItemLabel.setFont(new Font("Edit Item", Font.BOLD, 20));
        ControlPanel.add(editItemLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        ControlPanel.add(editItemBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        ControlPanel.add(editItemPriceLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
        ControlPanel.add(editItemPriceDollars, constraints);

        constraints.gridx = 2;
        constraints.gridy = 7;
        ControlPanel.add(editItemPriceCents, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        ControlPanel.add(editPriceSubmit, constraints);

        addNewItem.setFont(new Font("Add New Item", Font.BOLD, 20));
        constraints.gridx = 0;
        constraints.gridy = 9;
        ControlPanel.add(addNewItem, constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        ControlPanel.add(addNewItemName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 11;
        ControlPanel.add(addNewItemPriceLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 11;
        ControlPanel.add(addNewItemPriceDollars, constraints);

        constraints.gridx = 2;
        constraints.gridy = 11;
        ControlPanel.add(addNewItemPriceCents, constraints);

        constraints.gridx = 0;
        constraints.gridy = 12;
        ControlPanel.add(addNewItemButton, constraints);

        deleteItemLabel.setFont(new Font("Delete An Item", Font.BOLD, 20));
        constraints.gridx = 0;
        constraints.gridy = 13;
        ControlPanel.add(deleteItemLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 14;
        ControlPanel.add(deleteItemBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 15;
        ControlPanel.add(deleteItemSubmit, constraints);

        //---------------------------------------------------------
        StatisticPanel.setLayout(new GridBagLayout());

        constraints.insets = new Insets(0, 170, 0, 0);
        statsListLabel.setFont(new Font("Statistics", Font.BOLD, 30));
        constraints.gridx = 0;
        constraints.gridy = 0;
        StatisticPanel.add(statsListLabel, constraints);

        constraints.insets = new Insets(0, 25, 0, 25);
        constraints.gridx = 0;
        constraints.gridy = 1;
        StatisticPanel.add(itemPrice1, constraints);

        constraints.insets = new Insets(0, 25, 0, 25);
        constraints.gridx = 1;
        constraints.gridy = 1;
        StatisticPanel.add(itemPrice2, constraints);

        constraints.insets = new Insets(0, 25, 0, 25);
        constraints.gridx = 0;
        constraints.gridy = 2;
        StatisticPanel.add(itemPrice3, constraints);

        constraints.insets = new Insets(0, 25, 0, 25);
        constraints.gridx = 1;
        constraints.gridy = 2;
        StatisticPanel.add(itemPrice4, constraints);

        constraints.insets = new Insets(0, 25, 0, 25);
        constraints.gridx = 0;
        constraints.gridy = 3;
        StatisticPanel.add(itemPrice5, constraints);

        constraints.insets = new Insets(0, 25, 0, 25);
        constraints.gridx = 1;
        constraints.gridy = 3;
        StatisticPanel.add(itemPrice6, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        StatisticPanel.add(pickStat, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridheight = 1;
        StatisticPanel.add(statsList, constraints);

        //--------------------------------------------------------

        //Initial graph setup
        Comparable key = new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

        GraphPanel.setLayout(new GridLayout(2, 1));
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("this", new Double(40));
        data.setValue("that", new Double(60));

        PiePlot pie = new PiePlot(data);
        PiePlot pie2 = new PiePlot(data);
        ChartPanel chart = new ChartPanel(new JFreeChart(pie));
        ChartPanel chart2 = new ChartPanel(new JFreeChart(pie2));
        constraints.gridx = 0;
        constraints.gridy = 0;
        GraphPanel.add(chart);
        constraints.gridx = 0;
        constraints.gridy = 1;
        GraphPanel.add(chart2);

        //TODO: Implement these action listeners

        RCMList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRCMInformation(RCMList.getSelectedIndex());
            }
        });

        RCMTurnOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RCMTurnOn.setEnabled(false);
                RCMTurnOff.setEnabled(true);
                RCMChangeMoneyDollars.setEnabled(true);
                RCMChangeMoneyButton.setEnabled(true);
                RCMEmpty.setEnabled(true);
                RCMOffAlert.setEnabled(false);
            }
        });

        RCMTurnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RCMTurnOn.setEnabled(true);
                RCMTurnOff.setEnabled(false);
                RCMChangeMoneyDollars.setEnabled(false);
                RCMChangeMoneyButton.setEnabled(false);
                RCMEmpty.setEnabled(false);
                RCMOffAlert.setEnabled(true);
            }
        });

        RCMEmpty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyRCM(RCMList.getSelectedIndex());
            }
        });

        //TODO:Need to add this (add dollars and cents value)
        RCMChangeMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRMOS.editRCMMoney(new USMoney(Integer.parseInt(RCMChangeMoneyDollars.getText()), Integer.parseInt(RCMChangeMoneyCents.getText())),
                        RCMList.getSelectedIndex());
            }

        });

        //TODO: Fix, as we are using this to test
        addRCMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRMOS.addRCM(new RCMFunction(locationFieldRCM.getText(), newRCMName.getText(), Integer.parseInt(newRCMCapacity.getText())));
            }
        });

        addNewItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
                mRMOS.addItem((String) addNewItemName.getText(), new USMoney(Integer.parseInt(addNewItemPriceDollars.getText()), Integer.parseInt(addNewItemPriceCents.getText())));
                updateItemComboBoxesAdd();
                updateItemPriceInformation();
            }
        });

        editPriceSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRMOS.editItemPrice(editItemBox.getSelectedIndex(),
                        new USMoney(Integer.parseInt(editItemPriceDollars.getText()), Integer.parseInt(editItemPriceCents.getText())));
                updateItemPriceInformation();
            }
        });

        deleteItemSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRMOS.deleteItem(deleteItemBox.getSelectedIndex());
                updateItemComboBoxesDelete(deleteItemBox.getSelectedIndex());
                updateItemPriceInformation();
            }
        });

        updateRCMComboBoxes();
        RCMTurnOn.doClick();


        pack();
        revalidate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //functions to update UI-----------------------------------
    public void updateRCMComboBoxes() {
        for (int i = RCMList.getItemCount(); i < mRMOS.getRCMNumber(); i++) {
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

    public void updateItemComboBoxesAdd() {
        deleteItemBox.addItem(mRMOS.getActiveItemName(mRMOS.getActiveItemNumber() - 1));
        editItemBox.addItem(mRMOS.getActiveItemName(mRMOS.getActiveItemNumber() - 1));
    }

    public void updateItemComboBoxesDelete(int i) {
        deleteItemBox.removeItemAt(i);
        editItemBox.removeItemAt(i);
        revalidate();
    }

    public void updateItemPriceInformation() {
        for (int i = 0; i < 6; i++) {
            itemPrices.get(i).setVisible(false);
        }
        for (int i = 0; i < mRMOS.getActiveItemNumber(); i++) {
            itemPrices.get(i).setVisible(true);
            itemPrices.get(i).setText(mRMOS.getItemNameByIndex(i) + ": " + mRMOS.getItemPrice(i));
        }
    }

    //function to add/edit RCMs------------------------------------
    public void addRCM(RCMFunction rcm) {
        mRMOS.addRCM(rcm);
        updateRCMComboBoxes();
    }

    public void editRCMMoney(USMoney money, int index) {
        mRMOS.editRCMMoney(money, index);
    }

    public void editRCMCapacity(int index, double capacity) {
        mRMOS.editRCMCapacity(index, capacity);
    }

    public void emptyRCM(int index) {
        mRMOS.empty(index);
    }
//functions to add/edit items

    public void addNewItem(String name, USMoney price) {
        mRMOS.addItem(name, price);
    }


    //functions to pull statistic data
    public void postEverything() {
        mRMOS.setupStatistics();
        String string = mRMOS.getAllEntries();
//        ResultSet result = mRMOS.getAllEntries();
//        try {
//            if (result.next()) {
//                String ID = result.getString("RCMId");
//                statsList.setText(ID);
//            }
//        } catch(SQLException e) {
//            e.printStackTrace();
//            statsList.setText("Sorry :(");
//        }
        statsList.setText(string);
    }
}

//-------------------------------------------------
