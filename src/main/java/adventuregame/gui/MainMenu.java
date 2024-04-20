package adventuregame.gui;

import adventuregame.util.BindableEvent;
import adventuregame.util.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JComponent {

    private JPanel headerPanel;
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

    public MainMenu() {
        // Initialize variables
        headerPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("<html>ChooseYourOwnAdventure", SwingConstants.CENTER);
        nGameB = new JButton("New Game");
        lGameB = new JButton("Load Game");
        achievementsB = new JButton("Achievements");
        settingsB = new JButton("Settings");
        quitB = new JButton("Quit");
        buttonSize = new Dimension(150, 50);
        buttonPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        // Set up headerPanel
        headerPanel.setBackground(Color.BLACK);
        titleLabel.setFont(new Font("Book Antiqua", Font.BOLD, 500));
        titleLabel.setForeground(Color.MAGENTA);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        JFrame f= new JFrame();//adding JPanel to JFrame
        f.add(headerPanel);
        f.setVisible(true);
        add(headerPanel, BorderLayout.NORTH);

        // Set up main screen
        setLayout(new BorderLayout());

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

        // Add the buttonPanel to the center of the MainMenu
        add(buttonPanel, BorderLayout.CENTER);
    }
}
