import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
	public static void main(String[] args) {
		System.out.println("Welcome to a game of dice!");
		
		String player1 = prompt("Player1: ");
		String player2 = prompt("Player2: ");
		int totalRounds = Integer.parseInt(prompt("Rounds of Game: "));
		
		String[] gamers = { player1, player2 };
		Board game = new Board(totalRounds, gamers);
		game.start();
	}
	
	public static String prompt(String message) {
		BufferedReader buffy = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(message);
		String answer = "";
		try {
			answer = buffy.readLine();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return answer;
	}
}
