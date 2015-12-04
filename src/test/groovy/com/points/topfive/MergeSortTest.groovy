package com.points.topfive

import static org.junit.Assert.*
import org.junit.Before
import org.junit.Test

import com.points.topfive.MergeSort

class MergeSortTest {
	MergeSort mergeSort;
	
	@Before
	public void setup() {
		mergeSort = new MergeSort();
	}
	
	@Test
	public void testEmptyList() {
		def expectedResult = new ArrayList<String>()
		def actualResult = new ArrayList<String>()
		mergeSort.sort(actualResult)
		assertEquals(expectedResult, actualResult)
	}
	
	@Test
	public void testNullList() {
		def expectedResult = null
		def actualResult = null
		mergeSort.sort(actualResult)
		assertEquals(expectedResult, actualResult)
	}
	
	@Test
	public void testOneElementList() {
		def expectedResult = ["Apple"]
		def actualResult = ["Apple"]
		mergeSort.sort(actualResult)
		assertEquals(expectedResult, actualResult)
	}
	
	@Test
	public void testTwoElementList() {
		def expectedResult = ["apple", "Banana"]
		def actualResult = ["Banana", "apple"]
		mergeSort.sort(actualResult)
		assertEquals(expectedResult, actualResult)
	}
	
	@Test
	public void testListWithDuplicates() {
		def expectedResult = ["apple", "apple", "apple", "banana", "banana"]
		def actualResult = ["banana", "apple", "banana", "apple", "apple"]
		mergeSort.sort(actualResult)
		assertEquals(expectedResult, actualResult)
	}
}
