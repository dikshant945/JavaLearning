package DataStructures.Trie;

public class Trie {


    TrieNode root = null;

    public Trie(){
        root = new TrieNode();
    }

    protected void insert(String word){
        TrieNode node = root;

        for(int i =0;i<word.length();i++){
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i), new TrieNode());
                node = node.get(word.charAt(i));
            }
        }

        node.setEnd();
    }

    protected boolean search(String word){
        TrieNode node = root;
        for(int i =0;i<word.length();i++){
            if(!node.containsKey(word.charAt(i))){
                return false;
            }

            node = node.get(word.charAt(i));
        }

        return node.isEnd();
    }

    protected boolean startsWith(String word){
        TrieNode node = root;
        for(int i =0;i<word.length();i++){
            if(!node.containsKey(word.charAt(i))){
                return false;
            }
            node = node.get(word.charAt(i));
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("apps");
        trie.insert("apx");
        trie.insert("bac");

        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("ba"));
        System.out.println(trie.search("dikshant"));
    }

}
