package base;

public interface Agent <A extends Action, G extends Game<A, S>, S extends GameState<A>> {

    A getAnswer(final S current);
}
