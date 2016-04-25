
package didyoumean;

public class DidYouMean{

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary(new String[]{"cherry", "pineapple", "melon", "strawberry", "apple"});
        System.out.println(dictionary.findMostSimilar("appl"));
    }

}

class Dictionary {

    private final String[] words;

    public Dictionary(String[] words) {
        this.words = words;
    }

    public String findMostSimilar(String checkWord) {
        String result = words[0];
        for (int i = 0; i < words.length - 1; i++) {
            if (levenshtein(result, checkWord) > levenshtein(words[i + 1], checkWord)) {
                result = words[i + 1];
            }
        }
        return result;
    }

    public  int levenshtein(String word1, String word2) {
        int w1length = word1.length() + 1;
        int w2length = word2.length() + 1;

        int[] cost = new int[w1length];
        int[] newcost = new int[w1length];

        for (int i = 0; i < w1length; i++) {
            cost[i] = i;
        }

        for (int j = 1; j < w2length; j++) {

            newcost[0] = j;

            for (int i = 1; i < w1length; i++) {
                int match;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    match = 0;
                } else {
                    match = 1;
                }
                int costReplace = cost[i - 1] + match;
                int costInsert = cost[i] + 1;
                int costDelete = newcost[i - 1] + 1;

                newcost[i] = Math.min(Math.min(costInsert, costDelete), costReplace);
            }

            int[] change = cost;
            cost = newcost;
            newcost = change;
        }
        return cost[w1length - 1];
    }

}
