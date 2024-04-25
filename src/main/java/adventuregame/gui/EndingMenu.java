package adventuregame.gui;

import adventuregame.Graph;
import adventuregame.util.Event;
import adventuregame.util.BindableEvent;

import adventuregame.App;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the ending menu in an adventure game, providing options to restart the game, return to the main menu, or quit.
 * It also handles saving and viewing the inventory.
 */
public class EndingMenu extends JComponent {
    private JPanel leftPanel;
    private JPanel middlePanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;

    private JLabel leftLabel;
    private JLabel topTextLabel;

    private JButton mainMenuB;
    private JButton playAgainB;
    private JButton quitB;

    private JButton saveB;
    private JButton inventoryB;
    private JPanel buttonPanel2;
    
    private java.util.List<JButton> gameButtonList = new ArrayList<>();
    public adventuregame.util.Event<GameMenu.GameButtonClickEvent> onGameButtonClick = new BindableEvent<GameMenu.GameButtonClickEvent>();
    public adventuregame.util.Event<Void> onGameSave = new BindableEvent<>();
    public Graph.Node endNode;
    
    public Event<Void> playAgain = new BindableEvent<>();
    public Event<Void> goToMM = new BindableEvent<>();

    /**
     * Default constructor used for initial purposes.
     */
    public EndingMenu() { }

    /**
     * Constructor for initializing with specific events for play again and go to main menu functionalities.
     * @param pA The event to fire when "Play Again" is triggered.
     * @param gTMM The event to fire when "Go To Main Menu" is triggered.
     */
    public EndingMenu(Event<Void> pA, Event<Void> gTMM)
    {
        playAgain = pA;
        goToMM = gTMM;
    }

    /**
     * Creates and arranges all UI components within the ending menu.
     */
    public void create() {
        removeAll();
        setSize(800, 600);
        setLayout(new BorderLayout());

        // set up event screen layout
        leftPanel = new JPanel(new BorderLayout());
        middlePanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel(new BorderLayout());

        // left portion
        leftLabel = new JLabel("       "); // for some space between image and event text
        leftLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(leftLabel, BorderLayout.CENTER);
        leftPanel.setBackground(Color.BLACK);
        leftLabel.setBackground(Color.BLACK);

        // middle portion
        topTextLabel = new JLabel("<html>" + endNode.getDescription(), SwingConstants.CENTER);
        topTextLabel.setForeground(Color.WHITE);
        topTextLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
        middlePanel.add(topTextLabel, BorderLayout.CENTER);
        middlePanel.setBackground(Color.BLACK);

        bottomPanel = new JPanel(new GridLayout(3, 1)); // for stacking buttons
        bottomPanel.setBackground(Color.BLACK);

        mainMenuB = new JButton("Go to Main Menu");
        mainMenuB.setBackground(Color.gray);
        mainMenuB.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));

        playAgainB = new JButton("Play again");
        playAgainB.setBackground(Color.gray);
        playAgainB.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));


        quitB = new JButton("Quit");
        quitB.setBackground(Color.gray);
        quitB.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
        
        // set button actions
        mainMenuB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMM.Fire();
            }
        });
        playAgainB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAgain.Fire();
            }
        });
        quitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        gameButtonList.add(0, mainMenuB);
        gameButtonList.add(1, playAgainB);
        gameButtonList.add(2, quitB);

        Dimension buttonSize = new Dimension(150, 50);

        for(int i=0; i < 3; i++)
        {
            JButton button = gameButtonList.get(i);
            button.setPreferredSize(buttonSize);
            bottomPanel.add(button);
        }

        middlePanel.add(bottomPanel, BorderLayout.SOUTH); // center bottom of middle section

        // right portion
        saveB = new JButton("Save (and quit)");
        saveB.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
        saveB.setBackground(Color.gray);

        inventoryB = new JButton("Inventory"); // Add an extra button for demonstration
        inventoryB.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
        inventoryB.setBackground(Color.gray);

        buttonPanel2 = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonPanel2.add(saveB);
        buttonPanel2.add(inventoryB);
        buttonPanel2.setBackground(Color.BLACK);

        rightPanel.add(buttonPanel2, BorderLayout.NORTH);
        rightPanel.setBackground(Color.BLACK);

        saveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGameSave.Fire();
            }
        });
        
        loadImage(endNode);
        
        // adding panels to screen
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    /**
     * Sets the visibility of this component.
     * @param visible true to make the component visible; false otherwise.
     */
    public void setVisibility(boolean b)
    {
        setVisible(b);
    }

    /**
     * Updates the descriptions on the game buttons based on the provided descriptions.
     * @param buttonDescriptions Array of descriptions for the buttons.
     */
    public void massSetButtonListDesc(String[] buttonDescriptions)
    {
        assert (buttonDescriptions.length == 3) : "Description list must be of length 3";

        for(int i =0; i < 3; i++)
        {
            JButton button = gameButtonList.get(i);
            button.setText(buttonDescriptions[i]);
            button.setToolTipText(buttonDescriptions[i]);
        }
    }

    /**
     * Updates the event description text.
     * @param description The new description text.
     */
    public void setEventDescription(String description)
    {
        topTextLabel.setText("<html>" + description);
    }

    /**
     * Loads an image related to the current node into the ending menu.
     * @param currentNode The node whose image to load.
     */
    public void loadImage(Graph.Node currentNode) {
        String imagePath = currentNode.getImagePath();
        if (imagePath != null && !imagePath.isEmpty()) {
            setImage(imagePath);
        } else {
            setImage(null);
        }
    }

    /**
     * Sets the image on the left panel.
     * @param imagePath Path to the image file.
     */
    public void setImage (String imagePath)
    {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(450, 800, Image.SCALE_SMOOTH);//changed dem
        leftLabel.setIcon(new ImageIcon(image));
    }
}
