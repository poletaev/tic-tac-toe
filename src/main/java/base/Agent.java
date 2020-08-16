package base;

public interface Agent <A extends Action, G extends Game<A, S>, S extends GameState> {

    A getAnswer(final G game, final S current);
}
