package tictacktoe;

import agents.RandomAgent;
import base.Agent;
import base.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TTGame implements Game<TTAction, TTState> {

    private static final Logger logger = LoggerFactory.getLogger(TTGame.class);
    private TTState currentState;
    private int currentAgentIdx = 1;
    private final Agent<TTAction, TTGame, TTState>[] agents;

    public TTGame(final Agent<TTAction, TTGame, TTState>[] agents) {
        if (agents.length != 2)
            throw new IllegalStateException("incorrect number of players!");

        currentState = new TTState();
        this.agents = agents;
    }

    @Override
    public boolean isGameOver(TTState current) {
        return current.isGameOver();
    }

    @Override
    public int run() {

        while (!isGameOver(currentState)) {
            currentAgentIdx = getNext();
            final TTAction action = agents[currentAgentIdx - 1].getAnswer(currentState);
            logger.debug("agent {} action is {}", currentAgentIdx, action);
            currentState = (TTState) currentState.applyAction(action, currentAgentIdx);
            logger.debug("new state is:\n{}", currentState);
        }

        return currentAgentIdx;
    }

    private int getNext() {
        if (currentAgentIdx == 1) return 2;
        else return 1;
    }


    public static void main(String[] args) {

        final RandomAgent<TTAction, TTGame, TTState> agent1 = new RandomAgent<>();
        final RandomAgent<TTAction, TTGame, TTState> agent2 = new RandomAgent<>(3);

        final TTGame game = new TTGame(new Agent[]{agent1, agent2});
        final int winner = game.run();
        logger.info("winner is {}", winner);

    }
}
