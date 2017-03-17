
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Class RCM
 * Created by pjaffurs on 3/8/2017.
 */
public class RCM extends JFrame implements Observer{
    //Counters and operation variables
    private USMoney tmpMoney;

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
    private JButton recycleButton;
    private JButton[] objectButtons; //object option buttons
    private int[] objectCounters;
    private JLabel[] objectLabels;
    private JLabel[] counterLabels;

    private JLabel title;
    private JLabel moneyLabel;
    private JLabel disabled;

    /////////////////////// CONSTRUCTOR /////////////////////////
    public RCM(RCMFunction mRCM){
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

        this.mRCM = mRCM;
        tmpMoney = new USMoney(0,0);

        Font titleFont = new Font("Title", Font.PLAIN, 20);
        Font entryFont = new Font("Entry", Font.PLAIN, 30);

        //Initializing button status for single recycling
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
                recycleButton.setEnabled(false);

            }
        });

        multiple = new JRadioButton("Recycle Multiple Items");
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
                        list.add(new RecyclableItem(counterLabels[tmp].getText()));
                        mRCM.recycle(list);
                        list.clear();
                    }
                    else{
                        //TODO: add prices to counter and configure weights
                        list.add(new RecyclableItem(counterLabels[tmp].getText()));
                        objectCounters[tmp]++;
                        counterLabels[tmp].setText(String.valueOf(objectCounters[tmp]));
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
        recycleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mRCM.recycle(list);
                list.clear();
            }
        });
        recyclePanel.add(recycleButton, BorderLayout.SOUTH);

        parseActiveItems(list);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    ////////////////////// OPERATION METHODS //////////////////////
    public void activate(){
        recycleButton.setEnabled(true);
        single.setEnabled(true);
        multiple.setEnabled(true);
        for(int i=0;i < 6; i++){
            objectButtons[i].setEnabled(true);
        }
        disabled.setVisible(false);
    }

    public void deactivate(){
        recycleButton.setEnabled(false);
        single.setEnabled(false);
        multiple.setEnabled(false);
        for(int i=0;i < 6; i++){
            objectButtons[i].setEnabled(false);
        }
        disabled.setVisible(true);
    }

    public void update(Observable ob, Object object){
        ArrayList<RecyclableItem> available = (ArrayList<RecyclableItem>) object;

        parseActiveItems(available);
    }

    /**
     * parseActiveItems()
     * Parses the ArrayList of items, activating and initializing
     * buttons, counters, and labels if that value exists. If not,
     * they are hidden from view.
     * @param list      list of items accepted by RCMs
     */
    public void parseActiveItems(ArrayList<RecyclableItem> list){
        for(int i = 0; i < 6;i++) {
            if (i >= list.size()) {
                objectButtons[i].setVisible(false);
                objectLabels[i].setVisible(false);
                counterLabels[i].setVisible(false);
            }
            else {
                objectCounters[i] = 0;
                objectButtons[i].setText(list.get(i).getMaterialType());
                objectButtons[i].setVisible(true);
                objectLabels[i].setText(list.get(i).getMaterialType());
                objectLabels[i].setVisible(true);
                counterLabels[i].setText("0");
                counterLabels[i].setVisible(true);
            }
        }
    }
    ////////////////////////// MAIN ////////////////////////////
    public static void main(String[] args){
        new RCM(new RCMFunction("Library", "0", 100));
    }
}