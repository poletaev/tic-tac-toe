package base;

public interface GameState<A extends Action> {

    A[] getLegalActions();

    GameState<A> applyAction(final A action, final int agentIdx);

}
