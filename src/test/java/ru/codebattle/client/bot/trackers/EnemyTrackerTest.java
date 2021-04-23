package ru.codebattle.client.bot.trackers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTrackerTest {
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
                    "☼☼###☼~~~~  H &       H   H######H######### H###H #####H#☼\n" +
                    "☼☼(((☼      H ) ~~~~Є~H   H      H       «  H# #H      H ☼\n" +//other enemy
                    "☼########H###☼☼☼☼     H  ############   ###### ##########☼\n" +
                    "☼            «  ►    «           &              @       &☼\n" + //TODO : !PLAYER POSITION!
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

    BoardPoint myCurPos = gameBoard.getMyPosition();
    EnemyTracker et = new EnemyTracker();

    @Test
    public void testGetEnemyOnSamePlane(){
        String chceck_pos = "[[1,15], [41,12], [13,14], [21,14]]";
        long start = System.currentTimeMillis();
        List<BoardPoint> enemyPos = et.getEnemyOnSamePlane(gameBoard, myCurPos);
        long time = System.currentTimeMillis() - start;
        System.out.println(myCurPos);
        String enemyPos_str = enemyPos.toString();
        System.out.println(enemyPos_str);
        System.out.println(gameBoard.getEnemyPositions());
        System.out.println("Seconds elapsed: " + (float) (time / 1000));
        assertEquals(chceck_pos, enemyPos_str);
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
        assertEquals("[[13,14], [21,14]]", enemyPos_str);
    }

    @Test
    public void testGetEnemyPositioning(){
        List<BoardPoint> enemy_pos = et.getEnemyInQuadrant(gameBoard, myCurPos, 7,1);
        long start = System.currentTimeMillis();
        int positioning = et.getEnemyPositioning(enemy_pos, myCurPos);
        long time = System.currentTimeMillis() - start;
        System.out.println(myCurPos);
        System.out.println(positioning);
        System.out.println(enemy_pos.toString());
        System.out.println("Seconds elapsed: " + (float) (time / 1000));
        assertEquals(2, positioning);
    }

    @Test
    public void testIsNearestEnemyBehind(){
        List<BoardPoint> enemy_pos = et.getEnemyInQuadrant(gameBoard, myCurPos, 7,1);
        long start = System.currentTimeMillis();
        boolean is_behind = et.isNearestEnemyBehind(enemy_pos, myCurPos);
        long time = System.currentTimeMillis() - start;
        System.out.println(myCurPos);
        System.out.println(enemy_pos.toString());
        System.out.println("Seconds elapsed: " + (float) (time / 1000));
        assertTrue(is_behind);
    }


}
