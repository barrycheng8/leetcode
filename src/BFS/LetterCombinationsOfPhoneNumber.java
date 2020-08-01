package BFS;// LeetCode 17. Letter Combinations of a Phone Number
// Given a string containing digits from 2-9 inclusive, return all possible letter cominations that the number could represent.
// The mapping of digits to letters uses the phone button numbers.
// Example:
// Input: "23"
// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
//
// Intuition:
// We start out by creating a mapping between numbers and the letters that they can represent. We can then represent the growth
// of all combinations with a tree, and use BFS to build up to the final level, which will contain all possible combos.
//              a           b           c
//            / | \       / | \       / | \
//           d  e  f     d  e  f     d  e  f
//
// Growth functions:
// O(3^n * 4^m) runtime:
// n is the number of digits that map to 3 letters. m is the number of digits that map to 4 letters. We must iterate through
// all such letters to build all combinations
// O(3^n * 4^m) space: Because we need to build 3^n * 4^m solutions, we need to use the same amount of space to store and return it.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LetterCombinationsOfPhoneNumber {

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();

        String[] map = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Queue<String> q = new LinkedList<>();

        // Translate the first digit into its possible letters
        String first = map[digits.charAt(0) - '0'];
        for (int i = 0 ; i < first.length(); i++)
            q.offer(String.valueOf(first.charAt(i)));

        int len = digits.length();

        for (int i = 1; i < len; i++) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                // The first letter we will add to each new string we create
                String start = q.peek();
                // The new letters to add to the first letter
                String letters = map[digits.charAt(i) - '0'];
                // Add all combinations of the letters
                for (int j = 0; j < letters.length(); j++)
                    q.offer(start + String.valueOf(letters.charAt(j)));

                q.poll(); // finished processing string combinations using this starting combo
            }
        }
        return new ArrayList<>(q); // Queue will contain all combinations after iterating through loop
    }
}
