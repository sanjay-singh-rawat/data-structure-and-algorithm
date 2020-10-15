package com.learndatastructure.interviewcake.treesandgraphs.graphcoloring;

import com.learndatastructure.interviewcake.treesandgraphs.GraphNode;
import com.learndatastructure.utils.JUnitUtils;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Time Complexity : O(N + M) time where N is the number of nodes and M is the number of edges.
 * Space Complexity : O(D), The only thing we're storing is the illegalColors hash set. In the worst case,
 *                      all the neighbors of a node with the maximum degree (D) have different colors, so our hash set takes up O(D) space.
 *
 * @author Sanjay Singh Rawat
 * @since 2020.10.14
 */
public class Solution {

    public static void main(String[] args) {
        JUnitUtils.launch(Solution.class);
    }

    public static void colorGraph(GraphNode[] graph, String[] colors) {
        for (GraphNode node : graph) {
            Set<GraphNode> neighbors = node.getNeighbors();
            if (neighbors.contains(node)) {
                throw new IllegalArgumentException(String.format("Legal coloring impossible for node with loop : %s", node.getLabel()));
            }

            Set<String > illegalColors = new HashSet<>();
            for (GraphNode neighbor : neighbors) {
                if (neighbor.hasColor()) {
                    illegalColors.add(neighbor.getColor());
                }
            }

            for (String color : colors) {
                if (!illegalColors.contains(color)) {
                    node.setColor(color);
                    break;
                }
            }
        }
    }

    // tests

    @Test
    public void lineGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        final GraphNode nodeD = new GraphNode("D");
        nodeA.addNeighbor(nodeB);
        nodeB.addNeighbor(nodeA);
        nodeB.addNeighbor(nodeC);
        nodeC.addNeighbor(nodeB);
        nodeC.addNeighbor(nodeD);
        nodeD.addNeighbor(nodeC);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void separateGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        final GraphNode nodeD = new GraphNode("D");
        nodeA.addNeighbor(nodeB);
        nodeB.addNeighbor(nodeA);
        nodeC.addNeighbor(nodeD);
        nodeD.addNeighbor(nodeC);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void triangleGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        nodeA.addNeighbor(nodeB);
        nodeA.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeA);
        nodeB.addNeighbor(nodeC);
        nodeC.addNeighbor(nodeA);
        nodeC.addNeighbor(nodeB);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void envelopeGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        final GraphNode nodeD = new GraphNode("D");
        final GraphNode nodeE = new GraphNode("E");
        nodeA.addNeighbor(nodeB);
        nodeA.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeA);
        nodeB.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeD);
        nodeB.addNeighbor(nodeE);
        nodeC.addNeighbor(nodeA);
        nodeC.addNeighbor(nodeB);
        nodeC.addNeighbor(nodeD);
        nodeC.addNeighbor(nodeE);
        nodeD.addNeighbor(nodeB);
        nodeD.addNeighbor(nodeC);
        nodeD.addNeighbor(nodeE);
        nodeE.addNeighbor(nodeB);
        nodeE.addNeighbor(nodeC);
        nodeE.addNeighbor(nodeD);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD, nodeE};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void loopGraphTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            final GraphNode nodeA = new GraphNode("A");
            nodeA.addNeighbor(nodeA);
            final GraphNode[] graph = new GraphNode[] {nodeA};
            colorGraph(graph, getColors());
        });
    }

    private static String[] getColors() {
        return new String[] {"red", "green", "blue", "orange", "yellow", "white"};
    }

    private static void validateGraphColoring(GraphNode[] graph) {

        for (final GraphNode node : graph) {
            if (!node.hasColor()) {
                fail(String.format("Found non-colored node %s", node.getLabel()));
            }
        }

        int maxDegree = 0;
        final Set<String> usedColors = new HashSet<>();

        for (final GraphNode node : graph) {
            final Set<GraphNode> neighbors = node.getNeighbors();
            maxDegree = Math.max(maxDegree, neighbors.size());
            usedColors.add(node.getColor());
        }

        final int allowedColorCount = maxDegree + 1;

        if (usedColors.size() > allowedColorCount) {
            fail(String.format("Too many colors: %d allowed, but %d actually used",
                    allowedColorCount, usedColors.size()));
        }

        for (final GraphNode node : graph) {
            final Set<GraphNode> neighbors = node.getNeighbors();
            for (final GraphNode neighbor: neighbors) {
                if (neighbor.getColor().equals(node.getColor())) {
                    fail(String.format("Neighbor nodes %s and %s have the same color %s",
                            node.getLabel(), neighbor.getLabel(), node.getColor()));
                }
            }
        }
    }
}
