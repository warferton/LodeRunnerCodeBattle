package ru.codebattle.client.bot.algorithms.astar;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

import java.util.*;

@Slf4j
public class AStarSearch {
    @Setter
    private static GameBoard gameBoard;
    private static PriorityQueue<Node> openSet = new PriorityQueue<Node>(new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            BoardPoint p1 = (BoardPoint) o1;
            BoardPoint p2 = (BoardPoint) o2;
            int heuristic_1 = BoardElementWeight.valueOf(gameBoard.getElementAt(p1).name()).getWeight();
            int heuristic_2 = BoardElementWeight.valueOf(gameBoard.getElementAt(p2).name()).getWeight();
            return Integer.compare(heuristic_1,heuristic_2);
        }
    });
    private static ArrayList<Node> closedSet = new ArrayList<>();
    private static LinkedList<Node> path;
    private final static int COUNTER = 1;
    public static LinkedList<Node> findPath(GameBoard gameBoard, Node start, Node end){
        int board_size = gameBoard.size();
        int loopCounter = COUNTER;
        setGameBoard(gameBoard);
        openSet.add(start);
        while (openSet.size() > 0 && loopCounter > 0) {
            Node cur = openSet.poll();
            if (cur.equals(end)) {
                log.info("FOUND! : " + end.toString());
                //construct path
                return constructPath(cur);
            }
            closedSet.add(cur);

            List<Node> neighbours = cur.getNeighbors();

            for (Node node : neighbours) {
                if (closedSet.contains(node) || node.isOutOfBoard(board_size) || node.getWeight() > 10)
                    continue;

                long tempG = cur.getG() + 1;
                boolean newPath = false;
                if (openSet.contains(node)) {
                    if (tempG < node.getG()) {
                        node.setG(tempG);
                        newPath = true;
                    }
                } else {
                    node.setG(tempG);
                    openSet.add(node);
                    newPath = true;
                }
                if (newPath) {
                    node.setHeuristic(end);
                    node.setF(node.getFinalCost());
                    node.setPathParent(cur);
                }
            }
            path = constructPath(cur);
            loopCounter--;
        }

        log.error("NO CORRECT PATH FOUND");
        log.info("Final Path: " + path.toString());
        return path;
    }

    private static LinkedList<Node> constructPath(Node cur){
        path = new LinkedList<>();
        Node temp = cur;
        path.push(temp);
        while(temp.hasParent()){
            path.push(temp.getPathParent());
            temp = temp.getPathParent();
        }
        log.info("PATH CONSTRUCTED: " + path.toString());
        return path;
    }

}
