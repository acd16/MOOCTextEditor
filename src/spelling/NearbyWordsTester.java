package spelling;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class NearbyWordsTester {
	private String dictFile = "data/words.small.txt";
	
	NearbyWords smallTest;
	AutoCompleteDictionaryTrie largeDict;
	
	@Before
	public void setup() {
		largeDict = new AutoCompleteDictionaryTrie();
		DictionaryLoader.loadDictionary(largeDict, dictFile);
		smallTest = new NearbyWords(largeDict);
	}

//	@Ignore
	@Test
	public void insertionsTest() {
		List<String> retList = new LinkedList<String>();
		smallTest.insertions("thos", retList, true);
		boolean contains = retList.contains("those");// && retList.contains("ethos");
		System.out.println(retList);
		assertTrue(contains);
	}

	@Test
	public void deletionsTest() {
		List<String> retList = new LinkedList<String>();
		smallTest.deletions("thise", retList, true);
//		System.out.println(retList);
		boolean contains = retList.contains("this");// && retList.contains("ethos");
		assertTrue(contains);
	}
}
