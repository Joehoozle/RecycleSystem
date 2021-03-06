package UI;

import Backend.RCMFunction;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import Backend.*;

/**
 * Class RCM
 * Created by pjaffurs on 3/8/2017.
 */
public class RCM extends JPanel{

    //Counters and operation variables
    private USMoney sessionMoney;

    //RCM Method driver
    private RCMFunction mRCM;

    //ArrayList to store items to be recycled
    private ArrayList<RecyclableItem> list = new ArrayList<>();

    //GUI Components
    private JPanel headerPanel;
    private JPanel titlePanel;
    private JPanel optionPanel;
    private JPanel entryPane;
    private JPanel outputPane;
    private JPanel recyclePanel;
    private JPanel moneyPanel;
    private JPanel dataPanel;

    private JRadioButton single;
    private JRadioButton multiple;
    private JButton updateButton;
    private JButton recycleButton;
    private JButton[] objectButtons; //object option buttons
    private int[] objectCounters;
    private JLabel[] objectLabels;
    private JLabel[] counterLabels;

    private JLabel title;
    private JLabel moneyLabel;
    private JLabel disabled;

    private boolean isOn = true;

    /////////////////////// CONSTRUCTOR /////////////////////////
    public RCM(RCMFunction mRCM){

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Container container = new Container();
        container.setLayout(new BorderLayout());
        add(container);

        this.mRCM = mRCM;
        sessionMoney = new USMoney(0,0);

        Font titleFont = new Font("Title", Font.PLAIN, 20);
        Font entryFont = new Font("Entry", Font.PLAIN, 30);

        recycleButton = new JButton();
        recycleButton.setEnabled(false);

        //Buttons for choosing recyclables
        objectButtons = new JButton[6];
        //Counters for session objects
        objectCounters = new int[6];
        //Labels for session objects
        objectLabels = new JLabel[6];
        //Labels for object counters
        counterLabels = new JLabel[6];

        /* HEADER PANEL */
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        container.add(headerPanel, BorderLayout.NORTH);

        disabled = new JLabel("RCM INACTIVE");
        disabled.setFont(entryFont);
        disabled.setForeground(Color.RED);
        disabled.setVisible(false);

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.decode("#00BCD4"));
        title = new JLabel(mRCM.toString());
        title.setFont(entryFont);
        titlePanel.add(title);
        titlePanel.add(disabled);
        headerPanel.add(titlePanel, BorderLayout.NORTH);
        optionPanel = new JPanel();
        optionPanel.setBorder(BorderFactory.createTitledBorder(null,"Select Your Recycling Option",
                TitledBorder.CENTER, TitledBorder.TOP, titleFont));
        headerPanel.add(optionPanel, BorderLayout.SOUTH);

