package adventuregame.gui;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameMenu extends JFrame {

    private JPanel leftPanel;
    private JPanel middlePanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;

    private JLabel leftLabel;
    private JLabel topTextLabel;

    private JButton choice1B;
    private JButton choice2B;
    private JButton choice3B;

    private JButton saveB;
    private JButton inventoryB;
    private JPanel buttonPanel2;

    public GameMenu()
    {
        setTitle("Event Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // set up event screen layout
        leftPanel = new JPanel(new BorderLayout());
        middlePanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel(new BorderLayout());

        // left portion
        leftLabel = new JLabel("Left Section");
        leftLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(leftLabel, BorderLayout.CENTER);

        // middle portion
        topTextLabel = new JLabel("Event info text...", SwingConstants.CENTER);
        middlePanel.add(topTextLabel, BorderLayout.NORTH);

        bottomPanel = new JPanel(new GridLayout(3, 1)); // for stacking buttons

        choice1B = new JButton("Choice 1");
        choice2B = new JButton("Choice 2");
        choice3B = new JButton("Choice 3");
        Dimension buttonSize = new Dimension(150, 50);

        choice1B.setPreferredSize(buttonSize);
        choice2B.setPreferredSize(buttonSize);
        choice3B.setPreferredSize(buttonSize);

        bottomPanel.add(choice1B);
        bottomPanel.add(choice2B);
        bottomPanel.add(choice3B);
        middlePanel.add(bottomPanel, BorderLayout.SOUTH); // center bottom of middle section

        // right portion
        saveB = new JButton("Save (and quit)");
        inventoryB = new JButton("Inventory"); // Add an extra button for demonstration

        buttonPanel2 = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonPanel2.add(saveB);
        buttonPanel2.add(inventoryB);

        rightPanel.add(buttonPanel2, BorderLayout.NORTH);

        // adding panels to screen
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    public void setVisibility(boolean b)
    {
        setVisible(b);
    }

    public void massSetButtonListDesc(String[] buttonDescriptions)
    {
        assert (buttonDescriptions.length == 3) : "Description list must be of length 3";

        choice1B.setText(buttonDescriptions[0]);
        choice2B.setText(buttonDescriptions[1]);
        choice3B.setText(buttonDescriptions[2]);
    }

    public void setEventDescription(String description)
    {
        topTextLabel.setText(description);
    }
}
