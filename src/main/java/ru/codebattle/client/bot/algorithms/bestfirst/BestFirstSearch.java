package ru.codebattle.client.bot.algorithms.bestfirst;

import ru.codebattle.client.api.BoardElement;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

public class BestFirstSearch {
    GameBoard gameBoard;


    public void pathToGold(BoardPoint myCurPos, BoardPoint p2) {
        NodePoint curPos = (NodePoint) myCurPos;

        double curDist = distance(curPos, p2);
        while (distance(curPos, p2) != 0) {
            if (curPos.getY() != 0 && !curPos.getVisited().contains(0)) { // Check down direction
                BoardPoint point_down = curPos.shiftBottom();
                curPos.move(point_down, gameBoard.getElementAt(point_down));
                if (distance(curPos, p2) < curDist) {
                    System.out.println("UP");
                    curDist = distance(curPos, p2);
                    curPos.clearVisited();
                    continue;
                } else  {
                    curPos.setY(curPos.getY() + 1); // Revert
                    curPos.addVisited(0);
                    curDist = distance(curPos, p2);
                }
            } else if (!curPos.getVisited().contains(1)) { // Check up direction
                BoardPoint point_up = curPos.shiftTop();
                curPos.move(point_up, gameBoard.getElementAt(point_up));
                if (distance(curPos, p2) < curDist) {
                    System.out.println("DOWN");
                    curDist = distance(curPos, p2);
                    curPos.clearVisited();
                    continue;
                } else  {
                    curPos.setY(curPos.getY() - 1); // Revert
                    curDist = distance(curPos, p2);
                    curPos.addVisited(1);
                }

            } else if (curPos.getX() != 0 && !curPos.getVisited().contains(2)) { // Check left direction
                BoardPoint point_left = curPos.shiftLeft();
                curPos.move(point_left, gameBoard.getElementAt(point_left));
                if (distance(curPos, p2) < curDist) {
                    System.out.println("LEFT");
                    curDist = distance(curPos, p2);
                    curPos.clearVisited();
                    continue;
                } else  {
                    curPos.setX(curPos.getX() + 1); // Revert
                    curDist = distance(curPos, p2);
                    curPos.addVisited(2);
                }
            }
            else if (!curPos.getVisited().contains(3)) { // Check right direction
                BoardPoint point_right = curPos.shiftRight();
                curPos.move(point_right, gameBoard.getElementAt(point_right));
                if (distance(curPos, p2) < curDist) {
                    System.out.println("RIGHT");
                    curDist = distance(curPos, p2);
                    curPos.clearVisited();
                    continue;
                }
                else {
                    curPos.setX(curPos.getX() - 1);
                }
            }
        }
    }

    public double distance(BoardPoint p1, BoardPoint p2) {
        int x_1 = p1.getX();
        int y_1 = p1.getY();
        int x_2 = p2.getX();
        int y_2 = p2.getY();

        return Math.sqrt(Math.pow((x_2 - x_1), 2) + Math.pow((y_2 - y_1), 2));
    }

    //TODO: ????
    public double weightedDistance(BoardPoint p1, BoardPoint p2){
        double normal_distance = distance(p1, p2);
        double weighted_distance = normal_distance ;//+ BoardElementWeight.valueOf();
        return weighted_distance;
    }
}
/*
    TODO:  1. Rewrite to work with cur project's classes and structure
           2. Implement weights (e.g., GOLD > NONE, LADDER > BRICK) / Probably through 'distance method'
           3. Integrate with Tracking classes


 */