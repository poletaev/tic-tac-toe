package tictacktoe;

import base.GameState;

import java.util.ArrayList;

public class TTState implements GameState<TTAction> {

    private final int[][] grid = new int[3][3];

    public TTState() {}

    public TTState(final TTState rhs) {
        for (int x = 0; x < 3; x++) {
            System.arraycopy(rhs.grid[x], 0, grid[x], 0, 3);
        }
    }

    @Override
    public TTAction[] getLegalActions() {
        final ArrayList<TTAction> candidates = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y] == 0) {
                    candidates.add(new TTAction(x, y));
                }
            }
        }
        return candidates.toArray(new TTAction[candidates.size()]);
    }

    @Override
    public GameState<TTAction> applyAction(TTAction action, final int agentIdx) {
        final TTState n = new TTState(this);
        n.grid[action.x][action.y] = agentIdx;
        return n;
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                b.append(grid[x][y]).append(" | ");
            }
            b.append("\n");
        }
        return b.toString();
    }

    public boolean isGameOver() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y] == 0) return false;
            }
        }
        return true;
    }
}
