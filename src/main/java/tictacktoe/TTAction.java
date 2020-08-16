package tictacktoe;

import base.Action;

public class TTAction implements Action {

    public final int x;
    public final int y;
    public int agentIdx;

    public TTAction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public TTAction(final int x, final int y, final int agentIdx) {
        this(x, y);
        this.agentIdx = agentIdx;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")  ";
    }
}
