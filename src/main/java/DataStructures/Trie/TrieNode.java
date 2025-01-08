package DataStructures.Trie;

public class TrieNode {
    TrieNode[] links = new TrieNode[26];
    boolean flag = false;

    TrieNode(){
    }

    protected boolean containsKey(char c){
        return links[c-'a'] != null;
    }

    protected TrieNode get(char c){
        return links[c-'a'];
    }

    protected void put(char c, TrieNode trieNode){
        links[c-'a']=trieNode;
    }

    protected void setEnd(){
        flag = true;
    }

    protected boolean isEnd(){
        return flag;
    }
}
