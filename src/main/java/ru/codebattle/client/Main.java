package ru.codebattle.client;

import ru.codebattle.client.api.GameBoard;
import ru.codebattle.client.api.LoderunnerAction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class Main {

    private static final String SERVER_ADDRESS = "https://dojorena.io/codenjoy-contest/board/player/dojorena366?code=5032018246377952198";

    public static void main(String[] args) throws IOException {
        ReconnectableLodeRunnerClientWrapper client = new ReconnectableLodeRunnerClientWrapper(SERVER_ADDRESS, Main::doAction);

        client.run();

        System.in.read();

        client.initiateExit();
    }

    private static LoderunnerAction doAction(GameBoard gameBoard) {
        Random random = new Random(System.currentTimeMillis());
        return LoderunnerAction.values()[random.nextInt(LoderunnerAction.values().length)];
    }
}
