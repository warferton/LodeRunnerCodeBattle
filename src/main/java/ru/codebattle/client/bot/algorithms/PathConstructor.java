package ru.codebattle.client.bot.algorithms;

import lombok.extern.slf4j.Slf4j;
import ru.codebattle.client.api.BoardPoint;
import ru.codebattle.client.api.LoderunnerAction;
import ru.codebattle.client.bot.algorithms.astar.Node;

import java.util.LinkedList;

@Slf4j
public abstract class PathConstructor {

    public static LinkedList<LoderunnerAction> createPath(BoardPoint myCurPos, LinkedList<Node> path){

        LinkedList<LoderunnerAction> actions = new LinkedList<>();

        if(path.size() < 1)
        {
            if(myCurPos.getX() > 28)
                actions.push(LoderunnerAction.GO_LEFT);
            else
                actions.push(LoderunnerAction.GO_RIGHT);
        }

        Node node ;//= path.poll();
        while(path.size() > 0){
            node = path.poll();
            if (node.getX() > myCurPos.getX()) {
                actions.add(LoderunnerAction.GO_RIGHT);
            }
            else if (node.getX() < myCurPos.getX()) {
                actions.add(LoderunnerAction.GO_LEFT);
            }
            else if (node.getY() < myCurPos.getY()) {
                actions.add(LoderunnerAction.GO_UP);
            }
            else if (node.getY() > myCurPos.getY()) {
                actions.add(LoderunnerAction.GO_DOWN);
            }
        }
        log.info("Commands input:" + actions);

        return actions;
    }
}