        single = new JRadioButton("Recycle One Item");
        single.setSelected(true);
        single.setFont(entryFont);
        single.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButtons();
                recycleButton.setEnabled(false);
            }
        });

        multiple = new JRadioButton("Recycle Multiple Items");
        multiple.setFont(entryFont);
        multiple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButtons();
                recycleButton.setEnabled(true);
            }
        });

        //button grouping
        ButtonGroup bg = new ButtonGroup();
        bg.add(single);
        bg.add(multiple);
        optionPanel.add(single);
        optionPanel.add(multiple);
        updateButton = new JButton("UPDATE");
        updateButton.setFont(entryFont);

        //when pressed, the update button updates the RCM (checks if it is active, what items should be represented, ect)
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButtons();
                if(mRCM.isActivated() && !isOn ) {
                    activate();
                    isOn = true;
                } else if(!mRCM.isActivated() && isOn) {
                    deactivate();
                    isOn = false;
                }
            }
        });
        optionPanel.add(updateButton);

        /* ENTRY PANEL */
        entryPane = new JPanel();
        entryPane.setLayout(new GridLayout(3, 2, 200, 200));
        entryPane.setPreferredSize(new Dimension(958, 1080));
        entryPane.setBackground(Color.decode("#00BCD4"));
        entryPane.setBorder(BorderFactory.createTitledBorder(null,"Select Your Item(s)",
                TitledBorder.CENTER, TitledBorder.TOP, titleFont));
        entryPane.setFont(entryFont);
        container.add(entryPane, BorderLayout.WEST);

        //Loop to activate a set of 4 arrays: two of panels, one of buttons, one of ints
        for(int i = 0; i < 6; i++){
            final int tmp = i;
            objectCounters[i] = 0;
            objectButtons[i] = new JButton("");
            objectLabels[i] = new JLabel("");
            counterLabels[i] = new JLabel("0");
            objectButtons[i].setFont(entryFont);
            objectButtons[tmp].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if(single.isSelected()){
                        RecyclableItem item = new RecyclableItem(objectButtons[tmp].getText());
                        runningWeight(item.getWeight());
                        if(mRCM.isFull()){
                            return;
                        }
                        mRCM.incrementItemCount(item.getMaterialType());
                        list.add(item);
                        mRCM.recycle(list);
                        list.clear();
                    }
                    else{
                        //adds selected item to the list and updates the displayed price
                        USMoney tmpMoney;
                        USMoney price;
                        RecyclableItem item = new RecyclableItem(objectButtons[tmp].getText());
                        runningWeight(item.getWeight());
                        if(mRCM.isFull()){
                            list.clear();
                            resetCounterLabels();
                            sessionMoney = new USMoney(0,0);
                            return;
                        }
                        mRCM.incrementItemCount(item.getMaterialType());
                        list.add(item);
                        objectCounters[tmp]++;
                        counterLabels[tmp].setText(String.valueOf(objectCounters[tmp]));
                        tmpMoney = mRCM.getRecyclableItemPrices().get(objectButtons[tmp].getText());
                        price = tmpMoney.calculateCost(list.get(list.size()-1).getWeight());
                        sessionMoney.add(price);
                        moneyLabel.setText(sessionMoney.toString());
                    }
                }
            });
            objectButtons[i].setVisible(false);
            entryPane.add(objectButtons[i]);
        }

        /* OUTPUT PANEL */
        outputPane = new JPanel();
        outputPane.setLayout(new BorderLayout());
        outputPane.setPreferredSize(new Dimension(960, 1080));
        outputPane.setBackground(Color.decode("#00BCD4"));
        outputPane.setBorder(BorderFactory.createTitledBorder(null,"Session Information",
                TitledBorder.CENTER, TitledBorder.TOP, titleFont));
        container.add(outputPane, BorderLayout.EAST);

        // Money subpanel
        moneyPanel = new JPanel();
        moneyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        moneyPanel.setBackground(Color.decode("#00BCD4"));
        moneyLabel = new JLabel(sessionMoney.toString());
        Font moneyFont = new Font("Money", Font.BOLD, 45);
        moneyLabel.setFont(moneyFont);
        moneyLabel.setForeground(Color.YELLOW);
        moneyPanel.add(moneyLabel);
        outputPane.add(moneyPanel, BorderLayout.NORTH);

        //Data subpanel
        dataPanel = new JPanel();
        dataPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dataPanel.setLayout(new GridLayout(6, 2));
        //goes through arrays of labels to add to grid
        for(int i = 0;i < 6;i++){
            objectLabels[i].setFont(titleFont);
            objectLabels[i].setVisible(false);
            dataPanel.add(objectLabels[i]);
            counterLabels[i].setFont(titleFont);
            counterLabels[i].setVisible(false);
            dataPanel.add(counterLabels[i]);
        }
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

        //when clicked, the item enters the recycling process in RCMFunction
        recycleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mRCM.recycle(list);
                list.clear();
                sessionMoney.setDollars(0);
                sessionMoney.setCents(0);
                resetCounterLabels();
                moneyLabel.setText(sessionMoney.toString());

                //TODO: implement a pop-up for the actual money returned and the
            }
        });
        moneyPanel.add(recycleButton);
        parseActiveItems(list);
        repaint();
        setVisible(true);
    }

    ////////////////////// OPERATION METHODS //////////////////////
    private void activate(){
        recycleButton.setEnabled(true);
        single.setEnabled(true);
        multiple.setEnabled(true);
        for(int i=0;i < 6; i++){
            objectButtons[i].setEnabled(true);
        }
        disabled.setVisible(false);
    }

    private void deactivate(){
        recycleButton.setEnabled(false);
        single.setEnabled(false);
        multiple.setEnabled(false);
        for(int i=0;i < 6; i++){
            objectButtons[i].setEnabled(false);
        }
        disabled.setVisible(true);
    }

    /**
     * parseActiveItems()
     * Parses the ArrayList of items, activating and initializing
     * buttons, counters, and labels if that value exists. If not,
     * they are hidden from view.
     * @param list      list of items accepted by RCMs
     */
    private void parseActiveItems(ArrayList<RecyclableItem> list){
        HashMap<String, USMoney> prices = mRCM.getRecyclableItemPrices();
        for(int i = 0; i < 6;i++) {
            if (i >= list.size()) {
                objectButtons[i].setVisible(false);
                objectLabels[i].setVisible(false);
                counterLabels[i].setVisible(false);
            }
            else {
                String typeText = list.get(i).getMaterialType();
                objectCounters[i] = 0;
                objectButtons[i].setText(typeText + ": " + mRCM.getRecyclableItemPrices().get(typeText).toString() + "/lb");
                objectButtons[i].setVisible(true);
                objectLabels[i].setText(list.get(i).getMaterialType());
                objectLabels[i].setVisible(true);
                counterLabels[i].setText("0");
                counterLabels[i].setVisible(true);
            }
        }
    }

    //updates the RCM button and button label UI when new items are added or items are deleted from the RMOS
    private void updateButtons() {
        int i;
        for (i = 0; i < mRCM.availableItems(); i++) {
            objectButtons[i].setVisible(true);
            objectButtons[i].setText(mRCM.getItemNameByIndex(i));
            objectLabels[i].setText(mRCM.getItemNameByIndex(i) + ": " + mRCM.getRecyclableItemPrices().
                    get(objectButtons[i].getText()).toString() + "/lb");
            counterLabels[i].setVisible(true);
            objectLabels[i].setVisible(true);
        }
        for(;i<objectButtons.length;i++) {
            objectButtons[i].setVisible(false);
            counterLabels[i].setVisible(false);
            objectLabels[i].setVisible(false);

        }
        revalidate();
        repaint();
    }

    //sets counter labels to 0 after a recycle
    private void resetCounterLabels() {
        for(int i=0;i<objectCounters.length;i++) {
            objectCounters[i] = 0;
            counterLabels[i].setText("0");
            moneyLabel.setText(sessionMoney.toString());
        }
    }

    // keeps track of the weight as items are added in a session
    private void runningWeight(double weight) {
        if(mRCM.getWeight() + weight > mRCM.getCapacity()) {
            JOptionPane.showMessageDialog(null,"The RCM has reached capacity! Session Aborted!","Error",JOptionPane.ERROR_MESSAGE);
            mRCM.setFull(true);
            return;
        }
        mRCM.addWeight(weight);
    }
}