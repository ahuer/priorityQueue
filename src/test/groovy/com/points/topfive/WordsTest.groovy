package com.points.topfive

import static org.junit.Assert.*
import org.junit.Test

import com.points.topfive.Words 

class WordsTest {
	
	@Test
	public void testCompareToGreaterThan() {
		def pizza = new Words("pizza", 7)
		def apple = new Words("apple", 14)
		
		assertEquals(1, apple.compareTo(pizza))
	}
	
	@Test
	public void testCompareToLessThan() {
		def pizza = new Words("pizza", 7)
		def apple = new Words("apple", 4)
		
		assertEquals(-1, apple.compareTo(pizza))
	}
	
	@Test
	public void testCompareToEqual() {
		def pizza = new Words("pizza", 7)
		def apple = new Words("apple", 7)
		
		assertEquals(0, apple.compareTo(pizza))
	}
	
	@Test
	public void testToStringOnArrayOfWords() {
		def wordList = [new Words("aaa", 11), new Words("bbb", 22)]
		
		assertEquals("[Text: aaa - Freq: 11, Text: bbb - Freq: 22]", wordList.toString())
	}
}
