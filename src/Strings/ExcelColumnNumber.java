// LeetCode 171. Excel Sheet Column Number
// Given a column title as appears in an Excel sheet, return the corresponding column number.
// A = 1
// B = 2
// Z = 26
// AA = 27
// AB = 28
//
// Intuition:
// This is essentially a numbering system with a different base. All 26 letters must be reached before going
// to the next digit place.
//
// Growth Functions:
// O(n) runtime: runtime scales with the length of the string
// O(1) space

package Strings;

public class ExcelColumnNumber {

    public static int titleToNumber(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans = (ans * 26) + 1 + s.charAt(i) - 'A';
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "AB";
        System.out.println(titleToNumber(s));
    }
}
