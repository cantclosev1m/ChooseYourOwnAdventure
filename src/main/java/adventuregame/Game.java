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

public class Game{

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

    public Game(WindowService windowService) throws IOException {
        windowingService = windowService;

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

    public Game(WindowService windowService, String saveDataFile)
    {
        try{
            windowingService = windowService;

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

            setMenuInterface();
            gameMenu.setVisibility(true);
            updateGameState();
            initConnections();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.err.println("Error loading the game" + e.getMessage());
        }
    }

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

    }

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


    public void updateGameState()
    {

    }

    /**
     * updateGraphNode
     * Function to update game position (will need alterations to account for UI implementation)
     * @param choiceIndex
     * @return
     */
    public void updateGraphNode(int choiceIndex)
    {
        Graph.Node nextNode = gameGraph.nextNode(currentNode, choiceIndex);
        currentNode = nextNode;
        checkGameOver(currentNode);
    }
    /**
     * checkGameOver
     * Function to check if game is completed
     * @param n
     * @return
     */
    public void checkGameOver(Graph.Node n)
    {
        // check if node is null (no more node links)
        if(n == null)
        {
            endMenu = new EndingMenu();
            windowingService.registerComponent(endMenu);
            windowingService.activateComponent(endMenu);
        }
    }

    public void loadImage(Graph.Node currentNode) {
        String imagePath = currentNode.getImagePath();
        if (imagePath != null && !imagePath.isEmpty()) {
            gameMenu.setImage(imagePath);
        } else {
            gameMenu.setImage(null);
        }
    }
}
