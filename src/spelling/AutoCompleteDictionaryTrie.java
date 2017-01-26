package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size=0;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method
		if(isWord(word))
			return true;
		TrieNode loop = new TrieNode();
//		System.out.println("adding word " + word + " with root " + root);
		loop = root;
		for(char c : word.toLowerCase().toCharArray()) {
			if(loop.getChild(c) == null) {
//				System.out.println("inserting char " + c + " for " + loop + " with " + loop.getValidNextCharacters());
			    loop = loop.insert(c);
			} else {
				loop = loop.getChild(c);
			}
		}
//		System.out.println("setting end word for " + loop.getText());
//		System.out.println(root.getValidNextCharacters());
		loop.setEndsWord(true);
		size++;
//		System.out.println("incrementing size for " + word + " to "+ size);
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
//		System.out.println("size is " + size);
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode loop = new TrieNode();
		int i = 0;
		loop = root;
//		System.out.println(loop.toString() + " " + root.toString());
		if (s == "")
			return false;
		
		for (char c: s.toLowerCase().toCharArray()) {
			i++;
			loop = loop.getChild(c);
//			System.out.println("get child for " + c + " i " + i + " " + s.length() + " " + loop);
			if (loop == null) {
//				System.out.println("returning null for " + s + " on " + c);
				return false;
			}
//		    System.out.println(loop.toString() + " " + root.toString() + " for " + c);
		}
		return loop.endsWord();
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
//    	 System.out.println("in with " + prefix + " for "+ numCompletions);
    	 TrieNode loop = new TrieNode();
    	 List<String> comp = new ArrayList<String>();
    	 loop = root;
    	 for(char c: prefix.toLowerCase().toCharArray()) {
    		 loop = loop.getChild(c);
    		 if (loop == null) {
    			 return comp;
    		 }
    	 }
    	 List<TrieNode> nodeQ = new LinkedList<TrieNode>();
//    	 nodeQ.add(loop);
    	 if(loop.endsWord()) {
    		 comp.add(loop.getText());
    		 numCompletions--;
    	 }
    	 for (char c : loop.getValidNextCharacters()) {
    		 nodeQ.add(loop.getChild(c));
    	 }
//    	 System.out.println(nodeQ);
    	 while (!nodeQ.isEmpty() && numCompletions > 0) {
    		 if(nodeQ.get(0).endsWord()) {
    			 comp.add(nodeQ.get(0).getText());
    			 numCompletions--;
    		 }
    		 TrieNode loc = nodeQ.remove(0);
    		 for (char c : loc.getValidNextCharacters()) {
    			 nodeQ.add(loc.getChild(c));
    		 }
    	 }
//    	 System.out.println(comp);
         return comp;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}