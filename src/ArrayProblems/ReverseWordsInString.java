package ArrayProblems;// LeetCode 151. Reverse Words in a String
// Given an input string, reverse the string word by word.
// Ex.  Input: "  hello world!  "  The extra spaces are removed. Only 1 space between each word counts
//      Output: "world! hello"
// Intuition: Using StringBuilder and two pointers, we iterate through the string to reach towards the final answer
// Using the 'end' pointer, check for a space char. If found, check if the word length > 0.
// If it is, add it to StringBuilder and add an extra space. Move 'start' to the 'end' pointer
// If we found a multiple spaces, we keep moving 'start' forward until we don't find one
// If the next char is not a space, increment length. Move 'end' forward at each iteration
// If we reached the end of the string and we haven't copied the last word, copy it.
// If we reach the end of the string with length = 0, remove the trailing space we added earlier
// O(n^2) runtime: we make one pass through the entire array. Each insertion to the front of SBuilder is O(n).
// SBuilder insert is implemented as char[]. Worst case we shift n characters to complete the insertion
// O(n) space: StringBuilder class to hold the answer

public class ReverseWordsInString {

    public static String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        int start = 0; int end = 0; int len = 0;

        while (end < s.length()) {
            if(s.charAt(end) == ' ') {
                if (len > 0) {
                    ans.insert(0, s.substring(start, end));
                    ans.insert(0, " ");
                    len = 0;
                    start = end + 1;
                }
                else
                    start++;
            }
            else
                len++;
            end++;
        }

        if (len > 0) {
            ans.insert(0, s.substring(start, end));
        }

        if (ans.length() > 0 && ans.charAt(0) == ' ')
            ans.deleteCharAt(0);

        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "";

        System.out.println(reverseWords(s));
    }
}
