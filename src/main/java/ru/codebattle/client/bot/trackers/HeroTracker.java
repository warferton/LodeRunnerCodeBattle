package ru.codebattle.client.bot.trackers;

import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

import java.util.List;
import java.util.stream.Collectors;

public class HeroTracker {
    private List<BoardPoint> heroPositions;

    //populate the enemy and player pos list
    private void updatePositions(GameBoard gameBoard){
        this.heroPositions = gameBoard.getOtherHeroPositions();
    }

    /**
     *  Get Positions of heroes that are in the
     *  same quadrant as the character ( Quad = X*Y  )
     *  @return enemiesOnQuadrant
     */
    public List<BoardPoint> getHeroesInQuadrant(GameBoard gameBoard, BoardPoint myCurPos, int x, int y){
        updatePositions(gameBoard);
        return heroPositions.stream()
                .filter(pos ->
                        Math.abs(myCurPos.getY() - pos.getY()) <= y &&
                        Math.abs(myCurPos.getX() - pos.getX()) <= x
                )
                .collect(Collectors.toList());

    }

    /**
     * Checks if nearest hero is in front or behind
     * @param heroPos
     * @param myCurPos
     * @return
     */
    public int getHeroPositioning(List<BoardPoint> heroPos, BoardPoint myCurPos){
        boolean enemy_behind = heroPos.stream().anyMatch(pos -> pos.getX() < myCurPos.getX());
        boolean enemy_infront = heroPos.stream().anyMatch(pos -> pos.getX() > myCurPos.getX());
        if (enemy_behind && enemy_infront)
            return 2;
        if(enemy_behind)
            return 1;

        //if enemy in-front
        return 0;
    }


}
