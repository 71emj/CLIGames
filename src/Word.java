import java.util.*;

public class Word {
	private Letter[] word;
	
	public String name;
	public Set<Character> dictionary = new HashSet<Character>();

	public Word(String word) {
		Letter[] letters = new Letter[word.length()];

		for (int i = 0; i < word.length(); i++) {
			letters[i] = new Letter(word.charAt(i));
			
			char lowerChar = Character.toLowerCase(word.charAt(i));
			if (!isAlphabet(lowerChar)) {
				continue;
			}
			this.dictionary.add(lowerChar);
		}
		this.name = word;
		this.word = letters;
	}

	public StringBuilder showQuizz() {
		StringBuilder quizz = new StringBuilder("\n");
		for (int i = 0; i < name.length(); i++) {
			activateLetters(i);
			quizz.append(word[i].display()).append(' ');
		}
		return quizz;
	}
	
	private boolean isAlphabet(char inQuestion) {
		return (inQuestion >= 65 && inQuestion <= 90) || 
			   (inQuestion >= 97 && inQuestion <= 122) ;
	}

	private void activateLetters(int i) {
		char curLetter = Character.toLowerCase(word[i].alphabet);
		if (dictionary.contains(curLetter)) {
			return;
		}
		word[i].activate();
	}
}
