package base;

public interface Game <A extends Action, S extends GameState<A>> {

    public boolean isGameOver(final S current);

    public int run();

}
