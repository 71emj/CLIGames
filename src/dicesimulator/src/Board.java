import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
	private ArrayList<String> roundRecord = new ArrayList<String>();
	private Map<String, Integer> scoreBoard = new HashMap<String, Integer>();
	public final Player[] players;
	public final int totalRounds;

	public Board(int totalRounds, String[] gamers) {
		this.totalRounds = totalRounds;
		this.scoreBoard.put("---", 0);
		this.players = new Player[gamers.length];
		for (int i = 0; i < gamers.length; i++) {
			this.players[i] = new Player(gamers[i]);
			this.scoreBoard.put(gamers[i], 0);
		}
	}

	public void start() {
		for (int i = 0; i < totalRounds; i++) {
			gameRecord(gamePlay(), i);
		}
		end();
	}

	private void end() {
		StringBuilder result = new StringBuilder();
		int len = roundRecord.size();
		for (int i = 0; i < len; i++) {
			result.append(String.format("%-4d", i + 1)).append(roundRecord.get(i));
		}
		System.out.println(result);
		System.out.println("Winner is...");
		
		int player1 = scoreBoard.get(players[0].name);
		int player2 = scoreBoard.get(players[1].name);
		System.out.println(player1 > player2 ? players[0].name : players[1].name);
	}

	private String gamePlay() {
		String winner = "";
		String highRoll = "";
		int highScore = 0;
		for (Player player : players) {
			int playerScore = player.start();
			if (playerScore > highScore) {
				winner = player.name;
				highRoll = player.getCombo();
				highScore = playerScore;
			} else if (playerScore == highScore) {
				winner = "---";
				highRoll += "/" + player.getCombo();
			}
		}
		updateScoreBoard(winner);
		return String.format("=> Winner: %-7s | Score: %-2d | Roll: %s\n", winner, highScore, highRoll);
	}

	private void gameRecord(String record, int round) {
		roundRecord.add(record);
	}

	private void updateScoreBoard(String winner) {
		scoreBoard.put(winner, scoreBoard.get(winner) + 1);
	}
}
