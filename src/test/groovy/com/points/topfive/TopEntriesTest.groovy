package com.points.topfive

import java.util.PriorityQueue
import static org.junit.Assert.*
import org.junit.Test

import com.points.topfive.TopEntries
import com.points.topfive.Words

class TopEntriesTest {

	@Test 
	public void testTopEntriesNullQueueBecomesInitializedQueue() {
		def expectedResult = new PriorityQueue()
		
		def topEntry = new TopEntries(null, 7)
		def actualResult = topEntry.getTopEntries() 
		
		assertNotEquals(null, actualResult)
		assertEquals(expectedResult.size(), actualResult.size())
	}
	
	@Test 
	public void testMaxEntriesSmallerThanQueueSize() {
		def expectedResult = new PriorityQueue()
		
		def queue = new PriorityQueue()
		queue.add(new Words("apple", 4))
		queue.add(new Words("cat", 2))
		
		def topEntry = new TopEntries(queue, 1)
		def actualResult = topEntry.getTopEntries()
		
		assertEquals(expectedResult.size(), actualResult.size())
	}
	
	@Test
	public void testMaxEntriesLessThanZero() {
		def topEntry = new TopEntries(new PriorityQueue(), -1)
		
		assertEquals(1, topEntry.getMaxTopEntries())
	}
	
	@Test
	public void testAddNullToTopEntries() {
		def topEntry = new TopEntries(null, 3)
		
		assertEquals(false, topEntry.addToTopEntries(null))
	}
	
	@Test
	public void testAddWordToTopEntries() {
		def topEntry = new TopEntries(new PriorityQueue(), 4)
		
		assertEquals(true, topEntry.addToTopEntries(new Words("banana", 4)))
	}
	
	@Test
	public void testAddWordToTopEntriesWithQueuePop() {
		Words apple = new Words("apple", 6)
		Words banana = new Words("banana", 1)
		Words cat = new Words("cat", 7)
		
		def queue = new PriorityQueue()
		queue.add(apple)
		queue.add(banana)
		
		def topEntry = new TopEntries(queue, 2)
		def topTwo = topEntry.getTopEntries()
		assertEquals(true, topEntry.addToTopEntries(cat))
		assertEquals(true, topTwo.contains(apple))
		assertEquals(true, topTwo.contains(cat))
		assertEquals(false, topTwo.contains(banana))
	}
	
	@Test
	public void testDoesNotAddWordToTopEntriesWhenFreqTooLow() {
		Words apple = new Words("apple", 6)
		Words banana = new Words("banana", 5)
		Words cat = new Words("cat", 1)
		
		def queue = new PriorityQueue()
		queue.add(apple)
		queue.add(banana)
		
		def topEntry = new TopEntries(queue, 2)
		def topTwo = topEntry.getTopEntries()
		assertEquals(false, topEntry.addToTopEntries(cat))
		assertEquals(true, topTwo.contains(apple))
		assertEquals(true, topTwo.contains(banana))
		assertEquals(false, topTwo.contains(cat))		
	}
}
