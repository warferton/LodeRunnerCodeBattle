package ru.codebattle.client.bot.trackers;

import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tracks Enemy Positions on the Board
 */

public class EnemyTracker {
    private List<BoardPoint> enemyPositions;

    //populate the enemy and player pos list
    private void updatePositions(GameBoard gameBoard){
        this.enemyPositions = gameBoard.getEnemyPositions();
    }

    /**
     *  Get Positions of enemies that are on the
     *  same horizontal plane as the character ( Height = 3 )
     *  @return enemiesOnSamePlane
     */
    public List<BoardPoint> getEnemyOnSamePlane(GameBoard gameBoard, BoardPoint myCurPos){
        updatePositions(gameBoard);
        return enemyPositions.stream()
                .filter(pos -> Math.abs(myCurPos.getY() - pos.getY()) <= 3)
                .collect(Collectors.toList());

    }
    /**
     *  Get Positions of enemies that are in the
     *  same quadrant as the character ( Quad = 10x10 )
     *  @return enemiesOnQuadrant
     */
    public List<BoardPoint> getEnemyInQuadrant(GameBoard gameBoard, BoardPoint myCurPos, int x, int y){
        updatePositions(gameBoard);
        return enemyPositions.stream()
                .filter(pos ->
                        Math.abs(myCurPos.getY() - pos.getY()) <= y &&
                        Math.abs(myCurPos.getX() - pos.getX()) <= x
                )
                .collect(Collectors.toList());

    }

    /**
     * Determine if hero is sandwiched between two or more enemies
     * on a horizontal plane of size 1 by X (x = 5, by default)
     * @param enemyPos positions of enemies on the same horizontal plane
     * @param myCurPos hero position
     * @return 0 - if enemy in-front
     * <br/>
     * 1 - if enemy behind
     * <br/>
     * 2 - if sandwiched
     */
    public int getEnemyPositioning(List<BoardPoint> enemyPos, BoardPoint myCurPos){
       boolean enemy_behind = enemyPos.stream().anyMatch(pos -> pos.getX() < myCurPos.getX());
       boolean enemy_infront = enemyPos.stream().anyMatch(pos -> pos.getX() > myCurPos.getX());
       if (enemy_behind && enemy_infront)
           return 2;
       if(enemy_behind)
           return 1;

       //if enemy in-front
       return 0;
    }

    // Checks if enemy behind is the nearest one
    public boolean isNearestEnemyBehind(List<BoardPoint> enemyPos, BoardPoint myCurPos){
        int enemy_front_distance = 5;
        int enemy_behind_distance = 5;
        for(BoardPoint pos : enemyPos){
            int pos_x = pos.getX();
            int myPos = myCurPos.getX();

            if(pos_x < myPos){
                enemy_behind_distance = Math.min(
                        enemy_behind_distance,
                        Math.abs(pos_x - myPos));
            }
            else{
                enemy_front_distance = Math.min(
                        enemy_front_distance,
                        Math.abs(pos_x - myPos));
            }
        }

        return enemy_behind_distance < enemy_front_distance;
    }



}
