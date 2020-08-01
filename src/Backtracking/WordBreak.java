/*
 LeetCode 139. Word Break
 Given a non-empty string s and a dictionary wordDict that contains a list of non-empty words, determine if the string
 can be space-separated to construct a sentence where each word is a valid dictionary word.

 Example:
 Input: s = "leetcode", wordDict=["leet", "code"]
 Output: True

 Intuition:
 We only need to find one valid solution that successfully breaks the string into valid dictionary words. The idea is to
 find the valid dictionary word at the front of the string, and recurse on the substring. We continue processing the substring
 until we hit the base case, were s.length() == 0, or we cannot find a valid dictionary word in the substring.
 We can break this into 3 actions:
     1. Valid word found. Recurse on substring that excludes found word
     2. If substring does not contain a valid word, return false and backtrack
     3. If we continue recursing and substring lenth is 0, return true

 Growth Functions:
 O(n^2) runtime, where n is the length of the string:
 In the worst case, we may traverse the entire string once and not come up with a
 valid solution. We then backtrack and check a different combination of dictionary words to reach the solution. The HashMap helps speed up
 runtime by memoizing previous results to reduce recalculation.
 Ex. s = "aaaaab"       wordDict = ["a", "aa", "aaa", "aaaa", "aaaaa"]
 In the first pass, we will build "aaaaab" by choosing "a" 5 times. After we get the substring "b", we realize that this does not lead
 to a valid solution. We backtrack and memoize that "b" led to false. Then we try "aa" on "ab", which leads to false, "aaa" on "aab", etc.
 Without memoization, we would be recalculating a large number of times

 O(n) space:
 We use a HashMap to record results on previous substrings, and we incur space on the call stack while recursing on each substring.

*/

package Backtracking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, wordDict, new HashMap<>());
    }

    public boolean wordBreak(String s, List<String> wordDict, Map<String, Boolean> memo) {
        // Return result of previously seen substrings
        if (memo.containsKey(s)) // O(1)
            return memo.get(s);

        // Base case
        if (s.length() == 0) return true;

        // Iterate through all words in dictionary
        for (String word : wordDict) { // O(n) where n is size of wordDict
            if (s.startsWith(word)) {
                // If substring starts with the word, recurse
                boolean validBreak = wordBreak(s, wordDict, memo); // O(
                // After recursion, memoize the result of all prior substrings. This prevents TLE on certain inputs
                memo.put(s, validBreak);
                // If the complete recursion reaches the base case and returns true all the way up, we found a valid solution
                // Otherwise, we backtrack and try a different substring + dictionary word combination
                if (validBreak)
                    return true;
            }
        }
        return false;
    }
}
