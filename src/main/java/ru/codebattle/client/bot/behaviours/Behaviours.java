package ru.codebattle.client.bot.behaviours;

import ru.codebattle.client.api.BoardElement;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.LoderunnerAction;
import ru.codebattle.client.bot.trackers.EnemyTracker;

import java.util.Arrays;
import java.util.List;

import static ru.codebattle.client.api.BoardElement.*;
import static ru.codebattle.client.api.LoderunnerAction.*;

public class Behaviours {
    private final EnemyTracker et;

    public Behaviours(EnemyTracker et) {
        this.et = et;
    }

    private final List<BoardElement> HERO_STATES_LEFT = Arrays.asList(
                HERO_LEFT,
                HERO_PIPE_LEFT,
                HERO_SHADOW_LEFT,
                HERO_SHADOW_PIPE_LEFT
    );

    private final List<BoardElement> HERO_STATES_RIGHT = Arrays.asList(
                HERO_RIGHT,
                HERO_PIPE_RIGHT,
                HERO_SHADOW_RIGHT,
                HERO_SHADOW_PIPE_RIGHT
    );


    // Behaviour if enemy is in-front
    public LoderunnerAction enemyInFrontDrill(BoardElement myCurDirection){

        // Hero is pointed to the left
        if(HERO_STATES_LEFT.contains(myCurDirection)){
            return DRILL_LEFT;
        }
        // Hero is pointed to the right
        else if(HERO_STATES_RIGHT.contains(myCurDirection)){
            return DRILL_RIGHT;
        }
        //TODO: Hero is Falling or on a Ladder
        else{}

        return GO_UP;
    }

    // Behaviour if enemy is in-front
    public LoderunnerAction enemyBehindDrill(BoardElement myCurDirection){

        // Hero is pointed to the left
        if(HERO_STATES_LEFT.contains(myCurDirection)){
            return DRILL_RIGHT;
        }
        // Hero is pointed to the right
        else if(HERO_STATES_RIGHT.contains(myCurDirection)){
            return DRILL_LEFT;
        }
        //TODO: Hero is Falling or on a Ladder
        else{}

        return GO_DOWN;
    }

    // Behaviour if enemy is in-front
    public LoderunnerAction heroSandwichedDrill(List<BoardPoint> enemy_pos,
                                                BoardPoint myCurPos,
                                                BoardElement myCurDirection){

        if(et.isNearestEnemyBehind(enemy_pos, myCurPos))
            return enemyBehindDrill(myCurDirection);

        else
            return enemyInFrontDrill(myCurDirection);

    }
}

/*
 * TODO: 1. Decision
 *       Determine if enemy is on the same plane in near proximity:
 *       (1) if yes -> execute evasion.
 *       (2) if no -> continue.
 *       2. Execution
 *       Determine if enemy behind or in-front:
 *       (1) If behind -> dig hole behind -> continue
 *       (2) if in-front -> dig hole in-front -> go opposite
 *       ( Possible option: determine enemy closeness -> if far don't dig / else dig )
 *
 * */