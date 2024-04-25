package adventuregame;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class for graph includes, constructor and functions for graph
 */



public class Graph {
    private ArrayList<Node> nodeList;
    private Map<Node, ArrayList<Pair<Choice, Node>>> adjacencyList;
    private Node root;
    private String jsonFilePath;

    /**
     * Represents a node in the graph, containing descriptions, choices, and image path for the game.
     */
    public static class Node implements Serializable {
        @JsonProperty("Description")
        private String Description;

        @JsonProperty("Choices")
        private List<Choice> Choices;

        @JsonProperty("Image_Path")
        private String Image_Path;

        /**
         * Returns the description of the node.
         * @return Node description.
         */
        public String getDescription() {
            return Description;
        }

        /**
         * Returns the list of choices available at this node.
         * @return List of choices.
         */
        public List<Choice> getChoices()
        {
            return Choices;
        }

        /**
         * Returns the image path associated with this node.
         * @return Image path.
         */
        public String getImagePath()
        {
            return Image_Path;
        }

        
        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;

            boolean choicesEqual = true;

            for(int i=0; i < Choices.toArray().length; i++)
            {
                choicesEqual = choicesEqual && Choices.get(i).equals(node.getChoices().get(i));
            }
            return node.getDescription().equals(Description) && choicesEqual;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
//test check//
    /**
     * Constructs a graph from a JSON file path.
     * @param jsonFilePath Path to the JSON file that defines the graph.
     * @throws IOException If there is an error reading from the file.
     */
    public Graph(String jsonFilePath) throws IOException {
        this.jsonFilePath = jsonFilePath;
        initialize();
    }

    /**
     * Retrieves a node from the graph that matches the provided reference node.
     * @param savedNode The node to find in the graph.
     * @return Matching node, or null if not found.
     */
    public Node getNodeFromReference(Node savedNode)
    {
        for(Node n : nodeList)
        {
            if (n.equals(savedNode))
            {
                return n;
            }
        }
        return null;
    }

    /**
     * Returns the node list.
     * @return The nodeList of the graph.
     */
    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    /**
     * Returns the list of adjacent nodes.
     * @return The list of nodes adjacent to the current node.
     */
    public Map<Node, ArrayList<Pair<Choice, Node>>> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Sets the current path in the JSON file to another value.
     * @param jsonFilePath The path to be set to.
     */
    public void setJsonFilePath(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    /**
     * Retrieves the current JSON file path
     * @return The current path within the JSON file
     */
    public String getJsonFilePath() {
        return jsonFilePath;
    }

    /**
     * Initializes the graph by constructing it based on the JSON file.
     * @throws IOException If there is an error processing the JSON file.
     */
    public void initialize() throws IOException {
        adjacencyList = new HashMap<>();
        constructGraph();
    }

    /**
     * Returns root of the node.
     * @return The root of the node.
     */
    public Node getRoot()
    {
        return root;
    }

    /**
     * Converts a file into a string.
     * @param file The file to be converted.
     * @retrun The contents of the file as a string.
     */ 
    private String parseFile(File file) throws IOException {
        StringBuilder fileContents = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContents.append(line).append('\n');
            }
        }
        return fileContents.toString();
    }

    /**
     * Converts the json string into an Array List of nodes.
     * @param json The json that is to be converted.
     * @return the "map" of the nodes as defined by the json.
     */
    private ArrayList<Node> parseGameJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, new TypeReference<ArrayList<Node>>() {});
    }

    /**
     * Creates the graph-based implimentation of the game's decisions
     * @throws IOException if readign from the json causes an error.
     */
    private void constructGraph() throws IOException {
        String gameJSON = parseFile(new File(jsonFilePath));
        nodeList = parseGameJson(gameJSON);

        for (Node n : nodeList) {
            ArrayList<Pair<Choice, Node>> graphConnections = new ArrayList<>();
            for (Choice choice : n.Choices) {
                int index = choice.getChoiceLink();

                if (index == -1) {
                    Pair<Choice, Node> choiceNodePair = new Pair<>(choice, null);
                    graphConnections.add(choiceNodePair);
                    continue;
                }

                Node nodeLink = nodeList.get(index);
                Pair<Choice, Node> choiceNodePair = new Pair<>(choice, nodeLink);
                graphConnections.add(choiceNodePair);

            }
            adjacencyList.put(n, graphConnections);
        }

        root = nodeList.get(0);
    }

    /**
     * Gives the next node in the adjencencyList
     * @param currentNode The current node in the graph.
     * @param choiceIndex The index of the choice leading to the next node.
     * @return The next node.
     */
    public Node nextNode(Node currentNode, int choiceIndex) {
        ArrayList<Pair<Choice, Node>> choices = adjacencyList.get(currentNode);
        if (choiceIndex < 0 || choiceIndex >= choices.size()) {
            throw new IllegalArgumentException("Invalid choice index");
        }
        Pair<Choice, Node> selectedChoice = choices.get(choiceIndex);

        return selectedChoice.second;
    }
}
