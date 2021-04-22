package ru.codebattle.client.bot.trackers;

import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GoldTracker {
    GameBoard gameBoard;
    List<BoardPoint> gold_position;

    public GoldTracker(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

   //get list sorted by closeness to character
    public List<BoardPoint> getByCloseness(){
        findAllGold();
        BoardPoint myCurPos = gameBoard.getMyPosition();
        gold_position.sort(new Comparator<BoardPoint>() {
            @Override
            public int compare(BoardPoint o1, BoardPoint o2) {
                // Calculate vector lengths to determine distance to gold
                double dist_1 = Math.sqrt(Math.pow(Math.abs(myCurPos.getY() - o1.getY()), 2) +
                        Math.pow(Math.abs(myCurPos.getX() - o1.getX()), 2));
                double dist_2 = Math.sqrt(Math.pow(Math.abs(myCurPos.getY() - o2.getY()), 2) +
                        Math.pow(Math.abs(myCurPos.getX() - o2.getX()), 2));
                return Double.compare(dist_1, dist_2);
            }
        });
        return gold_position;
    }

    //populate the gold pos list
    public void findAllGold() {
        gold_position = gameBoard.getGoldPositions();
    }

}
