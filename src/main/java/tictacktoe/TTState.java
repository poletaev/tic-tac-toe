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
            b.append("| ");
            for (int y = 0; y < 3; y++) {
                switch (grid[x][y]) {
                    case 2 -> b.append('O');
                    case 1 -> b.append('X');
                    default -> b.append(' ');
                }
                b.append(" | ");
            }
            b.append("\n");
        }
        return b.toString();
    }

    public boolean isWinner(final int agentIdx) {
        for (int x = 0; x < 3; x++) {
            boolean isRow = true;
            for (int y = 0; y < 3; y++) {
                isRow &= grid[x][y] == agentIdx;
            }
            if (isRow)
                return true;
        }

        for (int y = 0; y < 3; y++) {
            boolean isColumn = true;
            for (int x = 0; x < 3; x++) {
                isColumn &= grid[x][y] == agentIdx;
            }
            if (isColumn)
                return true;
        }

        boolean isDiag = true;
        for (int y = 0; y < 3; y++) {
            isDiag &= grid[y][y] == agentIdx;
        }
        if (isDiag)
            return true;

        isDiag = true;
        for (int y = 0; y < 3; y++) {
            isDiag &= grid[2 - y][y] == agentIdx;
        }
        if (isDiag)
            return true;

        return false;
    }


    public boolean isGameOver() {
        if (isWinner(1) || isWinner(2))
            return true;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y] == 0) return false;
            }
        }
        return true;
    }
}
