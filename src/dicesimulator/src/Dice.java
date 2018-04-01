
public class Dice implements GameCues {
	private int value;
	
	public int start() {
		value = roll();
		return value;
	}
	
	private int roll() {
		return (int) (Math.random() * 6 + 1);
	}
}
