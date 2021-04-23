package ru.codebattle.client.bot.trackers;

import org.junit.jupiter.api.Test;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.GameBoard;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoldTrackerTest {
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
    private GoldTracker gt = new GoldTracker();

    /**
     * Test Sorting A Received Collection of Gold Positions
     */
    @Test
    public void testSortByCloseness(){
        String compare_pos = "[[51,4], [55,2], [56,2], [46,5], [47,1], [45,3], [55,14], [45,1], [43,10], [46,14], [56,16], [52,18], [51,20], [41,17], [42,18], [53,23], [38,16], [47,24], [33,5], [35,16], [32,5], [32,4], [43,24], [31,7], [31,4], [49,27], [33,16], [31,14], [33,20], [49,30], [54,31], [26,1], [32,24], [42,31], [36,29], [24,1], [27,20], [45,34], [30,25], [41,33], [27,22], [52,36], [21,3], [24,20], [25,22], [27,25], [31,30], [25,23], [20,5], [24,22], [42,37], [41,37], [19,3], [32,33], [20,17], [16,5], [54,42], [47,42], [45,42], [20,24], [15,5], [15,8], [15,3], [15,10], [14,5], [14,11], [55,46], [16,24], [42,45], [45,46], [11,6], [11,4], [39,45], [11,3], [48,47], [11,1], [38,45], [14,24], [10,1], [12,20], [40,47], [53,49], [9,8], [48,49], [11,20], [35,46], [26,41], [12,24], [10,19], [30,45], [9,19], [53,51], [31,46], [17,35], [6,5], [21,40], [7,18], [20,40], [44,52], [53,53], [5,5], [54,53], [42,52], [5,3], [12,31], [14,34], [30,48], [21,42], [28,47], [6,18], [44,53], [14,35], [5,18], [5,19], [51,55], [6,24], [31,51], [21,45], [9,34], [2,18], [10,38], [26,52], [6,33], [11,41], [5,33], [8,38], [20,50], [4,33], [7,38], [3,33], [4,36], [4,38], [2,36], [17,53], [4,40], [3,43], [2,43], [1,43], [2,48]]";

        long start = System.currentTimeMillis();
        List<BoardPoint> positions = gt.getByCloseness(gameBoard, myCurPos);
        long time = System.currentTimeMillis() - start;
        String pos_str = positions.toString();
        System.out.println(gameBoard.getMyPosition());
        assertEquals(compare_pos, pos_str);
        System.out.println("Seconds elapsed: " + (float) (time / 1000));
    }
}
