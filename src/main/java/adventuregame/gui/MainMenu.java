package adventuregame.gui;

import adventuregame.util.BindableEvent;
import adventuregame.util.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu {

    private boolean isActive;

    private JFrame mainWindow;
    private JLabel titleLabel;
    private JButton nGameB;
    private JButton lGameB;
    private JButton achievementsB;
    private JButton settingsB;
    private JButton quitB;
    private Dimension buttonSize;
    private JPanel buttonPanel;
    private GridBagConstraints gbc;

    // Events
    public Event<Void> onGameStart = new BindableEvent<>();
    public Event<Void> onMenuInit = new BindableEvent<>();
    public Event<Void> onGameLoad = new BindableEvent<>();


    // Event Connections //

    public void setActive()
    {
        isActive = true;
        mainWindow.setVisible(true);
        onMenuInit.Fire();
    }

    public void unActivate()
    {
        isActive = false;
        mainWindow.setVisible(false);
    }


    public MainMenu()
    {
        // Initialize variables
        mainWindow = new JFrame("The Labyrinth");
        titleLabel = new JLabel("The Labyrinth", SwingConstants.CENTER);
        nGameB = new JButton("New Game");
        lGameB = new JButton("Load Game");
        achievementsB = new JButton("Achievements");
        settingsB = new JButton("Settings");
        quitB = new JButton("Quit");
        buttonSize = new Dimension(150, 50);
        buttonPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        // Set up main screen
        
       
        
        mainWindow.setLayout(new BorderLayout());
        titleLabel.setFont(new Font("Book Antiqua", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);//changed text to white
        titleLabel.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0)); // Spacer between title and top of window
        mainWindow.add(titleLabel, BorderLayout.NORTH);
        mainWindow.setSize(800, 600);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.getContentPane().setBackground(Color.BLACK);//changed (some background to black)
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

        nGameB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGameStart.Fire();
            }
        });

        lGameB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGameLoad.Fire();
            }
        });

        // Add the buttonPanel to the center of the mainWindow
        mainWindow.add(buttonPanel, BorderLayout.CENTER);
    }

    private void onNewGameClicked()
    {

    }

    private void onLoadGameClicked()
    {

    }

    private void onQuitClicked()
    {

    }

    private void onSettingsClicked()
    {

    }
}
