
public class SpellChecker {


	public static void main(String[] args) {
		// String word = args[0];
		// int threshold = Integer.parseInt(args[1]);
		String word = "hello";
		int threshold = 1;
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 0) {
			return "";
		}

		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		String newWord1 = word1.toLowerCase();
		String newWord2 = word2.toLowerCase();
		if (newWord1.length() == 0) {
			return newWord2.length();
			
		}

		if (newWord2.length() == 0) {
			return newWord1.length();
		}

		if(newWord1.charAt(0) == newWord2.charAt(0)) {
			return levenshtein(tail(newWord1), tail(newWord2));
		}

		int levTailAAndB = levenshtein(tail(newWord1), newWord2);
		int levTailBAndA = levenshtein(newWord1, tail(newWord2));
		int levTailAAndTailB = levenshtein(tail(newWord1), tail(newWord2));

		return 1 + Math.min(Math.min(levTailAAndB,levTailBAndA), levTailAAndTailB);
	}
	public static String[] readDictionary(String fileName) {
		In in = new In(fileName);
		return in.readAllStrings();
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minDistance = levenshtein(word, dictionary[0]);
		int index = 0;
		int temp;
		for (int i = 1; i < dictionary.length; i++) {
			temp = levenshtein(word, dictionary[i]);
			if (temp < minDistance) {
				minDistance = temp;
				index = i;
			}
		}

		if (minDistance > threshold) {
			return word;
		}

		return dictionary[index];
	}

}
