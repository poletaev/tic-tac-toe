package agents;

import base.Action;
import base.Agent;
import base.Game;
import base.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class ExpectimaxAgent<A extends Action, G extends Game<A, S>, S extends GameState<A>> implements Agent<A, G, S> {

    private static final Logger logger = LoggerFactory.getLogger(ExpectimaxAgent.class);

    private static class Result<A> {
        public Float value;
        public ArrayList<A> actions;

        public Result(final Float value, final ArrayList<A> actions) {
            this.value = value;
            this.actions = actions;
        }
    }

    private final int maxDepth;
    private final int maxAgentIdx;
    private final Random rnd;
    private final boolean isChooseRandom;

    public ExpectimaxAgent(int maxDepth, int maxAgentIdx, boolean isChooseRandom) {
        this.maxDepth = maxDepth;
        this.maxAgentIdx = maxAgentIdx;
        this.isChooseRandom = isChooseRandom;
        rnd = new Random();
    }

    public ExpectimaxAgent(int maxDepth, int maxAgentIdx) {
        this(maxDepth, maxAgentIdx, false);
    }

    @Override
    public A getAnswer(final S current) {
        final ArrayList<A> paths = new ArrayList<>();
        final Result<A> result = value(current, maxAgentIdx, 0, paths);

        logger.debug("EXPECTIMAX: {}", result.actions.get(0));
        return result.actions.get(0);
    }

    private Result<A> value(final S state, final int agentIdx,
                            final int currentDepth, final ArrayList<A> path) {

        if (state.isGameOver() || currentDepth == maxDepth)
            return new Result<>(evaluationFunction(state), path);
        else {
            int newDepth = currentDepth;
            if (agentIdx == maxAgentIdx) newDepth++;

            int nextAgent = getNext(agentIdx);
            if (nextAgent == maxAgentIdx)
                return maxValue(state, nextAgent, newDepth, path);
            else
                return expValue(state, nextAgent, newDepth, path);
        }
    }

    private Result<A> maxValue(final S state, final int agentIdx,
                               final int currentDepth, final ArrayList<A> path) {
        final A[] legalMoves = state.getLegalActions();
        final HashMap<Float, ArrayList<A>> value2action = new HashMap<>();
        float v = Float.MIN_VALUE;

        for (final A legalMove : legalMoves) {
            final ArrayList<A> actions = new ArrayList<>(path);
            actions.add(legalMove);
            final S newState = (S) state.applyAction(legalMove, agentIdx);
            final Result<A> result = value(newState, getNext(agentIdx), currentDepth + 1, actions);
            value2action.put(result.value, result.actions);
            v = Float.max(v, result.value);
        }
        return new Result<A>(v, value2action.get(v));
    }

    private Result<A> expValue(final S state, final int agentIdx,
                               final int currentDepth, final ArrayList<A> path) {
        final A[] legalMoves = state.getLegalActions();
        final HashMap<Float, ArrayList<A>> value2action = new HashMap<>();
        float v = 1F;
        //todo: not all actions are equally probable
        final float prob = (float) (1.0 / legalMoves.length);

        for (final A legalMove : legalMoves) {
            final ArrayList<A> actions = new ArrayList<>(path);
            actions.add(legalMove);
            final S newState = (S) state.applyAction(legalMove, agentIdx);
            final Result<A> result = value(newState, getNext(agentIdx), currentDepth + 1, actions);
            value2action.put(result.value * prob, result.actions);
            v += result.value * prob;
        }
        if (isChooseRandom) {
            final Float[] values = value2action.keySet().toArray(new Float[0]);
            final int idx = rnd.nextInt(values.length);
            return new Result<A>(v, value2action.get(values[idx]));
        } else {
            float min = Collections.min(value2action.keySet());
            return new Result<A>(v, value2action.get(min));
        }
    }


    private int getNext(final int currentAgentIdx) {
        if (currentAgentIdx == 1) return 2;
        else return 1;
    }


    public Float evaluationFunction(final S current) {
        if (current.isWinner(maxAgentIdx))
            return 1F;
        else if (current.isWinner(getNext(maxAgentIdx)))
            return -1F;
        else
            return 0F;
    }
}
