package ru.codebattle.client.bot.trackers;

import org.junit.jupiter.api.Test;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTrackerTest {
    GameBoard gameBoard = new GameBoard(
            "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                    "☼    (    $@            $ $   ~~~~~~~~~      & &  ~~~~~~~☼\n" +
                    "☼##H########################H#H       H##########H     &$☼\n" +
                    "☼  H $     &   @   & &(     H######H  H      &   H#☼☼☼☼☼#☼\n" +
                    "☼H☼☼#☼☼H   $H#########U     H# &&  H#####H#####H## $~~~~~☼\n" +
                    "☼H   &$H    H &&&   & H#####H#  && H ~  ⊐H    @H  ~~     ☼\n" +
                    "☼H#☼#☼#H   &H         H  ~~~ #####H#     H     H    {~( (☼\n" +
                    "☼H  ~  H~~~ЄH~~~~Є~ ( H        &  H   H######H##      ЄЄ(☼\n" +
                    "☼H     H $  H  $ (H###☼☼☼☼☼☼H☼    H~~~H  »   H          #☼\n" +
                    "☼H(  ( H    H#####H         H    «H      H#########H     ☼\n" +
                    "☼☼###☼##☼##☼H  &      H###H##    H##     H#&      ##    (☼\n" +
                    "☼☼###☼~~~~  H &       H   H######H######### H###H #####H#☼\n" +
                    "☼☼(((☼      H ) ~~~~Є~H   H      H       «  H# #H      H ☼\n" +
                    "☼########H###☼☼☼☼     H  ############   ###### ##########☼\n" +
                    "☼        H   (        H        &              @        & ☼\n" +
                    "☼Q##########################H########~Є~####H############☼\n" +
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

    BoardPoint myCurPos = gameBoard.getMyPosition();
    EnemyTracker et = new EnemyTracker();

    @Test
    public void testGetEnemyOnSamePlane(){
        String chceck_pos = "[[33,9], [41,8]]";
        long start = System.currentTimeMillis();
        List<BoardPoint> enemyPos = et.getEnemyOnSamePlane(gameBoard, myCurPos);
        long time = System.currentTimeMillis() - start;
        System.out.println(myCurPos);
        String enemyPos_str = enemyPos.toString();
        System.out.println(enemyPos_str);
        System.out.println(gameBoard.getEnemyPositions());
        System.out.println("Seconds elapsed: " + (float) (time / 1000));
        assertEquals(enemyPos_str, chceck_pos);
    }

    @Test
    public void testGetEnemyInQuadrant(){
        long start = System.currentTimeMillis();
        List<BoardPoint> enemyPos = et.getEnemyInQuadrant(gameBoard, myCurPos, 10, 10);
        long time = System.currentTimeMillis() - start;
        System.out.println(myCurPos);
        String enemyPos_str = enemyPos.toString();
        System.out.println(enemyPos_str);
        System.out.println(gameBoard.getEnemyPositions());
        System.out.println("Seconds elapsed: " + (float) (time / 1000));
        assertEquals(enemyPos_str, Collections.emptyList().toString());
    }

}
