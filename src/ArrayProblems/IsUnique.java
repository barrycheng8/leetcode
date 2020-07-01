package ArrayProblems;// CTCI 1.1

import java.util.Arrays;
import java.util.HashMap;

public class IsUnique {

    // O(nlogn) time due to sorting. O(n) space because we are storing the string of length n in an array structure
    public static boolean isUniqueV3(String s) {
        char[] str = s.toCharArray();
        Arrays.sort(str);
        char prev = str[0];
        for (int i = 1; i < str.length; i++) {
            char curr = str[i];
            if (prev == curr) return false;
            else prev = curr;
        }
        return true;
    }

    // O(n) time, all characters must be touched. O(1) space. The size is capped at 26 as there are only 26 letters
    // No data structures used
    public static boolean isUniqueV2(String s) {
        int check = 0; // initialize bit vector to 0
        for (int i = 0; i < s.length(); i++) {
            int letter = s.charAt(i) - 'a'; // a=0, b=1, etc.
            letter = 1 << letter;
            if ((check & letter) != 0) return false; // check if that letter was previously seen
            else check = check | letter; // update the check to include the new letter
        }
        return true;
    }

    // O(n) time and O(1) space solution (26 letters maximum)
    public static boolean isUnique(String s) {
        HashMap<Character, Boolean> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)))
                return false;
            else
                map.put(s.charAt(i), true);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Are the following strings unique?");
        System.out.println("hello: " + isUniqueV3("hello"));
        System.out.println("friend: " + isUniqueV3("friend"));
        System.out.println("acdefghijklmnop: " + isUniqueV3("abcdefghijklmnop"));
    }
}
