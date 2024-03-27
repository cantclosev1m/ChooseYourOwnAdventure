package adventuregame;
import java.awt.*;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileReader;
import java.lang.StringBuilder;
import java.util.List;  


import javax.swing.*;
import adventuregame.gui.MainMenu;
import adventuregame.EventScreen;

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
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.border", BorderFactory.createLineBorder(Color.WHITE));

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


    public void startGame()
    {
        
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
                });


                /* for testing purposes 
                EventScreen eScreen = new EventScreen();
                eScreen.buildEventScreen();
                */
            }
        });
    }
}
