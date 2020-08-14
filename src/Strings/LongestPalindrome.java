// LeetCode 409. Longest Palindrome
// Given a string which consists of lowercase or uppercase letters, find the length of the longest palindrome
// built with those letters. Palindromes are case sensitive. "Aa" is not a palindrome.
//
// Example:
// Input: "abccccdd"    Output: 7   "dccaccd"
//
// Intuition:
// We don't need to actually create palindromes here. By definition, a palindrome will always consist an even
// frequency of all letters except for the middle one (if necessary)
// So we can have palindromes that look like this:
// ccbbabbcc = 1a, 4b, 4c
// ccbbaabbcc = 2a, 4b, 4c
// We need to count the frequency of characters and add all even #s to the total.
// Add the next lowest even # for all odd numbers. At the end we check if we have any leftover characters. If we do, add 1.
//
// Growth functions:
// O(n) runtime: need to touch all characters in the string and iterate over all entries in the map
// O(1) space: Because we are restricted to only lowercase/uppercase characters, the space used will be constant (26 +26)

package Strings;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {

    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        // Add all characters to map
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            // If even freq: all chars contribute to the longest palindrome
            // If odd freq: freq - 1 # of chars contribute to the longest palindrome
            ans += val % 2 == 0 ? val : val - 1;
        }

        // Add a leftover odd character if available
        return ans == s.length() ? ans : ans + 1;
    }

    // Faster than adding values to a map, but the concept is the same
    public int longestPalindromeV2(String s) {
        int[] freq = new int[56]; // hold lowercase and uppercase chars only

        for (char c : s.toCharArray()) {
            if (c > 'Z')
                freq[c - 'a' + 26]++;   // lowercase
            else
                freq[c - 'A']++;        // uppercase

        }

        int singles = 0;    // stores the # of single freq chars
        for (int i : freq) {
            if (i % 2 != 0)
                singles++;
        }

        return singles > 1 ? s.length() - singles + 1 : s.length();
    }
}
