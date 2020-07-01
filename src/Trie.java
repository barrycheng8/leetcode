//import java.util.HashMap;
//
//public class Trie {
//
//    Node root;
//
//    /** Initialize your data structure here. */
//    public Trie() {
//        root = new Node();
//    }
//
//    /** Inserts a word into the trie. */
//    public void insert(String word) {
//
//        if (word.length() == 1)
//            root.children.putIfAbsent(word.charAt(0), new Node(true));
//
//        Node curr = root;
//        for (int i = 0; i < word.length(); i++) {
//            char c = word.charAt(i);
//            if (i + 1 == word.length())
//                curr.children.put(c, )
//            curr.children.putIfAbsent(c, new Node(false));
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
//    class Node {
//        HashMap<Character, Node> children;
//        boolean isCompleteWord;
//
//        public Node(boolean isEndOfWord) {
//            children = new HashMap<>();
//            isCompleteWord = isEndOfWord;
//        }
//
//        public Node(char c, boolean isEndOfWord) {
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