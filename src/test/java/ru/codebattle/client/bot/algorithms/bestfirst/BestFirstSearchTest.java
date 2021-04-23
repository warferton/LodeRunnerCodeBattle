package ru.codebattle.client.bot.algorithms.bestfirst;

import org.junit.jupiter.api.Test;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.bot.trackers.EnemyTracker;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
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


    @Test
    void pathToGold() {

    //        List<NodePoint> points = new ArrayList<>();
    //        NodePoint goldPosition= null;
    //
    //        //adding points
    ////        for(int i = 0; i < m; i++) {
    ////            String line = in.next();
    ////            for(int i2 = 0; i2 < m; i2++) {
    ////                points.add(new Point(i2, i, Character.toString(line.charAt(i2))));
    ////            }
    ////        }
    //
    //        //find gold -> find closest
    //
    ////        pathToGold(myCurPos, goldList[0]);
    //    }
    }
}

/*
    TODO:  1. Rewrite to work with cur project's classes and structure
           2. Implement weights (e.g., GOLD > NONE, LADDER > BRICK)
           3. Integrate with Tracking classes


 */