package ru.codebattle.client.bot.algorithms.astar;

import lombok.Getter;
import lombok.Setter;
import ru.codebattle.client.api.BoardElement;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class Node extends BoardPoint {

    private GameBoard gameBoard;
    @Setter
    @Getter
    private Node pathParent;
    @Getter
    @Setter
    private long g;
    @Setter
    @Getter
    private long f;
    @Getter
    private long heuristic;
    @Getter
    private int weight = Integer.MAX_VALUE;

    public Node(int x, int y, GameBoard gameBoard) {
        super(x, y);
        try {
            weight = BoardElementWeight.valueOf(gameBoard.getElementAt(this).name()).getWeight();
        }catch (IndexOutOfBoundsException e){}
        this.gameBoard = gameBoard;
    }
    public Node(BoardPoint point, GameBoard gameBoard) {
        super(point.getX(), point.getY());
        weight = BoardElementWeight.valueOf(gameBoard.getElementAt(this).name()).getWeight();
        this.gameBoard = gameBoard;
    }


    public long getFinalCost() {
        return g + heuristic;
    }

//
//    public int compareTo(Object other) {
//        int thisValue = heuristic;
//        int otherValue = ((Node)other).getHeuristic();
//        return Integer.compare(thisValue, otherValue);
//    }

    public void setHeuristic(Node end){
        long raw_dist = (long) Math.sqrt(Math.pow(this.getY() - end.getY(), 2) +
                Math.pow(this.getX() - end.getX(), 2));
        heuristic = raw_dist + weight;
    }

    /**
                        Override shifts
     */
    @Override
    public Node shiftLeft() {
        return new Node(getX()-1, getY(), gameBoard);
    }

    @Override
    public Node shiftRight() {
        return new Node(getX()+1, getY(), gameBoard);
    }

    @Override
    public Node shiftTop() {
        return new Node(getX(), getY()+1, gameBoard);
    }

    @Override
    public Node shiftBottom() {
        return new Node(getX(), getY()-1, gameBoard);
    }


    public boolean hasParent(){
        return pathParent != null;
    }

    //TODO:  isOutOfBoard not working ....???
    public List<Node> getNeighbors(){
        List<Node> list = new ArrayList<>();
        Node top = shiftTop();
        Node left = shiftLeft();
        Node bottom = shiftBottom();
        Node right = shiftRight();
        if(left.getWeight() < 10)
            list.add(left);
        if(right.getWeight() < 10)
            list.add(right);
        if(bottom.getWeight() < 10)
            list.add(bottom);
        if(top.getWeight() < 10)
            list.add(top);



        return list;
    }
}