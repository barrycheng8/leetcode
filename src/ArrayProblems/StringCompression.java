// CTCI 1.6
// O(n) time: must go through each character in string
// O(n) space: Worst case is that each letter appears once and it would be useless to compress, therefore returning
//             the original string.

package strings;

public class StringCompression {

    public static String compressString(String s) {
        if (s.length() <= 2) return s;

        char prev = s.charAt(0);
        StringBuilder ans = new StringBuilder();
        ans.append(prev);
        int count = 1;
        char curr;

        for (int i = 1; i < s.length(); i++) {
            curr = s.charAt(i);
            if (prev == curr) count++;
            else {
                ans.append(count).append(curr);
                prev = curr;
                count = 1;
            }
        }
        ans.append(count);
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(compressString("aaa"));
        System.out.println(compressString("a"));
        System.out.println(compressString("aabccccccccccccccccccdaa"));
    }

}
