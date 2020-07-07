// CTCI 1.5
// O(n) time: Must go through all elements of at least one string
// O(1) space: no data structures are used

package strings;

public class OneAway {

    public static boolean oneAway(String s, String t) {
        if (s.length() == t.length())
            return checkReplace(s, t);
        else if (s.length() - 1 == t.length())
            return checkInsert(s, t); // large, small
        else if (s.length() + 1 == t.length())
            return checkInsert(t, s);
        return false;
    }

    /* Assumed that strings are equal length. Check if there is 0 or 1 mismatch */
    public static  boolean checkReplace(String s, String t) {
        boolean mismatchFound = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (mismatchFound) return false;
                else mismatchFound = true;
            }
        }
        return true;
    }

    /* Iterate through both strings. Allow one insertion by incrementing i (longer string) and leaving j stagnant */
    public static boolean checkInsert(String s, String t) {
        boolean insertNeeded = false;
        for (int i = 0, j = 0; i < s.length() && j < t.length(); i++) {
            if (s.charAt(i) != t.charAt(j)) {
                if (insertNeeded) return false;
                else {
                    i++;
                    insertNeeded = true;
                }
            }
            else j++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(oneAway("boba", "bob"));
        System.out.println(oneAway("jones", "pavelski"));
        System.out.println(oneAway("jane", "bane"));
        System.out.println(oneAway("pales", "pale"));
    }
}
