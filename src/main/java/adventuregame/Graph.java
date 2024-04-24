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

    /**m
     * Node class implementation
     * <<Subject to change>>
     */
    public static class Node implements Serializable {
        @JsonProperty("Description")
        private String Description;

        @JsonProperty("Choices")
        private List<Choice> Choices;

        @JsonProperty("Image_Path")
        private String Image_Path;

        public String getDescription() {
            return Description;
        }

        public List<Choice> getChoices()
        {
            return Choices;
        }

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
     * Constructor for the Graph class
     */
    public Graph(String jsonFilePath) throws IOException {
        this.jsonFilePath = jsonFilePath;
        initialize();
    }

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

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public Map<Node, ArrayList<Pair<Choice, Node>>> getAdjacencyList() {
        return adjacencyList;
    }

    public void setJsonFilePath(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public String getJsonFilePath() {
        return jsonFilePath;
    }

    public void initialize() throws IOException {
        adjacencyList = new HashMap<>();
        constructGraph();
    }

    public Node getRoot()
    {
        return root;
    }

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

    private ArrayList<Node> parseGameJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, new TypeReference<ArrayList<Node>>() {});
    }

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
     */
    public Node nextNode(Node currentNode, int choiceIndex) {
        ArrayList<Pair<Choice, Node>> choices = adjacencyList.get(currentNode);
        if (choiceIndex < 0 || choiceIndex >= choices.size()) {
            throw new IllegalArgumentException("Invalid choice index");
        }
        Pair<Choice, Node> selectedChoice = choices.get(choiceIndex);

        return selectedChoice.second;
    }

    public static void main(String[] args) {
        try {
            Graph myGraph = new Graph("game.json");
            myGraph.initialize();  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
