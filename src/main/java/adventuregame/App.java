package adventuregame;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


import javax.swing.*;
import adventuregame.gui.MainMenu;
import adventuregame.gui.EndingMenu;
import adventuregame.util.WindowService;
import adventuregame.util.Event;
/**
 * Hello world!
 *
 */

/**
 * The main application class for an adventure game.
 * This class sets up the game environment, handles the game lifecycle, including starting, saving, and loading games.
 */
public class App
{
    /**
     * Function for initializing new instance of game
     */

    private String saveFile = "saveFile.txt";
    private WindowService windowingService;
    private MainMenu mainMenu;
    private EndingMenu endMenu;

    /**
    *Sets the default theme for the program
    *@throws UnsupportedLookAndFeelException When the theme is unable to be accepted
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
    /**
    *Constructor for the App class which initializes the UI components and services.
    *@throws UnsupportedLookAndFeelException If the specified look and feel cannot be used
    */
    public App() throws UnsupportedLookAndFeelException {
        setDefaultTheme();
        mainMenu = new MainMenu();
        endMenu = new EndingMenu();
        windowingService = new WindowService();
        windowingService.registerComponent(mainMenu);
        startApp();
    }
    /**
    * Starts the application by setting up the main menu and its interactions.
    */
    public void startApp()
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                windowingService.activateComponent(mainMenu);
                
                mainMenu.onMenuInit.Connect((Void) -> {
                    System.out.println("Hello from the menu");
                });

                // mm.setActive();

                mainMenu.onGameStart.Connect((Void) -> {
                    System.out.println("A new game has started");
                    // mm.unActivate();
                    try {
                        startGame();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                mainMenu.onGameLoad.Connect((Void) -> {
                    System.out.println("A new game has been loaded");
                    //  mm.unActivate();
                    try {
                        loadGame();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                
                endMenu.playAgain.Connect((Void) -> {
                    try {
                        Event<Void> tempPlayAgain = endMenu.playAgain;
                        Event<Void> tempGTMM = endMenu.goToMM;
                        
                        endMenu = null;
                        endMenu = new EndingMenu(tempPlayAgain, tempGTMM);
                        startGame();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                endMenu.goToMM.Connect((Void) -> {
                    windowingService.activateComponent(mainMenu);
                });
                
            }
        });
    }
    /**
    *Starts a new game session.
    *@throws IOException If an input/output error occurs
    */
    public void startGame() throws IOException {
        Game game = new Game(windowingService, endMenu);
        game.onGameEnd.Connect(onGameEndEvent -> {
            saveGame(onGameEndEvent.getSavedNode());
        });
    }

    /**
     * Function responsible for ending game instance
     */
    public void endGame()
    {

    }

    /**
     * Function responsible for loading previous instance of game
     *@throws IoException if file I/O encounters error 
     */
    public void loadGame() throws IOException {
        Game game = new Game(windowingService, saveFile, endMenu);
        game.onGameEnd.Connect(onGameEndEvent -> {
            saveGame(onGameEndEvent.getSavedNode());
        });
    }

    /**
     * Function responsible for saving game instance
     * @param savedNode Position of game to be saved
     */
    public void saveGame(Graph.Node savedNode)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(savedNode);
        }
        catch (IOException e){
            System.err.println("Error saving the game" + e.getMessage());
        }
        System.exit(0);
    }
    /**
    * Main entry point for the application.
    * @param args Command line arguments (not used)
    * @throws Exception If an error occurs during initialization
    */
    public static void main( String[] args ) throws Exception
    {
        App application = new App();
    }
}
