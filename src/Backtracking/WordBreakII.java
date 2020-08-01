/*
 LeetCode 140. Word Break II
 Given a non-empty string s and a dictionary wordDict that contains a list of non-empty words, add spaces between s
 to construct a sentence where each word is a valid dictionary word. Return all possible sentences
 The same word can be reused in multiple sentences. The dictionary will not contain duplicate words

 Example:
 s = "catsanddog"         wordDict = ["cat", "cats", "and", "sand", "dog"]
 Output: [ "cats and dog", "cat sand dog"]

 Intuition:
 To find all possible sentences, we need to break down the original string into smaller substrings
 We then recurse and find the answer to these smaller substrings. After each recursive call, we build up the final result
 which we pass back up the previous function call. We then add the word in front of the answer to the smaller substring
 to build the final sentence

 Visual Representation:
 pineapplepenapple

 *** This actually breaks into two cases. Creating an answer with "apple" and another with "applepen" ***
 pine -> wordBreak(applepenapple)
 pine -> apple -> wordBreak(penapple)
 pine -> apple -> pen -> wordBreak(apple)
 pine -> apple -> pen -> apple -> wordBreak("") Base Case -> return and build the sentence
 pine -> apple -> pen -> "apple"
 pine -> apple -> "pen apple"
 pine -> "apple pen apple"
 "pine apple pen apple"

 pine -> applepen -> wordBreak(apple)
 pine -> applepen -> apple -> wordBreak("") Base Case -> return and rebuild the sentence
 pine -> applepen -> "apple"
 pine -> "applepen apple"
 "pine apple pen apple"

 pineapple -> wordBreak(penapple)
 pineapple -> pen -> wordBreak(apple)
 pineapple -> pen -> apple -> wordBreak("") Base Case -> return and rebuild the sentence
 pineapple -> pen -> "apple"
 pineapple -> "pen apple"
 "pineapple pen apple"

 O(2^n) runtime: In the worst case, s="aaa" and wordDict=["a", "aa", "aaa"]. Each partition results in a possible sentence,
                 thus creating 2^n-1 partitions
                 Ex. ["a a a", "a aa", "aa a", "aaa"] 2^(3-1)=4
                 However, this does optimize against the s="aaab" case, where it figures out after 1 full recursive call
                 that the substring including "b" does not lead to any valid solutions
 O(2^n) space: In the worst case, we have passed in parameters similar to above, which would lead to 2^(n-1) solutions.
*/

package Backtracking;

import java.util.*;

public class WordBreakII {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        return wordBreakHelper(s, wordDict, new HashMap<>());
    }

    public static List<String> wordBreakHelper(String s, List<String> wordDict, Map<String, List<String>> memo) {
        // DP: use previous result if we already solved it
        if (memo.containsKey(s))
            memo.get(s);

        List<String> res = new ArrayList<>();

        // Base case if string is empty
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        // If this loop completes without any recursion, we know the substring does not contain any valid words
        // from wordDict. In that scenario we return an empty list.
        for (String word : wordDict) {
            // Check if the substring begins with any of the words in the dictionary
            if (s.startsWith(word)) {
                // Recurse on the substring that removes the word found in the dictionary
                List<String> subStrings = wordBreakHelper(s.substring(word.length()), wordDict, memo);

                // Add the sentences created by each substring to the result and return it to the function call a level above
                for (String sub : subStrings) {
                    // Check if this is the first time we are adding the sentence. If it is, we do not include a space before it
                    String optionalSpace = (sub.length() == 0) ? "" : " ";
                    res.add(word + optionalSpace + sub);
                }
            }
        }

        // Memoize the results for each substring. This will improve overall runtime
        memo.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));

        System.out.println(wordBreak(s, wordDict));
    }
}
