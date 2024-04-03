package adventuregame;
import java.awt.*;
import java.io.IOException;


import javax.swing.*;
import adventuregame.gui.MainMenu;

/**
 * Hello world!
 *
 */
public class App
{
    /**
     * Function for initializing new instance of game
     */

    private static void setDefaultTheme() throws UnsupportedLookAndFeelException {

        // Should be separated from App class, but oh well

        UIManager.put("nimbusBase", new Color(255, 255, 255));
        UIManager.put("nimbusBlueGrey", new Color(255, 255, 255));
        UIManager.put("control", new Color(255, 255, 255));

        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.border", BorderFactory.createLineBorder(Color.BLACK));

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }


    public void startGame() throws IOException {
        Game game = new Game();
    }

    /**
     * Function responsible for ending game instance
     */
    public void endGame()
    {

    }

    /**
     * Function responsible for loading previous instance of game
     */
    public void loadGame()
    {

    }

    /**
     * Function responsible for saving game instance
     */
    public void saveGame()
    {

    }

    public static void main( String[] args ) throws Exception
    {
        setDefaultTheme();

        App application = new App();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenu mm = new MainMenu();

                mm.onMenuInit.Connect((Void) -> {
                    System.out.println("Hello from the menu");
                });

                mm.setActive();

                mm.onGameStart.Connect((Void) -> {
                    System.out.println("A new game has started");
                    mm.unActivate();
                    try {
                        application.startGame();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            }
        });
    }
}