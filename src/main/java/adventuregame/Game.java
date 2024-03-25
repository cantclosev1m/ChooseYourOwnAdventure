package adventuregame;
import adventuregame.Graph;

public class Game{


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
