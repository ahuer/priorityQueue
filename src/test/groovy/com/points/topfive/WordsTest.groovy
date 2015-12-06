package com.points.topfive

import static org.junit.Assert.*
import org.junit.Test

import com.points.topfive.Words 

class WordsTest {
	
	@Test
	public void testFreqLessThanZero() {
		def pizza = new Words("pizza", -1)
		assertEquals(0, pizza.getFrequency())
	}
	
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

}
