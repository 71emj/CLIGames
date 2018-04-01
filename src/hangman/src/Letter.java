public class Letter {
	private boolean active = false;
	private char underscore = '_';
	public char alphabet;
	
	public Letter(char alphabet) {
		this.alphabet = alphabet;
	}
	
	public char display() {
		return this.active ? this.alphabet : this.underscore;
	}
	
	public Letter activate() {
		this.active = true;
		return this;
	}
}
