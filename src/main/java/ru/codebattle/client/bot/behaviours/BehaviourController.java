package ru.codebattle.client.bot.behaviours;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.codebattle.client.api.BoardElement;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.api.LoderunnerAction;
import ru.codebattle.client.bot.algorithms.PathConstructor;
import ru.codebattle.client.bot.algorithms.astar.Node;
import ru.codebattle.client.bot.trackers.EnemyTracker;
import ru.codebattle.client.bot.trackers.HeroTracker;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class BehaviourController {
    private EnemyTracker et;
    private HeroTracker ht;
    private Behaviours behaviours;
    @Getter
    @Setter
    private LinkedList<LoderunnerAction> action_list = new LinkedList<>();

    public BehaviourController(EnemyTracker et, HeroTracker ht) {
        this.et = et;
        this.ht = ht;
        behaviours = new Behaviours(et);
    }

    public LinkedList<LoderunnerAction> getBehaviour(GameBoard gameBoard, BoardPoint myCurPos, LinkedList<Node> path) {
        List<BoardPoint> enemy_pos_list = et.getEnemyInQuadrant(gameBoard, myCurPos, 10, 1);
        BoardElement myCurDirection = gameBoard.getElementAt(myCurPos);
        List<BoardPoint> hero_pos_list = ht.getHeroesInQuadrant(gameBoard, myCurPos, 3, 1);
        if (!hero_pos_list.isEmpty()) {
            //get closest
            int STATUS_CODE = ht.getHeroPositioning(hero_pos_list, myCurPos);
            switch (STATUS_CODE) {
                case 0:
                    log.info("Hero In-front!");
                    if(gameBoard.hasElementAt(myCurPos.shiftBottom(), BoardElement.BRICK)){
                        action_list.addAll(0, behaviours.heroInFrontDrill(myCurDirection));
                        return action_list;
                    }
                    break;

                case 1:
                    log.info("Hero Behind!");
                    if(gameBoard.hasElementAt(myCurPos.shiftBottom(), BoardElement.BRICK)){
                        action_list.addAll(0, behaviours.heroBehindDrill(myCurDirection));
                        return action_list;
                    }
                    break;

                case 2:
                    log.info("I'm Sandwiched!");
                    if(gameBoard.hasElementAt(myCurPos.shiftBottom(), BoardElement.BRICK)){
                        action_list.addAll(0,behaviours.heroSandwichedDrill(
                                enemy_pos_list,
                                myCurPos,
                                myCurDirection
                        ));
                        return action_list;
                    }
                    break;

            }
        }
        // mo eme,ies near
        if (enemy_pos_list.isEmpty()) {
            log.info("Good to Go!");
            if (action_list.peek() == null|| path.peek() != myCurPos) {
                setAction_list(PathConstructor.createPath(myCurPos, path));
            }
            return action_list;
        }

        //get enemy positioning respective to the hero
        int STATUS_CODE = et.getEnemyPositioning(enemy_pos_list, myCurPos);

        action_list.clear();
        switch (STATUS_CODE) {
            case 0:
                log.info("Enemy In-front!");
                action_list.addAll(0,behaviours.enemyInFrontDrill(myCurDirection));
                return action_list;
            case 1:
                log.info("Enemy Behind!");
                action_list.addAll(0,behaviours.enemyBehindDrill(myCurDirection));
                return action_list;

            case 2:
                log.info("I'm Sandwiched!");

                action_list.addAll(0, behaviours.heroSandwichedDrill(
                        enemy_pos_list,
                        myCurPos,
                        myCurDirection
                ));
                return action_list;

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