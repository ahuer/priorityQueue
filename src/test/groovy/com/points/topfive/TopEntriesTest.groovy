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
}
