import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PalindromeInfo {
    String palindrome;
    int startIndex;
    int length;

    public PalindromeInfo(String palindrome, int startIndex) {
        this.palindrome = palindrome;
        this.startIndex = startIndex;
        this.length = palindrome.length();
    }
}

public class LongestPalindromesFinder {
    public static List<PalindromeInfo> findLongestPalindromes(String input) {
        Set<String> uniquePalindromes = new HashSet<>();
        List<PalindromeInfo> palindromeInfos = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            // Find odd-length palindromes (centered at i)
            expandFromCenter(input, i, i, uniquePalindromes, palindromeInfos);
            // Find even-length palindromes (centered between i and i + 1)
            expandFromCenter(input, i, i + 1, uniquePalindromes, palindromeInfos);
        }

        // Sort palindromes by length in descending order, and pick top 3
        palindromeInfos.sort(Comparator.comparingInt((PalindromeInfo p) -> p.length).reversed());
        
        return palindromeInfos.size() > 3 ? palindromeInfos.subList(0, 3) : palindromeInfos;
    }

    private static void expandFromCenter(String input, int left, int right, Set<String> uniquePalindromes,
                                         List<PalindromeInfo> palindromeInfos) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            String palindrome = input.substring(left, right + 1);
            if (uniquePalindromes.add(palindrome)) {
                palindromeInfos.add(new PalindromeInfo(palindrome, left));
            }
            left--;
            right++;
        }
    }

    public static void main(String[] args) {
        String input = "bananas are racecars and madams";
        List<PalindromeInfo> longestPalindromes = findLongestPalindromes(input);

        System.out.println("The 3 longest unique palindromic substrings are:");
        for (PalindromeInfo info : longestPalindromes) {
            System.out.println("Palindrome: \"" + info.palindrome + "\", Start Index: " + info.startIndex + ", Length: " + info.length);
        }
    }
}
