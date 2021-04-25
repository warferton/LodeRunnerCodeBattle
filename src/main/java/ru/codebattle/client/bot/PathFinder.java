package ru.codebattle.client.bot;

import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.bot.behaviours.BehaviourController;
import ru.codebattle.client.bot.trackers.GoldTracker;
import ru.codebattle.client.bot.trackers.PillTracker;
import ru.codebattle.client.bot.algorithms.astar.AStarSearch;
import ru.codebattle.client.bot.trackers.PortalTracker;

public class PathFinder {
    GameBoard gb;
    GoldTracker gt;
    BehaviourController bc;
    PillTracker pillTracker;
    PortalTracker portalTracker;

    public PathFinder(GameBoard gameBoard) {
        this.gb = gameBoard;
    }

    public void findPath(){

    }
}


 /*
  * TODO:
  *  Legend: ? - Done, not tested;
  *          V - Done, and tested;
  *          X - Not Done / Needs Work;
  * TODO: GOOD IDEA 1:
  *       Path Algorithm: Beam Search( Greedy Best-First );
  *       Desc: get all gold -> sort gold by closeness -> build path to the closest ->
  *             check for enemies:
  *                  (1) if no enemies -> head toward closes gold
  *                  (2) else: (1) enemy in-front -> dig hole in-front -> head opposite direction
  *                                 -> return to the beginning of the func.
  *                            (2) enemy behind -> dig hole behind -> move toward gold.
  *                  ----------------------------------------------------------------
  *                         Stage - 1
  *         Func's needed: (1) SortGoldByCloseness ( V )
  *                        (2) ConstructPath  (V / ?)
  *                        (3) CheckEnemies / EnemyTracker   (V)
  *                        (4) EvasionBehaviour (V)
  *                         Stage - 2
  *                         (1) PathInterpreter (X)
  *                         (2) BehaviourController (X)
  *                         Extra:
  *                               * Multi-threaded path finding
  *                  ----------------------------------------------------------------
  * TODO:
  *  Additional Enhancements:
  *                     1 - Make PathConstruction and enemy check run concurrently
  *                     2- run 3 path searches concurrently - which ever is the first -> execute
  *                         -> that solves a problem when gold is below bricks
  */
