package ru.codebattle.client.bot.algorithms.bestfirst;

import ru.codebattle.client.api.BoardPoint;

import java.util.ArrayList;
import java.util.List;

public class BestFirstSearch {

    public static double distance(BoardPoint p1, BoardPoint p2) {
        int a = p1.getX();
        int b = p1.getY();
        int c = p2.getX();
        int d = p2.getY();

        return Math.sqrt(Math.pow((c - a), 2) + Math.pow((d - b), 2));
    }

    public static void pathToGold(BoardPoint myCurPos, BoardPoint p2) {
        NodePoint currentPoint = (NodePoint) myCurPos;

        double currentDistance = distance(myCurPos, p2);
        while (distance(myCurPos, p2) != 0) {
            if (currentPoint.getY() != 0 && !currentPoint.getVisited().contains(0)) { // Check up direction
                currentPoint.setY(currentPoint.getY() - 1);
                if (distance(currentPoint, p2) < currentDistance) {
                    System.out.println("UP");
                    currentDistance = distance(currentPoint, p2);
                    currentPoint.clearVisited();
                    continue;
                } else  {
                    currentPoint.setY(currentPoint.getY() + 1); // Revert
                    currentPoint.addVisited(0);
                    currentDistance = distance(currentPoint, p2);
                }
            } else if (!currentPoint.getVisited().contains(1)) { // Check down direction
                currentPoint.setY(currentPoint.getY() + 1);
                if (distance(currentPoint, p2) < currentDistance) {
                    System.out.println("DOWN");
                    currentDistance = distance(currentPoint, p2);
                    currentPoint.clearVisited();
                    continue;
                } else  {
                    currentPoint.setY(currentPoint.getY() - 1); // Revert
                    currentDistance = distance(currentPoint, p2);
                    currentPoint.addVisited(1);
                }

            } else if (currentPoint.getX() != 0 && !currentPoint.getVisited().contains(2)) { // Check left direction
                currentPoint.setX(currentPoint.getX() - 1);
                if (distance(currentPoint, p2) < currentDistance) {
                    System.out.println("LEFT");
                    currentDistance = distance(currentPoint, p2);
                    currentPoint.clearVisited();
                    continue;
                } else  {
                    currentPoint.setX(currentPoint.getX() + 1); // Revert
                    currentDistance = distance(currentPoint, p2);
                    currentPoint.addVisited(2);
                }
            }
            else if ( !currentPoint.getVisited().contains(3)) { // Check right direction
                currentPoint.setX(currentPoint.getX() + 1);
                if (distance(currentPoint, p2) < currentDistance) {
                    System.out.println("RIGHT");
                    currentDistance = distance(currentPoint, p2);
                    currentPoint.clearVisited();
                    continue;
                }
                else {
                    currentPoint.setX(currentPoint.getX() - 1);
                }
            }
        }
    }
}
/*
    TODO:  1. Rewrite to work with cur project's classes and structure
           2. Implement weights (e.g., GOLD > NONE, LADDER > BRICK) / Probably through 'distance method'
           3. Integrate with Tracking classes


 */