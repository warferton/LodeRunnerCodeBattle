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
                    "☼)            ⊛    ⊛    (( $  ~~~~~~~~~ &    (S&$&~~~~~~~☼\n" +
                    "☼##H########################H#U    & SH##########H@      ☼\n" +
                    "☼ )H(               (   &&  H######H ⊛H &        H#☼☼☼☼☼#☼\n" +
                    "☼H☼☼#☼☼HS   H#########H    SH#&S& &H#####H#####H## S~~~~~☼\n" +
                    "☼H     H    H   $$    H#####H#     H ~   H     H  ~~&&@  ☼\n" +
                    "☼H#☼#☼#H    H   &⊛@   H  ~~~ ###1#H#     H&  & H &S ~Є   ☼\n" +
                    "☼H  ~  H~Є~~H~~~~~~   H (      $  H   H######H##  @⊛  ~~(☼\n" +
                    "☼H     H    H     ⋕###☼☼☼☼☼☼H☼    H~~~H   ( ⊛H   (      #☼\n" +
                    "☼H     H    H#####H         H  S  H      H#########H &  @☼\n" +
                    "☼☼###☼##☼##☼H         H###H##  ( H## ⊛   H# (     ##     ☼\n" +
                    "☼☼###☼~~~~  H  ⊛$ &  $H   H######H######### H###H(#####H#☼\n" +
                    "☼☼$»&☼R     H)  ~~~~~~H   H      H          U# #H(    (H ☼\n" +
                    "☼########H###☼☼☼☼     H  ############  &###### ##########☼\n" +
                    "☼    &   H            H                                  ☼\n" +
                    "☼H##########################H########~~~####H############☼\n" +
                    "☼⋕                $~~~$   $ Q             & H            ☼\n" +
                    "☼#######H####### &       &  H###~~~~⊛     ############H  ☼\n" +
                    "☼&      H~~~~~~~~~~         H                         H  ☼\n" +
                    "☼@   $  H    ##H   #######H##########~~~~~~~H######## H  ☼\n" +
                    "☼(      H    ##H          H                 H   &     H  ☼\n" +
                    "☼##H#####    ########H#######~~~~  ~~~4########~~~~~  H @☼\n" +
                    "☼( H           ⊛     H    &  ⊛                    ~~~~H  ☼\n" +
                    "☼#########H##########H   &S   #☼☼☼☼☼☼#   ☼☼☼☼☼☼☼      H S☼\n" +
                    "☼ (&  &   H $   S    H    & & ~(    @~                H  ☼\n" +
                    "☼☼☼$      H~~~~~~~~~~H   (     ######  ⊏###########   H  ☼\n" +
                    "☼    H######         #######H⊛          Є~~~~~~~~~~~~~H  ☼\n" +
                    "☼H☼  H                      H  H####H )   ⊛⊛          H  ☼\n" +
                    "☼H☼☼#☼☼☼☼☼☼☼☼☼☼☼☼###☼☼☼☼☼☼☼☼U☼☼☼☼☼☼☼☼#☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼#☼\n" +
                    "☼H &  &       ~~H~~~~☼☼☼☼☼☼☼H☼☼☼☼☼☼☼$      H   ~~~~~~~~~H☼\n" +
                    "☼H~~~~  ######  H       ⊛ H☼H☼H        ####H  ☼⊛        H☼\n" +
                    "☼H($  (         ##H#######H☼H☼H######H     ###☼☼☼☼☼☼☼☼ ~H☼\n" +
                    "☼H#########       H    ~~~H☼H☼H~~~   H~~~~~ ##  ))    ~ H☼\n" +
                    "☼HS       ###H####H##H   ()☼H☼       H     ###☼☼☼☼☼☼ ~  H☼\n" +
                    "☼H@          H      #######☼H☼#####  H#####   ~~~~~~~ ~ H☼\n" +
                    "☼~~~~~~~~~~~~H &     H~~~~~☼H☼~~~~~  H             ~ ~ SH☼\n" +
                    "☼     H              H  ⊛  ☼H☼     #####3####H   S      H☼\n" +
                    "☼(### #############H H#####☼H☼               H ######## H☼\n" +
                    "☼U                 H     S ☼H☼#######        H       $  H☼\n" +
                    "☼H#####      ⊛  H##H####   »            ###H#########   H☼\n" +
                    "☼H   S  H######### H   ############    ⊐   H            H☼\n" +
                    "☼H##    H       $  H~~~~~~           (     H #######H## H☼\n" +
                    "☼~~~~#####H#   ~~~~H      ⊛  ########H $   H        H   H☼\n" +
                    "☼         HS       H      ~~~~~~~~   H     H        H   H☼\n" +
                    "☼&  ########H    ######H##        ##############   ⊛H   H☼\n" +
                    "☼(     ⊛    H      &S  H    ⊛   ~~~~~           ##H#####H☼\n" +
                    "☼H    ###########H     H#####H         H##H       H &   H☼\n" +
                    "☼H###            H     H     ###########  ##H###  H    ⊛H☼\n" +
                    "☼H  ######  ##H######  Q              ⌋   ⋊ H   ##H###  H☼\n" +
                    "☼H S      @   H ~~~~~##H###H⊛    #########H##    &  S   H☼\n" +
                    "☼    H########H#       H   ######   ⊛     H  (          H☼\n" +
                    "☼ ###H        H         ~~~~~H      ##H###H####H###     H☼\n" +
                    "☼    H########H#########     H      ⊛&H ⊛  ⊛   H        H☼\n" +
                    "☼H   H                       H        H        H(       H☼\n" +
                    "☼H  ####H######     »   #####H########H##     $H#####   H☼\n" +
                    "☼H      H      H#######H             ⊛   ⊛ (   H      ( H☼\n" +
                    "☼##############H       H#################################☼\n" +
                    "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼");

    Node myCurPos = new Node(gameBoard.getMyPosition(), gameBoard);
    private GoldTracker gt = new GoldTracker();
    List<BoardPoint> gold_list = gt.getByCloseness(gameBoard, myCurPos);
    Node end = new Node(gold_list.get(0), gameBoard);
    Node end2 = new Node(gold_list.get(1), gameBoard);

//TODO: debug the Down Ladders !!!
    // About 0.2 - 0.5 sec
    @Test
    void testConstructPath() {
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
        LinkedList<LoderunnerAction> commands = PathConstructor.createPath(myCurPos, path);
        long time = System.currentTimeMillis() - start;
        System.out.println(commands.toString());
        System.out.println("Time Elapsed : " + (float)time/1000);
        System.out.println("Actual GoldBag: " + end2.toString());
        LinkedList<Node> path2 = AStarSearch.findPath(gameBoard, myCurPos, end2);
        long start2 = System.currentTimeMillis();
        LinkedList<LoderunnerAction> command2 = PathConstructor.createPath(myCurPos, path2);
        long time2 = System.currentTimeMillis() - start2;
        System.out.println(command2.toString());
        System.out.println("Time Elapsed : " + (float)time2/1000);
        System.out.println(path2);
        assertNotNull(path2);
        assertNotNull(path);
    }
}