package datastructure.trie;

/**
 * 字典树
 * @author sqzhang
 * @year 2020
 */
public class Trie {

    private static final char startCharacter = 'A';
    
    static class Node {
        Node[] next = new Node[256];
        boolean isWord = false;
    }

    private Node root;

    public Trie() {
        root = new Node();
    }

    // 插入单词
    public void insert(String word) {
        Node cur = root;
        for(char c : word.toCharArray()) {
            Node next = cur.next[c - startCharacter];
            if (next == null) {
                cur.next[c - startCharacter] = new Node();
            }
            cur = cur.next[c - startCharacter];
        }
        cur.isWord = true;
    }

    // 搜索单词
    public boolean search(String word) {
        return match(word, root, 0, false);
    }

    // 搜索单词前缀
    public boolean startsWith(String prefix) {
        return match(prefix, root, 0, true);
    }

    private boolean match(String word, Node node, int start, boolean prefix) {
        if (start == word.length()) {
            return prefix || node.isWord;
        }
        char w = word.charAt(start);
        if (node.next[w - startCharacter] == null) {
            return false;
        }
        return match(word, node.next[w - startCharacter], start + 1, prefix);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("this");
        trie.insert("the");
        trie.insert("they");
        trie.insert("abc");

        System.out.println(trie.search("this"));
        System.out.println(trie.search("the"));
        System.out.println(trie.search("they"));
        System.out.println(trie.search("t"));
        System.out.println(trie.startsWith("t"));
    }

}
