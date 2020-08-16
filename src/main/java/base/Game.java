package base;

public interface Game <A extends Action, S extends GameState> {

    A[] getLegalActions(final S current);

    S applyAction(final S current, final A action);
}
