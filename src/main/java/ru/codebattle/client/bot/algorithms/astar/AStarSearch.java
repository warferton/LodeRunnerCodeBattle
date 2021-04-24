package ru.codebattle.client.bot.algorithms.astar;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.bot.algorithms.bestfirst.BoardElementWeight;

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

    public static LinkedList<Node> cunstructPath(GameBoard gameBoard, Node start, Node end){
        setGameBoard(gameBoard);
        openSet.add(start);
        while(openSet.size() > 0){
            Node cur = openSet.poll();
            if(cur.equals(end)){
                log.info("FOUND! : " + end.toString());
                //construct path
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
            closedSet.add(cur);

            List<Node> neighbours = cur.getNeighbors();

            for(Node node : neighbours){
                if(closedSet.contains(node))
                    continue;

                long tempG = cur.getG() + 1 ;

                if(openSet.contains(node)){
                    if(tempG < node.getG())
                        node.setG(tempG);
                }
                else{
                    node.setG(tempG);
                    openSet.add(node);
                }

                node.setHeuristic(end);
                node.setF(node.getG() + node.getHeuristic());
                node.setPathParent(cur);
            }

        }
        log.error("NO PATH FOUND");
        return path;
    }


}