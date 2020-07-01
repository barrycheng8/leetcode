//import java.util.HashMap;
//
//public class Trie {
//
//    LinkedListProblems.Node root;
//
//    /** Initialize your data structure here. */
//    public Trie() {
//        root = new LinkedListProblems.Node();
//    }
//
//    /** Inserts a word into the trie. */
//    public void insert(String word) {
//
//        if (word.length() == 1)
//            root.children.putIfAbsent(word.charAt(0), new LinkedListProblems.Node(true));
//
//        LinkedListProblems.Node curr = root;
//        for (int i = 0; i < word.length(); i++) {
//            char c = word.charAt(i);
//            if (i + 1 == word.length())
//                curr.children.put(c, )
//            curr.children.putIfAbsent(c, new LinkedListProblems.Node(false));
//            curr = curr.children.get(c);
//        }
//        curr.children.get(word.charAt(word.length() - 1)).isCompleteWord = true;
//    }
//
//    /** Returns if the word is in the trie. */
//    public boolean search(String word) {
//
//    }
//
//    /** Returns if there is any word in the trie that starts with the given prefix. */
//    public boolean startsWith(String prefix) {
//
//    }
//
//    class LinkedListProblems.Node {
//        HashMap<Character, LinkedListProblems.Node> children;
//        boolean isCompleteWord;
//
//        public LinkedListProblems.Node(boolean isEndOfWord) {
//            children = new HashMap<>();
//            isCompleteWord = isEndOfWord;
//        }
//
//        public LinkedListProblems.Node(char c, boolean isEndOfWord) {
//            children = new HashMap<>();
//            children.put(c, null);
//            isCompleteWord = isEndOfWord;
//        }
//
//        public String toString() {
//            return children.toString() + " " + isCompleteWord;
//        }
//    }
//}
//
///**
// * Your Trie object will be instantiated and called as such:
// * Trie obj = new Trie();
// * obj.insert(word);
// * boolean param_2 = obj.search(word);
// * boolean param_3 = obj.startsWith(prefix);
// */