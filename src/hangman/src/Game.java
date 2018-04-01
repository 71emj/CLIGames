import java.util.*;

public class Game {
	private int count;
	private Scanner input = new Scanner(System.in);
	private Set<Character> attempted = new HashSet<Character>();
	private Word $;
	
	public Game(String word) {
		$ = new Word(word);
		count = 7;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Hangman Game CLI!!\n");
		System.out.println("Rules: \n1. You have 7 tries for each word");
		System.out.println("2. Upper and lower case letters are the same..\n");
		
		Game.gameStart();
	}
	
	protected static void gameStart() {
		// initialization will look into a dictionary, as random source of word
		Game hangman = new Game("kiTten&");
		System.out.println(hangman.$.showQuizz());
		hangman.gamePlay();
	}
	
	private void gameEnd() {
		System.out.println("Would you like to restart?");
		if (input.next().matches("[yY]")) {
			gameStart();
			return;
		}
		System.out.println("See you next time ( ^_^)");
	}
	
	private void gamePlay() {
		System.out.printf("You still have %d chances left\n", count);
		gameHandler( wordHandler(input.next().charAt(0)) );
		input.close();
	}
	
	private void gameHandler(StringBuilder result) {
		System.out.println(result);
		if (!gameState()) {
			gamePlay();
			return;
		}
		gameEnd();
	}

	private StringBuilder wordHandler(char input) {
		char lowerChar = Character.toLowerCase(input);
		if ($.dictionary.contains(lowerChar)) {
			$.dictionary.remove(lowerChar);
		}
		else {
			count += attempted.contains(lowerChar) ? 0 : -1;
		}
		attempted.add(lowerChar);
		return $.showQuizz();
	}
	
	private boolean gameState() {
		if ($.dictionary.size() == 0) {
			System.out.println("Congrats, you win!!");
			return true;
		}
		if (count == 0) {
			System.out.println("You lose~~");
			System.out.printf("The answer is: %s\n", $.name);
			return true;
		}
		StringBuilder attempts = new StringBuilder();
		for (Character c : attempted) {
			attempts.append(c).append(' ');
		}
		System.out.printf("So far you've tried: %s\n\n", attempts);
		return false;
	}
}
