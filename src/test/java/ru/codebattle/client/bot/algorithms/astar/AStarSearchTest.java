package ru.codebattle.client.bot.algorithms.astar;

import org.junit.jupiter.api.Test;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.api.LoderunnerAction;
import ru.codebattle.client.bot.algorithms.PathConstructor;
import ru.codebattle.client.bot.trackers.GoldTracker;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AStarSearchTest {
    GameBoard gameBoard = new GameBoard(
            "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                    "☼    (    $@            $ $   ~~~~~~~~~      & &  ~~~~~~~☼\n" +
                    "☼##H########################H#H       H##########H     &$☼\n" +
                    "☼  H $     &   @   & &(     H######H  H      &   H#☼☼☼☼☼#☼\n" +
                    "☼H☼☼#☼☼H   $H#########U     H# &&  H#####H#####H## $~~~~~☼\n" +
                    "☼H   &$H    H &&&   & H#####H#  && H ~  ⊐H    @H  ~~     ☼\n" +
                    "☼H#☼#☼#H   &H         H  ~~~ #####H#     H     H     ~( (☼\n" +
                    "☼H  ~  H~~~ЄH~~~~Є~ ( H        &  H   H######H##      ЄЄ(☼\n" +
                    "☼H     H $  H  $ (H###☼☼☼☼☼☼H☼    H~~~H  »   H          #☼\n" +
                    "☼H(  ( H    H#####H         H    «H      H#########H     ☼\n" +
                    "☼☼###☼##☼##☼H  &      H###H##    H##     H#&      ##    (☼\n" +
                    "☼☼###☼~~~~  H &       H   H######H######### H###H #####H#☼\n" +// Found this one
                    "☼☼(((☼      H ) ~~~~Є~H   H      H       «  H# #H      H ☼\n" +//other enemy
                    "☼########H###☼☼☼☼     H  ############   ###### ##########☼\n" +
                    "☼                          ►     &              @       &☼\n" + //TODO : !PLAYER POSITION!
                    "☼Q##########################H########~Є~####H############☼\n" +// other enemy on ladder
                    "☼H                 ~~~      H (  $ $  &     H           &☼\n" +
                    "☼#######H#######   (&       H###~~~~     &############H  ☼\n" +
                    "☼ &  &@@H~~~~~~~~~~(   (    H             $         & H  ☼\n" +
                    "☼    &  H$&  ##H   #######H##########~~Є~~~~H######## H  ☼\n" +
                    "☼       H  $&##H()(     & H&     $    (     H      &  H  ☼\n" +
                    "☼##H#####    ########H#######~~~~  ~~~#########~~~~~  H  ☼\n" +
                    "☼  H                 H  && $                   (  ~~~~H  ☼\n" +
                    "☼######## H##########H   @    #☼☼☼☼☼☼#   ☼☼☼☼☼☼☼     &H  ☼\n" +
                    "☼(    &   H & @ $   $H        ~(&    ~     &   @  (   H  ☼\n" +
                    "☼☼☼      )H~~~~~~~~~~H     &  &######   ###########   H  ☼\n" +
                    "☼    H######         #######H           ~~~~~~~~~~~~~~H  ☼\n" +
                    "☼H☼  H          »(          H  H####H ((         &    H  ☼\n" +
                    "☼H☼☼#☼☼☼☼☼☼☼☼☼☼☼☼###☼☼☼☼☼☼☼☼H☼☼☼☼☼☼☼☼#☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼#☼\n" +
                    "☼H            ~~H~~~~☼☼☼☼☼☼☼H☼☼☼☼☼☼☼&      H   ~~~~~~~~~H☼\n" +
                    "☼Q~~~~  ######  H         H☼H☼H&       ####H  ☼  @      H☼\n" +
                    "☼H          $   ##H#######H☼H☼H######H    $###☼☼☼☼☼☼☼☼&~H☼\n" +
                    "☼H#########       H    ~~~H☼H☼H~~~   H~~~~~ ##    ((  ~ H☼\n" +
                    "☼H &&$$   ###H####H##H     ☼H☼  &    H   & ###☼☼☼☼☼☼ ~  H☼\n" +
                    "☼H       $   H&     #######☼H☼#####  H#####  &~~~~~~~ ~ H☼\n" +
                    "☼~~~~~~~~~~~~H@  &   H~~~~~☼H☼~~~~~  H             ~ ~  H☼\n" +
                    "☼ & @ H          «   H( (  ☼H☼     ##########H    ( &   H☼\n" +
                    "☼ ###⊏####X########H H#####☼H☼(          &&  H ######## H☼\n" +
                    "☼H  $  $& &        H((     ☼H☼#######        H          H☼\n" +
                    "☼H#####     ((  H##H#### ()             ###H#########   H☼\n" +
                    "☼H  &   H######### H$& ############        H         (( H☼\n" +
                    "☼H##    H  &       H~~~~~~&          (     H #######H## H☼\n" +
                    "☼~~~~#####H#   ~Є~~H $       ########H     H & &    H & H☼\n" +
                    "☼$@$      H        H      ~~~~~~~~   H     H(       H   H☼\n" +
                    "☼   ########H    ######H##        ##############    H   H☼\n" +
                    "☼           H        $ H   (  & ~~~~~ &$  $     ##H#####H☼\n" +
                    "☼H    ###########H     Q#####H &⌊  $(  H##H  @    H    $H☼\n" +
                    "☼H###            H     H    @####*######$ ##H###& H     H☼\n" +
                    "☼H& ######  ##H######  H      @             H   ##H###  H☼\n" +
                    "☼H   (        H(~~~Є~##H###H  «  #########H##   @    $  H☼\n" +
                    "☼   (H########H#    &  H   ######         H             H☼\n" +
                    "☼ ###H        H    (    ~~~~~H @    ##H###H####H###  @  H☼\n" +
                    "☼    H########H#########  &  H        H   @ &  H        H☼\n" +
                    "☼H   H           &           H   (    H(    &  H     $$ H☼\n" +
                    "☼H  ####H######   (    (#####H########H##      H#####   H☼\n" +
                    "☼H      H  (   H#######U                       H   &    H☼\n" +
                    "☼##############H       H#################################☼\n" +
                    "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n");

    Node myCurPos = new Node(gameBoard.getMyPosition(), gameBoard);
    private GoldTracker gt = new GoldTracker();
    List<BoardPoint> gold_list = gt.getByCloseness(gameBoard, myCurPos);
    Node end = new Node(gold_list.get(0), gameBoard);
    Node end2 = new Node(gold_list.get(1), gameBoard);

//TODO: debug the Down Ladders !!!
    // About 0.2 - 0.5 sec
    @Test
    void testCunstructPath() {
        System.out.println(gold_list.toString());
        System.out.println("Actual GoldBag: " + end.toString());
        System.out.println("Initial Position: " + myCurPos.toString());
        long start = System.currentTimeMillis();
        List<Node> path = AStarSearch.findPath(gameBoard,myCurPos, end);
        long time = System.currentTimeMillis() - start;
        System.out.println("Time Elapsed : " + (float)time/1000);
        System.out.println("Actual GoldBag: " + end2.toString());
        long start2 = System.currentTimeMillis();
        List<Node> path2 = AStarSearch.findPath(gameBoard,myCurPos, end2);
        long time2 = System.currentTimeMillis() - start2;
        System.out.println("Time Elapsed : " + (float)time2/1000);
        System.out.println(path2);
        assertNotNull(path2);
        assertNotNull(path);
    }

    // 0.2 - 0.4
    @Test
    void testPathConstructor() {
        System.out.println(gold_list.toString());
        System.out.println("Actual GoldBag: " + end.toString());
        System.out.println("Initial Position: " + myCurPos.toString());
        LinkedList<Node> path = AStarSearch.findPath(gameBoard,myCurPos, end);
        long start = System.currentTimeMillis();
        LoderunnerAction[] commands = PathConstructor.createPath(myCurPos, path);
        long time = System.currentTimeMillis() - start;
        for(LoderunnerAction a : commands){
            System.out.println(a.toString());
        }
        System.out.println("Time Elapsed : " + (float)time/1000);
        System.out.println("Actual GoldBag: " + end2.toString());
        LinkedList<Node> path2 = AStarSearch.findPath(gameBoard, myCurPos, end2);
        long start2 = System.currentTimeMillis();
        LoderunnerAction[] commands2 = PathConstructor.createPath(myCurPos, path2);
        long time2 = System.currentTimeMillis() - start;
        for(LoderunnerAction a : commands2){
            System.out.println(a.toString());
        }
        System.out.println("Time Elapsed : " + (float)time2/1000);
        System.out.println(path2);
        assertNotNull(path2);
        assertNotNull(path);
    }
}