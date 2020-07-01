package ArrayProblems;// CTCI 1.2

import java.util.HashMap;
import java.util.Map;

public class CheckPermutation {

    // O(2n) time, must iterate through both strings. O(n) space, must add each character to HashMap
    public static boolean checkPermutation(String str1, String str2) {
        if (str1.length() != str2.length()) return false; // If string lengths are not equal, they are not permutations

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            if (!map.containsKey(c))
                map.put(c, 1);
            else
                map.put(c, map.get(c) + 1);
        }

        Integer a = new Integer(1);
        boolean z = a.intValue() == 1;

        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);
            if (!map.containsKey(c) || (map.get(c) == 0)) return false;
            else map.put(c, map.get(c) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Are the following strings permutations of each other?");
        System.out.println("abba, baab: " + checkPermutation("abba", "baab"));
        System.out.println("joe, schmoe: " + checkPermutation("joe", "schmoe"));
    }
}
