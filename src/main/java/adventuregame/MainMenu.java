package adventuregame;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    public void createMM() {
        // Initialize variables
        JFrame mainWindow = new JFrame("The Labyrinth");
        JLabel titleLabel = new JLabel("The Labyrinth", SwingConstants.CENTER);
        JButton nGameB = new JButton("New Game");
        JButton lGameB = new JButton("Load Game");
        JButton achievementsB = new JButton("Achievements");
        JButton settingsB = new JButton("Settings");
        JButton quitB = new JButton("Quit");
        Dimension buttonSize = new Dimension(150, 50);
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set up main screen
        mainWindow.setLayout(new BorderLayout());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0)); // Spacer between title and top of window
        mainWindow.add(titleLabel, BorderLayout.NORTH);
        mainWindow.setSize(800, 600);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Make window non-resizable
        mainWindow.setResizable(false);

        // Set preferred size for buttons
        nGameB.setPreferredSize(buttonSize);
        lGameB.setPreferredSize(buttonSize);
        achievementsB.setPreferredSize(buttonSize);
        settingsB.setPreferredSize(buttonSize);
        quitB.setPreferredSize(buttonSize);

        // Configure GridBagConstraints
        gbc.insets = new Insets(10, 10, 10, 10); // Y pad for buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Add buttons to the buttonPanel
        buttonPanel.add(nGameB, gbc);
        gbc.gridy++;
        buttonPanel.add(lGameB, gbc);
        gbc.gridy++;
        buttonPanel.add(achievementsB, gbc);
        gbc.gridy++;
        buttonPanel.add(settingsB, gbc);
        gbc.gridy++;
        buttonPanel.add(quitB, gbc);

        // Add the buttonPanel to the center of the mainWindow
        mainWindow.add(buttonPanel, BorderLayout.CENTER);

        // Define button actions
        nGameB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        lGameB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        achievementsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        settingsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        quitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // code
            }
        });
        // Make screen visible
        mainWindow.setVisible(true);
    }
}
