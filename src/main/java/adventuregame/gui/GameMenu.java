package adventuregame.gui;

import adventuregame.util.BindableEvent;
import adventuregame.util.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameMenu extends JComponent {

    public interface GameButtonClickEvent
    {
        int buttonNumber = 0;
        int getButtonNumber();
    }

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

    private List<JButton> gameButtonList = new ArrayList<>();
    public Event<GameButtonClickEvent> onGameButtonClick = new BindableEvent<GameButtonClickEvent>();
    public Event<Void> onGameSave = new BindableEvent<>();


    public GameMenu()
    {
        setSize(800, 600);
        setLayout(new BorderLayout());

        // set up event screen layout
        leftPanel = new JPanel(new BorderLayout());
        middlePanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel(new BorderLayout());

        // left portion

        leftLabel = new JLabel("       "); // for some space between image and event text
        leftLabel.setBackground(Color.BLACK);
        leftLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(leftLabel, BorderLayout.CENTER);
        leftPanel.setBackground(Color.BLACK);
        leftLabel.setBackground(Color.BLACK);

        // middle portion
        topTextLabel = new JLabel("<html>Event info text...", SwingConstants.CENTER);
        topTextLabel.setForeground(Color.white);
        topTextLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        middlePanel.add(topTextLabel, BorderLayout.CENTER);
        middlePanel.setBackground(Color.BLACK);

        bottomPanel = new JPanel(new GridLayout(3, 1)); // for stacking buttons
        bottomPanel.setBackground(Color.BLACK);

        choice1B = new JButton("Choice 1");
        choice2B = new JButton("Choice 2");
        choice3B = new JButton("Choice 3");

        gameButtonList.add(0, choice1B);
        gameButtonList.add(1, choice2B);
        gameButtonList.add(2, choice3B);


        Dimension buttonSize = new Dimension(150, 50);

        for(int i=0; i < 3; i++)
        {
            int finalI = i;
            JButton button = gameButtonList.get(i);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onGameButtonClick.Fire(new GameButtonClickEvent() {
                        @Override
                        public int getButtonNumber() {
                            return finalI;
                        }
                    });
                }
            });
            button.setPreferredSize(buttonSize);
            bottomPanel.add(button);
        }

        middlePanel.add(bottomPanel, BorderLayout.SOUTH); // center bottom of middle section

        // right portion
        saveB = new JButton("Save (and quit)");
        saveB.setBackground(Color.gray);
        saveB.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
        inventoryB = new JButton("Inventory"); // Add an extra button for demonstration
        inventoryB.setBackground(Color.gray);
        inventoryB.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));

        buttonPanel2 = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonPanel2.add(saveB);
        buttonPanel2.add(inventoryB);
        buttonPanel2.setBackground(Color.black);

        rightPanel.add(buttonPanel2, BorderLayout.NORTH);
        rightPanel.setBackground(Color.BLACK);

        saveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGameSave.Fire();
            }
        });

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

        for(int i =0; i < 3; i++)
        {

            JButton button = gameButtonList.get(i);
            button.setText("<html>" + buttonDescriptions[i]);
            button.setFont(new Font("Tempus Sans ITC", Font.ITALIC, 14));
            button.setBackground(Color.gray);
            button.setToolTipText(buttonDescriptions[i]);
        }
    }

    public void setEventDescription(String description)
    {
        topTextLabel.setText("<html>" + description);
    }


    public void setImage (String imagePath)
    {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(450, 800, Image.SCALE_SMOOTH);//changed dem
        leftLabel.setIcon(new ImageIcon(image));
    }

}

