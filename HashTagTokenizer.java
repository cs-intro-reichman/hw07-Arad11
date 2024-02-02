

public class HashTagTokenizer {

	public static void main(String[] args) {

		// String hashTag = args[0];
		String hashTag = "iloverecursion";
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		In in = new In(fileName);
		return in.readAllStrings();
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for(int i = 0; i < dictionary.length; i++) {
			var a = dictionary[i];
			if(word.equals(a)) {
				return true;
			}
		}

		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		String word = "";
		String wordToPrint = "";

        for (int i = 0; i < N; i++) {
			word = word + hashtag.charAt(i);
 			if (existInDictionary(word, dictionary)) { // inside of this if statement I am looking for the longest word starting with that prefix
				wordToPrint = word;                    // in order to ignore it, I just need to stop running and concatinating the string.
			}
		}
		
		if (wordToPrint.length() >= 1) {
			System.out.println(wordToPrint);
			breakHashTag(hashtag.substring(wordToPrint.length()), dictionary);
		} else {
			breakHashTag(hashtag.substring(1), dictionary);
		}
	}

}
