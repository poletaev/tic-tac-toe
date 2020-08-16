package agents;

import base.Action;
import base.Agent;
import base.Game;
import base.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RandomAgent<A extends Action, G extends Game<A, S>, S extends GameState<A>> implements Agent<A, G, S> {

    private static final Logger logger = LoggerFactory.getLogger(RandomAgent.class);

    private final Random rnd;

    public RandomAgent() {
        rnd = new Random();
    }

    public RandomAgent(final int seed) {
        rnd = new Random(seed);
    }

    @Override
    public A getAnswer(S current) {
        final A[] legalMoves = current.getLegalActions();
        final int index = rnd.nextInt(legalMoves.length);
        return legalMoves[index];
    }
}
