package ru.codebattle.client.bot.algorithms.bestfirst;

import lombok.Getter;
import ru.codebattle.client.api.BoardElement;

public enum BoardElementWeight {

    //TODO: Implement appropriate weights

    NONE(2),                    // Пустое место – по которому может двигаться герой

    BRICK(5),                   // Cтена в которой можно просверлить дырочку слева или справа от героя
    // (в зависимости от того, куда он сейчас смотрит)

    PIT_FILL_1(2),              // Стена со временем зарастает. Когда процес начинается - мы видим таймер
    PIT_FILL_2(2),
    PIT_FILL_3(Integer.MAX_VALUE),
    PIT_FILL_4(Integer.MAX_VALUE),

    UNDESTROYABLE_WALL(Integer.MAX_VALUE),      // Неразрушаемая стена - в ней ничего просверлить не получится

    DRILL_PIT(1),               // В момент сверления мы видим процесс так

    // Боты-охотники
    ENEMY_LADDER(Integer.MAX_VALUE),
    ENEMY_LEFT(Integer.MAX_VALUE),
    ENEMY_RIGHT(Integer.MAX_VALUE),
    ENEMY_PIPE_LEFT(Integer.MAX_VALUE),
    ENEMY_PIPE_RIGHT(Integer.MAX_VALUE),
    ENEMY_PIT(Integer.MAX_VALUE),

    YELLOW_GOLD(0),             // Горстка золота жёлтого цвета
    GREEN_GOLD(-1),              // Зелёная горстка золота
    RED_GOLD(-2),                // Золото красного цвета

    // Твой герой в зависимости от того, чем он сейчас занят отображается следующими символами
    HERO_DIE(0),                // Герой переживает процесс умирания
    HERO_DRILL_LEFT(0),         // Герой сверлит слева от себя
    HERO_DRILL_RIGHT(0),        // Герой сверлит справа от себя
    HERO_LADDER(0),             // Герой находится на лестнице
    HERO_LEFT(0),               // Герой бежит влево
    HERO_RIGHT(0),              // Герой бежит вправо
    HERO_FALL_LEFT(0),          // Герой падает, смотря влево
    HERO_FALL_RIGHT(0),         // Герой падает, смотря вправо
    HERO_PIPE_LEFT(0),          // Герой ползёт по трубе влево
    HERO_PIPE_RIGHT(0),         // Герой ползёт по трубе вправо

    // Тоже твой герой, но под таблеткой тени:
    HERO_SHADOW_DIE(0),         // Герой-тень переживает процесс умирания
    HERO_SHADOW_DRILL_LEFT(0),  // Герой-тень сверлит слева от себя
    HERO_SHADOW_DRILL_RIGHT(0), // Герой-тень сверлит справа от себя
    HERO_SHADOW_LADDER(0),      // Герой-тень находится на лестнице
    HERO_SHADOW_LEFT(0),        // Герой-тень бежит влево
    HERO_SHADOW_RIGHT(0),       // Герой-тень бежит вправо
    HERO_SHADOW_FALL_LEFT(0),   // Герой-тень падает, смотря влево
    HERO_SHADOW_FALL_RIGHT(0),  // Герой-тень падает, смотря вправо
    HERO_SHADOW_PIPE_LEFT(0),   // Герой-тень ползёт по трубе влево
    HERO_SHADOW_PIPE_RIGHT(0),  // Герой-тень ползёт по трубе вправо

    // Герои других игроков отображаются так
    OTHER_HERO_DIE(2),          // Герой переживает процесс умирания
    OTHER_HERO_DRILL_LEFT(6),   // Герой сверлит слева от себя
    OTHER_HERO_DRILL_RIGHT(6),  // Герой сверлит справа от себя
    OTHER_HERO_LADDER(6),       // Герой находится на лестнице
    OTHER_HERO_LEFT(6),         // Герой бежит влево
    OTHER_HERO_RIGHT(6),        // Герой бежит вправо
    OTHER_HERO_FALL_LEFT(6),    // Герой падает, смотря влево
    OTHER_HERO_FALL_RIGHT(6),   // Герой падает, смотря вправо
    OTHER_HERO_PIPE_LEFT(6),    // Герой ползёт по трубе влево
    OTHER_HERO_PIPE_RIGHT(6),   // Герой ползёт по трубе вправо

    // А если герои других игроков под таблеткой тени, то так
    OTHER_HERO_SHADOW_DIE(2),         // Другой герой-тень переживает процесс умирания
    OTHER_HERO_SHADOW_DRILL_LEFT(Integer.MAX_VALUE),  // Другой герой-тень сверлит слева от себя
    OTHER_HERO_SHADOW_DRILL_RIGHT(Integer.MAX_VALUE), // Другой герой-тень сверлит справа от себя
    OTHER_HERO_SHADOW_LEFT(Integer.MAX_VALUE),        // Другой герой-тень находится на лестнице
    OTHER_HERO_SHADOW_RIGHT(Integer.MAX_VALUE),       // Другой герой-тень бежит влево
    OTHER_HERO_SHADOW_LADDER(Integer.MAX_VALUE),      // Другой герой-тень бежит вправо
    OTHER_HERO_SHADOW_FALL_LEFT(Integer.MAX_VALUE),   // Другой герой-тень падает, смотря влево
    OTHER_HERO_SHADOW_FALL_RIGHT(Integer.MAX_VALUE),  // Другой герой-тень падает, смотря вправо
    OTHER_HERO_SHADOW_PIPE_LEFT(Integer.MAX_VALUE),   // Другой герой-тень ползёт по трубе влево
    OTHER_HERO_SHADOW_PIPE_RIGHT(Integer.MAX_VALUE),  // Другой герой-тень ползёт по трубе вправо

    LADDER(2),              // Лестница - по ней можно перемещаться по уровню
    PIPE(2),                // Труба - по ней так же можно перемещаться по уровню, но только горизонтально

    PORTAL(1),              // Портал - позволяет телепортироваться на карте в иное место на карте

    SHADOW_PILL(0);         // Таблетка тени - наделяют героя дополнительными способностями

    @Getter
    private final int weight;

    BoardElementWeight(int weight) {
        this.weight = weight;
    }

}
