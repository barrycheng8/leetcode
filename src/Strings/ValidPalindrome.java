/*
 LeetCode 125. Valid Palindrome
 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 Empty strings are defined as valid palindromes.
 Ex. Input: "A man, a plan, a canal: Panama"        Output: true

 Intuition:
 Use two pointers and iterate towards the center of the string. Since we only care about alphanumeric characters,
 we continue to increment the counter if we run into anything otherwise. We also check to make sure the point remains within
 bounds.

 Notes:
 The key part is accounting for edge cases. See examples below
 Ex. Input: ",."    Output: false. Because we ignore non-alphanumeric characters, this is the same as an empty string.
 Ex. Input: "0P"    Output: false. We compare 0 == P, and we get false.

 Growth Functions:
 O(n) runtime: The runtime grows with the length of the string. The estimated runtime is 0.5n. At each iteration of the while
 loop, we at least move a distance of two by incrementing lo and decrementing hi.
 O(1) space: Space used is independent of input. We use two pointers and a few variables to hold the lowercase character.

*/

package Strings;

public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        if (s.length() < 2) return true;

        int lo = 0;
        int hi = s.length() - 1;

        while (lo < hi) {
            while (lo + 1 < s.length() && !isAlphanumeric(s.charAt(lo)))
                lo++;
            while (hi - 1 >= 0 && !isAlphanumeric(s.charAt(hi)))
                hi--;

            char clo = Character.toLowerCase(s.charAt(lo));
            char chi = Character.toLowerCase(s.charAt(hi));

            if (isAlphanumeric(clo) && isAlphanumeric(chi)) {
                if (clo != chi)
                    return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    public static boolean isAlphanumeric(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }

    public static void main(String[] args) {
        String s = "0P";
        System.out.println(isPalindrome(s));
    }
}
