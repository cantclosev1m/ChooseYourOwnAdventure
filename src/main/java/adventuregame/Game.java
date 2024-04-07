package adventuregame;
import adventuregame.Graph;
import adventuregame.gui.GameMenu;
import adventuregame.util.BindableEvent;
import adventuregame.util.Event;

import java.io.*;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class Game{

    private Graph gameGraph;
    private GameMenu gameMenu;
    private Graph.Node currentNode;
    private String saveFile = "SaveData.txt";

    public Event<Void> onGameEnd = new BindableEvent<>();
    public Consumer<GameMenu.GameButtonClickEvent> buttonClickListener;

    public Game() throws IOException {
        gameGraph = new Graph("game.json");
        gameMenu = new GameMenu();
        Initialize();
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
    }

    private void setMenuInterface()
    {
        // Updates the Menu looks based on the current node
        List<Choice> choices = currentNode.getChoices();
        String[] choiceDescriptions = new String[3];

        for(int i=0; i<3; i++)
            choiceDescriptions[i] = choices.get(i).getDescription();

        gameMenu.massSetButtonListDesc(choiceDescriptions);
        gameMenu.setEventDescription(currentNode.getDescription());
    }

    private void Initialize() throws IOException {
        gameGraph.initialize();
        currentNode = gameGraph.getRoot();
        setMenuInterface();
        gameMenu.setVisibility(true);
        updateGameState();
        initConnections();
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
    }
    //TODO Implement function to check if game should be over.
    public void checkGameOver()
    {

    }


    //TODO SaveGame Function

    public void saveGame()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(currentNode);
        }
        catch (IOException e){
            System.err.println("Error saving the game" + e.getMessage());
        }
    }


    //TODO loadImage Function
    public void loadImage (Graph.Node currentnode)
    {
        //Implementation depends on how we store images
    }

    //TODO loadGame Function
    public void loadGame()
    {
       try{
           FileInputStream fis = new FileInputStream(saveFile);
           ObjectInputStream ois = new ObjectInputStream(fis);
           ois.close();
           fis.close();
           currentNode = (Graph.Node) ois.readObject();
           gameGraph.initialize();

       }
       catch (IOException | ClassNotFoundException e)
       {
           System.err.println("Error loading the game" + e.getMessage());
       }
    }
}
