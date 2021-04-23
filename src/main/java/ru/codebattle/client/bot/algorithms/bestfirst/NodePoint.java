package ru.codebattle.client.bot.algorithms.bestfirst;

import ru.codebattle.client.api.BoardPoint;

import java.util.ArrayList;
import java.util.List;

public class NodePoint extends BoardPoint {
    private int x;
    private int y;

    private static List<Integer> visited = new ArrayList<Integer>();

    public NodePoint(int x, int y) {
        super(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
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