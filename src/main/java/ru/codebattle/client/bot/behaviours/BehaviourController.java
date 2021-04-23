package ru.codebattle.client.bot.behaviours;

import lombok.extern.slf4j.Slf4j;
import ru.codebattle.client.api.BoardElement;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.api.LoderunnerAction;
import ru.codebattle.client.bot.trackers.EnemyTracker;

import java.util.List;

@Slf4j
public class BehaviourController {
    EnemyTracker et;
    Behaviours behaviours;

    public BehaviourController(EnemyTracker et) {
        this.et = et;
        behaviours = new Behaviours(et);
    }

    public LoderunnerAction getBehaviour(GameBoard gameBoard, BoardPoint myCurPos) {
        List<BoardPoint> enemy_pos_list = et.getEnemyInQuadrant(gameBoard, myCurPos, 5, 1);
        BoardElement myCurDirecton = gameBoard.getElementAt(myCurPos);

        // mo eme,ies near
        if (enemy_pos_list.isEmpty()) {
            log.info("Good to Go!");
            /* continue traversing path */
        }

        //get enemy positioning respective to the hero
        int STATUS_CODE = et.getEnemyPositioning(enemy_pos_list, myCurPos);

        // hero is sandwiched
        switch (STATUS_CODE) {
            case 0 :    log.info("Enemy In-front!");
                        return behaviours.enemyInFrontDrill(myCurDirecton);

            case 1 :    log.info("Enemy Behind!");
                        return behaviours.enemyBehindDrill(myCurDirecton);

            case 2 :    log.info("I'm Sandwiched!");
                        return behaviours.heroSandwichedDrill(
                                enemy_pos_list,
                                myCurPos,
                                myCurDirecton
                        );

            default:
                throw new IllegalStateException("Unexpected value: " + STATUS_CODE);
        }

    }


}
/*
 * TODO: 1. Decision
 *       Determine if enemy is on the same plane:
 *       (1) if yes -> execute evasion.
 *       (2) if no -> continue.
 *       2. Execution
 *       Determine if enemy behind or in-front:
 *       (1) If behind -> dig hole behind -> continue
 *       (2) if in-front -> dig hole in-front -> go opposite
 *       ( Possible option: determine enemy closeness -> if far don't dig / else dig )
 *
 * */