package ru.codebattle.client.bot.algorithms.bestfirst;

import ru.codebattle.client.api.BoardElement;
import ru.codebattle.client.api.BoardPoint;

import java.util.ArrayList;
import java.util.List;

public class NodePoint extends BoardPoint {
    private int x;
    private int y;
    private int path_weight = 0;

    private static List<Integer> visited = new ArrayList<Integer>();

    public NodePoint(int x, int y) {
        super(x, y);
    }

    public int getX() {
        return x;
    }

    public void move(BoardPoint point, BoardElement symbol){
        // Get and add weight
        int weight = BoardElementWeight.valueOf(symbol.name()).getWeight();
        path_weight += weight;

        // Moved Right
        if(x < point.getX()){
            this.x = x;
        }
        // Moved LEFT
        if(x > point.getX()){
            this.x = x;
        }
        // Moved UP
        if(y < point.getX()){
            this.x = y;
        }
        // Moved DOWN
        if(y > point.getY()){
            this.x = y;
        }
    }

    public void addVisited(int markerID){
        visited.add(markerID);
    }

    public void clearVisited(){
        visited.clear();
    }

    public List<Integer> getVisited(){
        return visited;
    }

    public int getY() {
        return y;
    }
}