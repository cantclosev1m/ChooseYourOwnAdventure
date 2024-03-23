package adventuregame;

import javax.swing.*;
import java.awt.*;

public class EventScreen extends JFrame {
    // function responsible for building event screen
    public void buildEventScreen() {
        // config screen
        setTitle("Event Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // set up event screen layout
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel middlePanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        // left portion
        JLabel leftL = new JLabel("Left Section");
        leftL.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(leftL, BorderLayout.CENTER);
        // middle portion
        JLabel topTextLabel = new JLabel("Event info text...", SwingConstants.CENTER);
        middlePanel.add(topTextLabel, BorderLayout.NORTH);
        JPanel bottomPanel = new JPanel(new GridLayout(3, 1)); // for stacking buttons
        JButton choice1B = new JButton("Choice 1");
        JButton choice2B = new JButton("Choice 2");
        JButton choice3B = new JButton("Choice 3");
        Dimension buttonSize = new Dimension(150, 50);
        choice1B.setPreferredSize(buttonSize);
        choice2B.setPreferredSize(buttonSize);
        choice3B.setPreferredSize(buttonSize);
        bottomPanel.add(choice1B);
        bottomPanel.add(choice2B);
        bottomPanel.add(choice3B);
        middlePanel.add(bottomPanel, BorderLayout.SOUTH); // center bottom of middle section
        // right portion
        JButton saveB = new JButton("Save (and quit)"); 
        JButton inventoryB = new JButton("Inventory"); // Add an extra button for demonstration
        JPanel buttonPanel2 = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonPanel2.add(saveB);
        buttonPanel2.add(inventoryB);
        rightPanel.add(buttonPanel2, BorderLayout.NORTH);
        
        // define button actions
        choice1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        choice2B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        choice3B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        saveB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        inventoryB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        
        // adding panels to screen
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        setVisible(true);
    }
}
