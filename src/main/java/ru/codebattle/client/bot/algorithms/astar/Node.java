package ru.codebattle.client.bot.algorithms.astar;

import lombok.Getter;
import lombok.Setter;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.bot.algorithms.bestfirst.BoardElementWeight;

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
    private int weight;

    public Node(int x, int y, GameBoard gameBoard) {
        super(x, y);
        weight = BoardElementWeight.valueOf(gameBoard.getElementAt(this).name()).getWeight();
        this.gameBoard = gameBoard;
    }
    public Node(BoardPoint point, GameBoard gameBoard) {
        super(point.getX(), point.getY());
        weight = BoardElementWeight.valueOf(gameBoard.getElementAt(this).name()).getWeight();
        this.gameBoard = gameBoard;
    }


    public float getFinalCost() {
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
        if (!gameBoard.hasBarrierAt(top) && gameBoard.hasLadderAt(top))
            list.add(top);
        if (!gameBoard.hasBarrierAt(left))
            list.add(left);
        if (!gameBoard.hasBarrierAt(bottom))
            list.add(bottom);
        if (!gameBoard.hasBarrierAt(right))
            list.add(right);
        return list;
    }
}