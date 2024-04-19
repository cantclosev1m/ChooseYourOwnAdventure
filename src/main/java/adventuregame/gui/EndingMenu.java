package adventuregame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndingMenu extends JComponent {
    private JPanel panel;
    private JLabel endgameLabel;
    private JButton mmButton; // mm = main menu
    private JButton exitButton;

    public EndingMenu() {
        // organization
        setSize(800, 600);
        setLayout(new BorderLayout());
        panel = new JPanel(new GridBagLayout()); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0); // vertical spacer
        gbc.anchor = GridBagConstraints.CENTER;

        endgameLabel = new JLabel("<html>Game Over<br>This should be updated based on if the player won or not.<br>Maybe have a custom message based on what happens</html>");
        endgameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        // end game button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // go back to main menu button
        mmButton = new JButton("Main Menu");
        mmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
        
        // more organization
        panel.add(endgameLabel, gbc);
        gbc.gridy++;
        panel.add(mmButton, gbc);
        gbc.gridy++;
        panel.add(exitButton, gbc);
        add(panel, BorderLayout.CENTER);
    }

    public void setVisibility(boolean b) {
       // setLocation(coord); // make new window in the same location as game menu screen
        setVisible(b);
    }
    
    public static void main(String[] args) {
        EndingMenu m = new EndingMenu();
      //  m.setVisibility(true, new Point(100, 100));
    }
}
