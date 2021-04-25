package ru.codebattle.client.bot.algorithms;

import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.LoderunnerAction;
import ru.codebattle.client.bot.algorithms.astar.Node;

import java.util.LinkedList;

public abstract class PathConstructor {

    public static LoderunnerAction[] createPath(BoardPoint myCurPos, LinkedList<Node> path){
        if(path.size() < 1)
            return new LoderunnerAction[]{LoderunnerAction.DO_NOTHING};
        LoderunnerAction[] actions = new LoderunnerAction[path.size()-1];
        path.poll();
        for(int i = 0; i < actions.length; i++) {
            Node node = path.poll();
            if(node != null) {
                if (node.getX() > myCurPos.getX()) {
                    actions[i] = LoderunnerAction.GO_RIGHT;
                } else if (node.getX() < myCurPos.getX()) {
                    actions[i] = LoderunnerAction.GO_LEFT;
                } else if (node.getY() > myCurPos.getY()) {
                    actions[i] = LoderunnerAction.GO_UP;
                } else if (node.getY() < myCurPos.getY()) {
                    actions[i] = LoderunnerAction.GO_DOWN;
                }
            }
        }
        return actions;
    }
}
