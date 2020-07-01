package ArrayProblems;

import java.util.HashMap;
import java.util.Map;


public class PalindromePermutation {

    // O(n) time, need to touch each character of string and iterate through each entry in the hash table
    // O(1) space, under the assumption that only letters a-z and spaces are used
    public static boolean palPerm(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if (c != ' ') {
                if (!map.containsKey(c))
                    map.put(c, 1);
                else
                    map.put(c, map.get(c) + 1);
            }
        }

        int odd_found = 0;

        for (Map.Entry entry: map.entrySet()) {
            int count = (int)entry.getValue();
            if (count % 2 != 0)
                odd_found++;
        }

        return odd_found <= 1;
    }

    public static void main(String[] args) {
        System.out.println(palPerm("Tact Coa"));
        System.out.println(palPerm("race car"));
        System.out.println(palPerm("google facebook amazon"));
    }
}
