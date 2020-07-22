// LeetCode 79. Word Search
// Given a 2D board and word, find if it exists in the grid
// The word can be constructed from sequential adjacent (vertically or horizontally neighboring) cells.
// Letters cannot be used more than once
//
// Intuition: Whenever we are searching for a path that satisfies a condition, we should think DFS and backtracking
// Start at a location in the array, and check all positions vertically/horizontally adjacent to the character.
// If we find a valid character, we recurse and travel to the new location. As soon as that path reaches a dead end,
// backtrack and check if we could have chosen a different path along the way that leads to a valid solution.
// (Draw it out on paper to convince yourself)
// Because we only care about determining if the char[][] actually has the word, we return as soon as we find a valid starting point.
//
// O(M * N * 3^L) runtime: where M is the outer array size, N is the inner array size, and L is the length of the word to search
// This is the worst case scenario, where we traverse through all the characters (M * N) and at each character, we have 3 directions
// to choose from (up, down, left, right, but you cannot go back to your original position so there are only 3, NOT 4 options) for each
// character in the word.
// O(L) space: we add a new function call to the call stack upon successfully find a letter that matches the word.
// The worst case is during the final recursive calls to search for the last letter.

package Backtracking;

public class WordSearch {

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(dfs(board, i, j, word, 0))
                    return true;
            }
        }
        return false;
    }

    static boolean dfs(char[][] board, int i, int j, String word, int idx) {
        if (idx == word.length()) return true;
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0) return false;
        if (board[i][j] != word.charAt(idx)) return false;

        board[i][j] = '*'; // replace the character to prevent reuse in the recursive call
        boolean result = dfs(board, i + 1, j, word, idx + 1) ||
                        dfs(board, i - 1, j, word, idx + 1) ||
                        dfs(board, i, j + 1, word, idx + 1) ||
                        dfs(board, i, j - 1, word, idx + 1);

        board[i][j] = word.charAt(idx); // replace the character after using it
        return result;
    }

    public static void main(String[] args) {
        char[][] words = {{'F','Y','C','E','N','R','D'},
                        {'K','L','N','F','I','N','U'},
                        {'A','A','A','R','A','H','R'},
                        {'N','D','K','L','P','N','E'},
                        {'A','L','A','N','S','A','P'},
                        {'O','O','G','O','T','P','N'},
                        {'H','P','O','L','A','N','O'}};
        String word = "POLAND";

        // We cannot use binary search because the char[] is not sorted!
        // System.out.println(Arrays.binarySearch(words[6], 'P'));

        System.out.println(exist(words, word));
    }
}
