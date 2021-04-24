package ru.codebattle.client.bot.trackers;

import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

import java.util.Comparator;
import java.util.List;

public class GoldTracker {
    List<BoardPoint> gold_position;

   //get list sorted by closeness to character
    public List<BoardPoint> getByCloseness(GameBoard gameBoard, BoardPoint myCurPos){
        updatePositions(gameBoard);
        gold_position.sort(new Comparator<BoardPoint>() {
            @Override
            public int compare(BoardPoint o1, BoardPoint o2) {
                // Calculate vector lengths to determine distance to gold
                double dist_1 =
                        //manhattan
//                        Math.abs(myCurPos.getX() - o1.getX()) + Math.abs(myCurPos.getY() - o1.getY());
                        //raw euclidean;
                        Math.sqrt(Math.pow(myCurPos.getY() - o1.getY(), 2) +
                        Math.pow(myCurPos.getX() - o1.getX(), 2));
                double dist_2 =
                        //manhattan
//                        Math.abs(myCurPos.getX() - o2.getX()) + Math.abs(myCurPos.getY() - o2.getY());
                        // raw euclidean;
                        Math.sqrt(Math.pow(myCurPos.getY() - o2.getY(), 2) +
                        Math.pow(myCurPos.getX() - o2.getX(), 2));
                return Double.compare(dist_1, dist_2);
            }
        });
        return gold_position;
    }

    //populate the gold pos list
    private void updatePositions(GameBoard gameBoard) {
        gold_position = gameBoard.getGoldPositions();
    }

}
