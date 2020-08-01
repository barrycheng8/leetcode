/*
 LeetCode 520. Detect Capital
 Given a word, determine if the usage of capitals is correct. Use the following criteria:
 1. All letters in the word are capital: USA
 2. All letters are not capital: leetcode
 3. Only the first letter is capitalized: Google
 All other words would be invalid

 Intuition:
 Best case runtime we need to check all the characters in the word to verify that the usage is correct.
 Intuitively, all words of length 1 or less are guaranteed to be valid uses, because the rules allow for
 the first char to be upper or lowercase. We then traverse through the characters and compare the current to the previous
 to ensure the rules are followed. There are 2 violations that we will check for:
 1. If prev is lower and curr is upper: "fF" would be invalid.
 2. If prev is upper and curr is lower, and we are no longer at the first character: "FFFf" is invalid.
 If we don't catch these cases, return true

 Growth functions:
 O(n) runtime: Must traverse through the entire string
 O(1) space: variables to keep track of previous character

 Improvements:
 This can be turned into a 1-liner using Regex
*/

package ArrayProblems;

public class DetectCapital {

    public boolean detectCapitalUse(String word) {
        // Word of length 1 or less is always valid
        if (word.length() < 2) return true;

        char prev = word.charAt(0);

        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            // Case 1: if "fF", return false
            if (!Character.isUpperCase(prev) && Character.isUpperCase(curr))
                return false;
            // Case 2: if "FFFf", return false. i > 1 only accepts if the first letter is capital
            if (Character.isUpperCase(prev) && !Character.isUpperCase(curr) && i > 1)
                return false;
            prev = curr;
        }
        return true;
    }
}
