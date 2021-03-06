package UI;

import Backend.RCMFunction;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Backend.*;

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
    private JButton RCMTurnOn;
    private JButton RCMTurnOff;
    private JLabel RCMOffAlert;
    private JLabel RCMWeight;
    private JLabel RCMItems;
    private JLabel RCMCapacity;
    private JButton RCMEmpty;
    private JLabel RCMMoney;
    private JLabel RCMLocation;
    private JLabel RCMChangeCapacity;
    private JTextField RCMChangeMoneyDollars;
    private JTextField RCMChangeMoneyCents;
    private JLabel RCMCapacityChangeError;
    private JButton RCMChangeMoneyButton;
    private JButton RCMUpdateButton;

    //modify menu items
    private JPanel GraphPanel;
    private ChartPanel chart1;
    private ChartPanel chart2;

    //statistic Menu
    private JPanel StatisticPanel;
    private JLabel statsList;
    private JComboBox<String> pickStat;
    private JLabel statsListLabel;
    private ArrayList<JLabel> itemPrices;
    private JLabel itemPrice1;
    private JLabel itemPrice2;
    private JLabel itemPrice3;
    private JLabel itemPrice4;
    private JLabel itemPrice5;
    private JLabel itemPrice6;

    //control panel
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


    //Login nested class that requires a user to log in before accessing the RMOS
    public class LoginFrame extends JFrame {
        //Log in screen
        private JTextField username;
        private JPasswordField password;
        private JButton loginButton;
        private JPanel loginBox;
        private Container loginScreen;

        public LoginFrame() {
            super("Please Log In");
            setLocationRelativeTo(null);

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
            loginScreen.setSize(new Dimension(400, 100));

            //what happens when the submit button is pushed
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
    //constructor
    public RMOS(RMOSFunction rmosFunction) {
        super("RMOS");
        setSize(new Dimension(1920, 1080));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        container = new Container();
        add(container);

        LoginFrame login = new LoginFrame();
        login.setVisible(true);

        //RMOSFunctional that was passed in with constuctor
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
        RCMUpdateButton = new JButton("Update");

        //control panel
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

        //statistics panel
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
        statsList = new JLabel();
        container.setLayout(new GridLayout(1, 4, 20, 0));

        //using a gridbaglayout for layout
        panel1.setLayout(new GridBagLayout());
        panel2.setLayout(new GridBagLayout());
        panel3.setLayout(new GridBagLayout());
        panel3.setOpaque(true);
        panel4.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        RCMPanel.setBackground(Color.decode("#A9A9A9"));
        GraphPanel.setBackground(Color.decode("#A9A9A9"));
        StatisticPanel.setBackground(Color.decode("#A9A9A9"));
        ControlPanel.setBackground(Color.decode("#A9A9A9"));


        //////// Grid Bag Layout Setup  ///////???

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

        //-------------------------------------
        RCMPanel.setLayout(new GridBagLayout());

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 25, 0, 25);
        RCMPanel.add(RCMList, constraints);

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

        constraints.gridx = 1;
        constraints.gridy = 4;
        RCMPanel.add(RCMUpdateButton, constraints);

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

        //----------------------------------------------------

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

        /////// Graph Setup /////////

        Comparable key = new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

        GraphPanel.setLayout(new GridLayout(2, 1));
        DefaultPieDataset data = new DefaultPieDataset();

        PiePlot pie = new PiePlot(data);
        PiePlot pie2 = new PiePlot(data);

        chart1 = new ChartPanel(new JFreeChart(pie));
        chart2 = new ChartPanel(new JFreeChart(pie2));

        constraints.gridx = 0;
        constraints.gridy = 0;
        GraphPanel.add(chart1);
        constraints.gridx = 0;
        constraints.gridy = 1;
        GraphPanel.add(chart2);

        pickStat.addItem("RCM Least Recently Emptied");
        pickStat.addItem("RCM with Most Recycles");

        //////// Action Listeners for Buttons and Comboboxes //////////

        pickStat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatsList();
            }
        });
        RCMUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRCMInformation(RCMList.getSelectedIndex());
                updateRCMComboBoxes();
            }
        });

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
                RCMChangeMoneyCents.setEnabled(true);
                RCMChangeMoneyButton.setEnabled(true);
                RCMEmpty.setEnabled(true);
                RCMOffAlert.setEnabled(false);
                mRMOS.activateRCM(RCMList.getSelectedIndex());
            }
        });

        RCMTurnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RCMTurnOn.setEnabled(true);
                RCMTurnOff.setEnabled(false);
                RCMChangeMoneyDollars.setEnabled(false);
                RCMChangeMoneyCents.setEnabled(false);
                RCMChangeMoneyButton.setEnabled(false);
                RCMEmpty.setEnabled(false);
                RCMOffAlert.setEnabled(true);
                mRMOS.deactivateRCM(RCMList.getSelectedIndex());
            }
        });

        RCMEmpty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyRCM(RCMList.getSelectedIndex());
                updateRCMInformation(RCMList.getSelectedIndex());
            }
        });

        //TODO:Need to add this (add dollars and cents value)
        RCMChangeMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRMOS.editRCMMoney(new USMoney(Integer.parseInt(RCMChangeMoneyDollars.getText()), Integer.parseInt(RCMChangeMoneyCents.getText())),
                        RCMList.getSelectedIndex());
                updateRCMInformation(RCMList.getSelectedIndex());
            }

        });

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
        setPreferredSize(new Dimension(1920,1080));
        pack();
        login.toFront();
        login.repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    ///////////// Update Functions ////////////////

    //update RCM comboboxes when new RCMs are added or deleted
    private void updateRCMComboBoxes() {
        for (int i = RCMList.getItemCount(); i < mRMOS.getRCMNumber(); i++) {
            RCMList.addItem(mRMOS.getRCMName(i));
        }
    }

    //Update the information on the RCM panel as well as the graphs and statistics
    private void updateRCMInformation(int i) {
        RCMWeight.setText("Weight: " + mRMOS.getRCMWeight(i));
        RCMCapacity.setText("Capacity: " + mRMOS.getRCMCapacity(i));
        RCMLabel.setText("RCM: " + mRMOS.getRCMID(i));
        RCMLocation.setText("Location: " + mRMOS.getRCMLocation(i));
        RCMItems.setText("Items: " + mRMOS.getNumberOfItems(i));
        RCMMoney.setText("Money: " + mRMOS.getRCMMoney(i));
        updateGraphs();
        updateStatsList();
    }

    //Updates the current statistic
    private void updateStatsList() {
        statsList.setText("");
        if(pickStat.getSelectedIndex() == 0) {
            statsList.setText(mRMOS.smallestTimestamp());
        } else if(pickStat.getSelectedIndex() == 1) {
            statsList.setText(mRMOS.mostRecycles());
        }
        statsList.revalidate();
        statsList.repaint();
    }

    //updates the graphs
    private void updateGraphs() {
        DefaultPieDataset data1 = new DefaultPieDataset();
        DefaultPieDataset data2 = new DefaultPieDataset();

        for(int i=0;i<mRMOS.getActiveItemNumber();i++) {
            if (mRMOS.getNumberOfItems(0) > 0) {
                int temp1 = (((mRMOS.fetchItemNumbers(mRMOS.getItemNameByIndex(i), 0)) * 100) / ((mRMOS.getNumberOfItems(0))));
                data1.setValue(mRMOS.getItemNameByIndex(i), temp1);
            } else {
                data1.setValue(mRMOS.getItemNameByIndex(i), 0);
            }
            if(mRMOS.getNumberOfItems(1) > 0) {
                int temp2 = (((mRMOS.fetchItemNumbers(mRMOS.getItemNameByIndex(i), 1))  * 100) / ((mRMOS.getNumberOfItems(1))));
                data2.setValue(mRMOS.getItemNameByIndex(i), temp2);
            } else {
                data2.setValue(mRMOS.getItemNameByIndex(i),0);
            }
        }
        chart1.removeAll();
        chart2.removeAll();
        chart1.revalidate();
        chart2.revalidate();
        GraphPanel.removeAll();
        GraphPanel.revalidate();
        PiePlot pie1 = new PiePlot(data1);
        PiePlot pie2 = new PiePlot(data2);
        ChartPanel newChart1 = new ChartPanel(new JFreeChart(pie1));
        ChartPanel newChart2 = new ChartPanel(new JFreeChart(pie2));
        GraphPanel.setLayout(new GridLayout(2,1));
        GraphPanel.add(newChart1);
        GraphPanel.add(newChart2);
        GraphPanel.validate();
        GraphPanel.repaint();

    }

    //updates the item comboboxes when a new item is added
    private void updateItemComboBoxesAdd() {
        deleteItemBox.addItem(mRMOS.getActiveItemName(mRMOS.getActiveItemNumber() - 1));
        editItemBox.addItem(mRMOS.getActiveItemName(mRMOS.getActiveItemNumber() - 1));
    }

    //updates the item comboboxes when an item is deleted
    private void updateItemComboBoxesDelete(int i) {
        deleteItemBox.removeItemAt(i);
        editItemBox.removeItemAt(i);
        revalidate();
    }

    //updates the price information of each item on the statistics panel
    private void updateItemPriceInformation() {
        for (int i = 0; i < 6; i++) {
            itemPrices.get(i).setVisible(false);
        }
        for (int i = 0; i < mRMOS.getActiveItemNumber(); i++) {
            itemPrices.get(i).setVisible(true);
            itemPrices.get(i).setText(mRMOS.getItemNameByIndex(i) + ": " + mRMOS.getItemPrice(i));
        }
    }

    //empties an RCM of all items
    private void emptyRCM(int index) {
        mRMOS.empty(index);
    }
}
