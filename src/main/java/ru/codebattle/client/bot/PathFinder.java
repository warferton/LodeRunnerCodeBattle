package ru.codebattle.client.bot;

import lombok.Getter;
import lombok.Setter;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.api.LoderunnerAction;
import ru.codebattle.client.bot.algorithms.astar.Node;
import ru.codebattle.client.bot.behaviours.BehaviourController;
import ru.codebattle.client.bot.trackers.*;
import ru.codebattle.client.bot.algorithms.astar.AStarSearch;

import java.util.LinkedList;
import java.util.List;

public class PathFinder {
    private GoldTracker gt;
    private BehaviourController bc;
    private EnemyTracker et;
    private HeroTracker ht;
    private PillTracker pillTracker;
    private PortalTracker portalTracker;
    @Setter
    private LinkedList<LoderunnerAction> action_list;

    public PathFinder() {
        gt = new GoldTracker();
        et = new EnemyTracker();
        ht = new HeroTracker();
        bc = new BehaviourController(et, ht);
        pillTracker = new PillTracker();
        portalTracker = new PortalTracker();
        action_list = new LinkedList<>();
    }

    public LoderunnerAction findPath(GameBoard gameBoard) {
        Node myCurPos = new Node(gameBoard.getMyPosition(), gameBoard);
        if (action_list.isEmpty() || action_list.peekFirst() == null) {
            List<BoardPoint> gold_list = gt.getByCloseness(gameBoard, myCurPos);
            List<BoardPoint> portal_list = portalTracker.getByCloseness(gameBoard, myCurPos);
            Node end_1 = new Node(gold_list.get(0), gameBoard);
            Node end_2 = new Node(gold_list.get(1), gameBoard);
            Node end_3 = new Node(gold_list.get(2), gameBoard);
            Node end_4 = new Node(portal_list.get(0), gameBoard);

            LinkedList<Node> gold_path_1 = AStarSearch.findPath(gameBoard, myCurPos, end_1);
            if (gold_path_1 != null)
                action_list.addAll(0, bc.getBehaviour(gameBoard, myCurPos, gold_path_1));

            LinkedList<Node> gold_path_2 = AStarSearch.findPath(gameBoard, myCurPos, end_2);
            if (gold_path_2 != null)
                action_list.addAll(0, bc.getBehaviour(gameBoard, myCurPos, gold_path_2));

            LinkedList<Node> gold_path_3 = AStarSearch.findPath(gameBoard, myCurPos, end_3);
            if (gold_path_3 != null)
                action_list.addAll(0, bc.getBehaviour(gameBoard, myCurPos, gold_path_3));

            LinkedList<Node> portal_path_1 = AStarSearch.findPath(gameBoard, myCurPos, end_4);
            action_list.addAll(0, bc.getBehaviour(gameBoard, myCurPos, portal_path_1));
        }
        return action_list.poll();
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
  *                        (2) ConstructPath  (V)
  *                        (3) CheckEnemies / EnemyTracker   (V)
  *                        (4) EvasionBehaviour (V)
  *                         Stage - 2
  *                         (1) PathInterpreter (V)
  *                         (2) BehaviourController (V)
  *                         Extra:
  *                               * Multi-threaded path finding
  *                  ----------------------------------------------------------------
  * TODO:
  *  Additional Enhancements:
  *                     1 - Make PathConstruction and enemy check run concurrently
  *                     2- run 3 path searches concurrently - which ever is the first -> execute
  *                         -> that solves a problem when gold is below bricks
  */
