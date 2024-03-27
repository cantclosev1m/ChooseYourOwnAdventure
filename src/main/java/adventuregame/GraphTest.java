package adventuregame;

import java.io.IOException;

/**
 * GraphTest
 * Class for testing the graph class
 */

public class GraphTest {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph("game.json");
        myGraph.initialize();
        System.out.println(myGraph);
    }


}
