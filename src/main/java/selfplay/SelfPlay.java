package selfplay;

import agents.MinimaxAgent;
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

//  Random vs Random
//            ties: 115
//            player1: 592
//            player2: 293
//            final RandomAgent<TTAction, TTGame, TTState> agent1 = new RandomAgent<>();
//            final RandomAgent<TTAction, TTGame, TTState> agent2 = new RandomAgent<>();

//            ties: 32
//            player1: 799
//            player2: 169
//            final MinimaxAgent<TTAction, TTGame, TTState> agent1 = new MinimaxAgent<>(10, 1);
//            final RandomAgent<TTAction, TTGame, TTState> agent2 = new RandomAgent<>();


//  Random vs MiniMax (depth = 10)
//            ties: 41
//            player1: 500
//            player2: 459
//            final RandomAgent<TTAction, TTGame, TTState> agent1 = new RandomAgent<>();
//            final MinimaxAgent<TTAction, TTGame, TTState> agent2 = new MinimaxAgent<>(10, 2);


//  MiniMax (depth = 2) vs Random
//            ties: 104
//            player1: 865
//            player2: 31
            final MinimaxAgent<TTAction, TTGame, TTState> agent1 = new MinimaxAgent<>(2, 1);
            final RandomAgent<TTAction, TTGame, TTState> agent2 = new RandomAgent<>();

//  Random vs MiniMax (depth = 2)
//            ties: 366
//            player1: 99
//            player2: 535
//            final RandomAgent<TTAction, TTGame, TTState> agent1 = new RandomAgent<>();
//            final MinimaxAgent<TTAction, TTGame, TTState> agent2 = new MinimaxAgent<>(2, 2);

//  MiniMax (depth = 3) vs Random
//            ties: 46
//            player1: 748
//            player2: 206
//            final MinimaxAgent<TTAction, TTGame, TTState> agent1 = new MinimaxAgent<>(3, 1);
//            final RandomAgent<TTAction, TTGame, TTState> agent2 = new RandomAgent<>();

//  Random vs MiniMax (depth = 3)
//            ties: 46
//            player1: 516
//            player2: 438
//            final RandomAgent<TTAction, TTGame, TTState> agent1 = new RandomAgent<>();
//            final MinimaxAgent<TTAction, TTGame, TTState> agent2 = new MinimaxAgent<>(3, 2);

            final TTGame game = new TTGame(new Agent[]{agent1, agent2});
            results[game.run()]++;
        }

        logger.info("RESULTS:\nties: {}\nplayer1: {}\nplayer2: {}", results[0], results[1], results[2]);

    }

}
