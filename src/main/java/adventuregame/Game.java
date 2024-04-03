package adventuregame;
import adventuregame.Graph;
import adventuregame.gui.GameMenu;
import adventuregame.util.BindableEvent;
import adventuregame.util.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game{

    private Graph gameGraph;
    private GameMenu gameMenu;
    private Graph.Node currentNode;

    public Event<Void> onGameEnd = new BindableEvent<>();

    public Game() throws IOException {
        gameGraph = new Graph("game.json");
        gameMenu = new GameMenu();
        Initialize();
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
     * updateGraphNode
     * Function to update game position (will need alterations to account for UI implementation)
     * @param gameGraph
     * @param currentNode
     * @param choiceIndex
     * @return
     */
    public Graph.Node updateGraphNode(Graph gameGraph, Graph.Node currentNode, int choiceIndex)
    {
        Graph.Node nextNode = gameGraph.nextNode(currentNode, choiceIndex);
        currentNode = nextNode;
        return currentNode;
    }
    //TODO Implement function to check if game should be over.
    public void checkGameOver()
    {

    }
}
