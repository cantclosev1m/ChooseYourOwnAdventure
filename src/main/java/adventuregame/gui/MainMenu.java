package adventuregame.gui;

import adventuregame.util.BindableEvent;
import adventuregame.util.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
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
        // Set up main screen
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        headerPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("The Labyrinth", SwingConstants.CENTER);
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
        titleLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        headerPanel.add(titleLabel, BorderLayout.NORTH);



        add(headerPanel, BorderLayout.NORTH);



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
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(nGameB, gbc);
        nGameB.setBackground(Color.gray);
        nGameB.setFont(new Font("Tempus Sans ITC", Font.ITALIC, 18));
        gbc.gridy++;

        buttonPanel.add(lGameB, gbc);
        lGameB.setBackground(Color.gray);
        lGameB.setFont(new Font("Tempus Sans ITC", Font.ITALIC, 18));
        gbc.gridy++;

        buttonPanel.add(achievementsB, gbc);
        achievementsB.setBackground(Color.gray);
        achievementsB.setFont(new Font("Tempus Sans ITC", Font.ITALIC, 18));
        gbc.gridy++;

        buttonPanel.add(settingsB, gbc);
        settingsB.setBackground(Color.gray);
        settingsB.setFont(new Font("Tempus Sans ITC", Font.ITALIC, 18));
        gbc.gridy++;
        buttonPanel.add(quitB, gbc);
        quitB.setBackground(Color.gray);
        quitB.setFont(new Font("Tempus Sans ITC", Font.ITALIC, 18));



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
        add(buttonPanel);


    }
}
