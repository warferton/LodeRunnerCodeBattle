package ru.codebattle.client.bot;

import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.bot.trackers.EnemyTracker;
import ru.codebattle.client.bot.trackers.GoldTracker;
import ru.codebattle.client.bot.trackers.PillTracker;

public class PathFinder {
    GameBoard gameBoard;
    GoldTracker goldTracker;
    EnemyTracker enemyTracker;
    PillTracker pillTracker;

    public PathFinder(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}


 /*
  * TODO:
  *  Legend: ? - Done, not tested;
  *          V - Done, and tested;
  *          X - Not Done / Needs Work;
  * TODO: GOOD IDEA 1:
  *       Algo: Greedy Beast-First;
  *       Desc: get all gold -> sort gold by closeness -> build path to the closest ->
  *             check for enemies:
  *                  (1) if no enemies -> head toward closes gold
  *                  (2) else: (1) enemy in-front -> dig hole in-front -> head opposite direction
  *                                 -> return to the beginning of the func.
  *                            (2) enemy behind -> dig hole behind -> move toward gold.
  *                  ----------------------------------------------------------------
  *         Func's needed: (1) SortGoldByCloseness ( V )
  *                        (2) ConstructPath  (X)
  *                        (3) CheckEnemies   (X)
  *                        (4) EvasionBehaviour (X)
  *                  ----------------------------------------------------------------
  * TODO:
  *  Additional Enhancements:
  *                     1 - Make PathConstruction and enemy check run concurrently
  *
  */
