package adventuregame;
import adventuregame.Graph;
import adventuregame.gui.EndingMenu;
import adventuregame.gui.GameMenu;
import adventuregame.util.BindableEvent;
import adventuregame.util.Event;
import adventuregame.util.WindowService;

import java.io.*;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import java.awt.Point;


/**
* The main class responsible for managing the game logic and interactions in the adventure game.
*/
public class Game{
    /**
     * Interface to handle events when the game ends, providing access to the saved game node.
     */
    public interface onGameEndEvent
    {
        Graph.Node getSavedNode();
    }

    private Graph gameGraph;
    private GameMenu gameMenu;
    private EndingMenu endMenu;
    private Graph.Node currentNode;
    private WindowService windowingService;

    public Event<onGameEndEvent> onGameEnd = new BindableEvent<onGameEndEvent>();
    public Consumer<GameMenu.GameButtonClickEvent> buttonClickListener;
    /**
     * Constructor for Game class to initiate a new game session.
     * @param windowService The service for managing GUI window components.
     * @param eMenu The menu to be displayed at the end of the game.
     * @throws IOException If there is an error in reading graph configuration.
     */
    public Game(WindowService windowService, EndingMenu eMenu) throws IOException {
        windowingService = windowService;
        endMenu = eMenu;
 
        gameMenu = new GameMenu();
        windowingService.registerComponent(gameMenu);
        windowService.activateComponent(gameMenu);

        gameGraph = new Graph("game.json");
        gameGraph.initialize();
        currentNode = gameGraph.getRoot();

        setMenuInterface();

        gameMenu.setVisibility(true);

        updateGameState();
        initConnections();
    }
    /**
     * Constructor for loading a game session from saved data.
     * @param windowService The service for managing GUI window components.
     * @param saveDataFile The file path where game data is saved.
     * @param eMenu The menu to be displayed at the end of the game.
     */
    public Game(WindowService windowService, String saveDataFile, EndingMenu eMenu)
    {
        try{
            windowingService = windowService;
            endMenu = eMenu;

            FileInputStream fis = new FileInputStream(saveDataFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Graph.Node currentNodeReference = (Graph.Node) ois.readObject();
            ois.close();
            fis.close();

            gameGraph = new Graph("game.json");
            gameMenu = new GameMenu();
            windowingService.registerComponent(gameMenu);
            windowService.activateComponent(gameMenu);

            gameGraph.initialize();

            currentNode  = gameGraph.getNodeFromReference(currentNodeReference);
            //System.out.println(currentNode);

            setMenuInterface();
            gameMenu.setVisibility(true);
            updateGameState();
            initConnections();
            checkGameOver(currentNode);
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.err.println("Error loading the game" + e.getMessage());
        }
    }
    /**
     * Initializes connections and event handlers for game button clicks and game save events.
     */
    private void initConnections()
    {
        buttonClickListener = new Consumer<GameMenu.GameButtonClickEvent>() {
            @Override
            public void accept(GameMenu.GameButtonClickEvent gameButtonClickEvent) {
                updateGraphNode(gameButtonClickEvent.getButtonNumber());
                setMenuInterface();
            }
        };
        gameMenu.onGameButtonClick.Connect(buttonClickListener);
        gameMenu.onGameSave.Connect((Void) -> {
            onGameEnd.Fire(new onGameEndEvent() {
                @Override
                public Graph.Node getSavedNode() {
                    return currentNode;
                }
            });
        });
        endMenu.onGameButtonClick.Connect(buttonClickListener);
        endMenu.onGameSave.Connect((Void) -> {
            onGameEnd.Fire(new onGameEndEvent() {
                @Override
                public Graph.Node getSavedNode() {
                    return currentNode;
                }
            });
        });

    }
    /**
     * Sets up the menu interface based on the current game state.
     */
    private void setMenuInterface()
    {
        // Updates the Menu looks based on the current node
        if(currentNode == null) { return; } // stop updating choices if end node is reached
        List<Choice> choices = currentNode.getChoices();
        String[] choiceDescriptions = new String[3];

        for(int i=0; i<3; i++)
            choiceDescriptions[i] = choices.get(i).getDescription();

        gameMenu.massSetButtonListDesc(choiceDescriptions);
        gameMenu.setEventDescription(currentNode.getDescription());
        loadImage(currentNode);
    }

    /**
     * getNextDescription
     * function to return the description of the next node (Not even sure if it's useful).
     * @param gameGraph
     * @param currentNode
     * @param choiceIndex
     * @return
     */
     public String getNextDescription(Graph gameGraph, Graph.Node currentNode, int choiceIndex)
    {
        return gameGraph.nextNode(currentNode, choiceIndex).getDescription();
    }

    /**
    *Unimplemented method that would progress the game into the next choice.
    */
    public void updateGameState()
    {

    }

    /**
     * updateGraphNode
     * Function to update game position (will need alterations to account for UI implementation)
     * @param choiceIndex
     */
    public void updateGraphNode(int choiceIndex)
    {
        Graph.Node nextNode = gameGraph.nextNode(currentNode, choiceIndex);
        checkGameOver(nextNode);
        currentNode = nextNode;
        //checkGameOver(currentNode);
    }
    /**
     * checkGameOver
     * Function to check if game is completed by checking for empty description
     * @param n Node to be checked
     */
    public void checkGameOver(Graph.Node n)
    {
        List<Choice> choices = n.getChoices();
        String checkDesc = choices.get(0).getDescription();
        endMenu.endNode = n;
        
        if(checkDesc.isEmpty())
        {
            endMenu.create();
            windowingService.registerComponent(endMenu);
            windowingService.activateComponent(endMenu);
        }
    }
    /**
    *Loads the image related to the current node.
    *@param currentNode The node the player is currently in.
    */
    public void loadImage(Graph.Node currentNode) {
        String imagePath = currentNode.getImagePath();
        if (imagePath != null && !imagePath.isEmpty()) {
            gameMenu.setImage(imagePath);
        } else {
            gameMenu.setImage(null);
        }
    }
}
