package si2024.daniellinfonyealu.p04.astar;

import java.util.*;

/**
 * A Star Algorithm
 *
 * @author Marcelo Surriabre
 * @version 2.1, 2017-02-23
 */
	
public class AStar {
    private static final int DEFAULT_HV_COST = 1; // Costo horizontal y vertical
    private final int hvCost;
    private final Node[][] searchArea;
    private final Set<Node> openSet;
    private final Set<Node> closedSet;
    private Node initialNode;
    private Node finalNode;

    public AStar(int rows, int cols, Node initialNode, Node finalNode, int hvCost) {
        this.hvCost = hvCost;
        setInitialNode(initialNode);
        setFinalNode(finalNode);
        this.searchArea = new Node[rows][cols];
        setNodes();
        this.openSet = new HashSet<>();
        this.closedSet = new HashSet<>();
    }

    public AStar(int rows, int cols, Node initialNode, Node finalNode) {
        this(rows, cols, initialNode, finalNode, DEFAULT_HV_COST);
    }

    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(getFinalNode());
                this.searchArea[i][j] = node;
            }
        }
    }

    public List<Node> findPath() {
        openSet.add(initialNode);
        while (!openSet.isEmpty()) {
            Node currentNode = getLowestFScoreNode(openSet);
            openSet.remove(currentNode);
            closedSet.add(currentNode);

            if (currentNode.equals(finalNode)) {
                return getPath(currentNode);
            }

            List<Node> neighbors = getNeighbors(currentNode);
            for (Node neighbor : neighbors) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = currentNode.getG() + getDistance(currentNode, neighbor);
                if (!openSet.contains(neighbor) || tentativeGScore < neighbor.getG()) {
                    neighbor.setParent(currentNode);
                    neighbor.setG(tentativeGScore);
                    neighbor.calculateFinalCost();

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private Node getLowestFScoreNode(Set<Node> nodes) {
        return nodes.stream().min(Comparator.comparingInt(Node::getF)).orElse(null);
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int row = node.getRow();
        int col = node.getCol();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isValidLocation(newRow, newCol) && !searchArea[newRow][newCol].isBlock()) {
                neighbors.add(searchArea[newRow][newCol]);
            }
        }
        return neighbors;
    }

    private int getDistance(Node from, Node to) {
        return Math.abs(from.getRow() - to.getRow()) + Math.abs(from.getCol() - to.getCol());
    }

    private boolean isValidLocation(int row, int col) {
        return row >= 0 && row < searchArea.length && col >= 0 && col < searchArea[0].length;
    }

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    public void setBlocks(int[][] blocksArray) {
        for (int[] block : blocksArray) {
            int row = block[0];
            int col = block[1];
            setBlock(row, col);
        }
    }

    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public Node[][] getSearchArea() {
        return searchArea;
    }

    public int getHvCost() {
        return hvCost;
    }
}
