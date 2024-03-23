package adventuregame;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileReader;
import java.lang.StringBuilder;
import java.util.List;  


import javax.swing.*;
import adventuregame.MainMenu;
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
        // display main menu
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                MainMenu mm = new MainMenu();
                mm.createMM();
                
                /* for testing purposes 
                EventScreen eScreen = new EventScreen();
                eScreen.buildEventScreen();
                */
            }
        });
    }
}
