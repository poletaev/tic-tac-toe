package selfplay;

import agents.RandomAgent;
import base.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tictacktoe.TTAction;
import tictacktoe.TTGame;
import tictacktoe.TTState;

public class SelfPlay {

    private static final Logger logger = LoggerFactory.getLogger(SelfPlay.class);

    public static void main(String[] args) {

        final int numGames = 1000;
        final int[] results = new int[3];

        for (int n = 0; n < numGames; n++) {
            final RandomAgent<TTAction, TTGame, TTState> agent1 = new RandomAgent<>();
            final RandomAgent<TTAction, TTGame, TTState> agent2 = new RandomAgent<>();
            final TTGame game = new TTGame(new Agent[]{agent1, agent2});
            results[game.run()]++;
        }

        logger.info("RESULTS:\nties: {}\nplayer1: {}\nplayer2: {}", results[0], results[1], results[2]);

    }

}
