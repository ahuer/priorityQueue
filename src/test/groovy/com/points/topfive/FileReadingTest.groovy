package com.points.topfive

import java.util.PriorityQueue
import static org.junit.Assert.*
import org.junit.Test
import org.junit.Before

import com.points.topfive.FileReading
import com.points.topfive.TopEntries

class FileReadingTest {
	private FileReading reader;
	private String inputFile = "src/test/groovy/com/points/topfive/file1.txt";
	private String outputFilePath = "src/test/groovy/com/points/topfive/testOutput/"
	
	@Before
	public void setup() {
		reader = new FileReading()
	}
	
	@Test
	public void testReadLargeFileWriteSmallFiles() {
		assertEquals(1, reader.readLargeFileWriteSmallFiles(inputFile, outputFilePath))
	}
	
	@Test 
	public void testReadSortSmallFilesWithZero() {
		assertEquals(false, reader.readSortWriteSmallFiles(0, outputFilePath))
	}
	
	@Test
	public void testReadSortSmallFilesWithNegative() {
		assertEquals(false, reader.readSortWriteSmallFiles(-1, outputFilePath))
	}
	
	@Test
	public void testReadSortSmallFiles() {
		assertEquals(true, reader.readSortWriteSmallFiles(1, outputFilePath))
	}
	
	@Test
	public void testSumWordTotalsFromFilesWithZero() {
		def topEntryQueue = new TopEntries(new PriorityQueue(), 3)
		assertEquals(false, reader.sumWordTotalsFromFiles(0, outputFilePath, topEntryQueue))
	}
	
	@Test
	public void testSumWordTotalsFromFilesWithNegative() {
		def topEntryQueue = new TopEntries(new PriorityQueue(), 3)
		assertEquals(false, reader.sumWordTotalsFromFiles(-1, outputFilePath, topEntryQueue))
	}
	
	@Test
	public void testSumWordTotalsFromFiles() {
		def topEntryQueue = new TopEntries(new PriorityQueue(), 3)
		assertEquals(false, reader.sumWordTotalsFromFiles(1, outputFilePath, topEntryQueue))
	}
}
