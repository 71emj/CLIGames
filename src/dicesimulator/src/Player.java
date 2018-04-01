import java.util.ArrayList;
import java.util.Arrays;

public class Player implements GameCues {
	private Dice dice = new Dice();
	private ArrayList<String> record = new ArrayList<String>();
	private int[] perRoll = new int[3];
	private int value; // value is tiered, ex 1 1 6 --> 7 (6 + 1 2 3)
	public final String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public int start() {
		if (reRoll(rollDice())) {
			return start();
		}
		return value;
	}
	
	public String getCombo() {
		return record.get(record.size() - 1);
	}

	private int[] rollDice() {
		for (byte i = 0; i < 3; i++) {
			perRoll[i] = dice.start();
		}
		record.add(Arrays.toString(perRoll));
		Arrays.sort(perRoll);
		return perRoll;
	}

	private boolean reRoll(int[] result) {
		if (result[1] == result[0] && result[1] == result[2]) {
			value = result[1] + 8;
			return false;
		}
		if (result[1] == result[0] || result[1] == result[2]) {
			value = Math.abs((result[0] + result[2]) - result[1] + 1);
			return false;
		}
		if (result[0] == 4 || result[2] == 3) {
			value = result[0] < 4 ? 1 : 8;
			return false;
		}
		return true;
	}
}
