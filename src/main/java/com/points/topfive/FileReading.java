package com.points.topfive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.points.topfive.MergeSort;
import com.points.topfive.Words;

public class FileReading {
	private int maxBytesPerFile = 2048;
	private String encoding = "UTF-8";
	
	public int readLargeFileWriteSmallFiles(String inputFileName, String outputFilePath) throws IOException {
		int byteCount = 0;
		int fileCount = 1;
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outputFilePath + "file" + fileCount + ".txt"), encoding));
				
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inputFileName), encoding)) ) {
			for (String line; (line = bufferedReader.readLine()) != null; ) {
				if (line.getBytes(encoding).length + byteCount >= maxBytesPerFile) {
					bufferedWriter.close();
					byteCount = 0;
					fileCount++;
					bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
							outputFilePath + "file" + fileCount + ".txt"), encoding));
				}
				byteCount += line.getBytes(encoding).length;
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
		} catch (IOException ex) {
			bufferedWriter.close();
			return 0;
		}
		
		bufferedWriter.close();
		return fileCount;
	}
	
	public boolean readSortWriteSmallFiles(int numFiles, String filePath) {
		if (numFiles <= 0 ) {
			return false;
		}
		
		for (int fileCount = 1; fileCount <= numFiles; fileCount++ ) {
			ArrayList<String> fileWords = new ArrayList<>();
			
			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath + "/file" + fileCount + ".txt"), encoding)) ) {
				for (String line; (line = bufferedReader.readLine()) != null; ) {
					fileWords.add(line);
				}
			} catch (IOException ex) {
				return false;
			}
			
			MergeSort mergeSort = new MergeSort();
			mergeSort.sort(fileWords);
			
			try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath + "/file" + fileCount + "-sorted.txt"), encoding)) ) {
				for (String word : fileWords ) {
					bufferedWriter.write(word);
					bufferedWriter.newLine();
				}
			} catch (IOException ex) {
				return false;
			}
		}		
		return true;
	}
	
	public boolean sumWordTotalsFromFiles(int numFiles, String filePath, TopEntries topEntries) throws IOException {
		if (numFiles <= 0 ) {
			return false;
		}
		
		ArrayList<BufferedReader> fileReaders = new ArrayList<>();
		
		try {
			for (int fileCount = 1; fileCount <= numFiles; fileCount++ ) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath + "/file" + fileCount + "-sorted.txt"), encoding));
				fileReaders.add(bufferedReader);
			}
			
		} catch (IOException ex) {
			for (BufferedReader br : fileReaders ) {
				br.close();
			}
			return false;
		}
		
		String currentWord = "";
		String nextWord = "";
		int currentFreq = 0;
		ArrayList<String> currentFilePointers = new ArrayList<>();
		
		try {
			while (fileReaders.size() > 0 ) {
				for (int fileNum = 0; fileNum < fileReaders.size(); fileNum++ ) {
					String nextLine = "";
					
					if (currentFilePointers.size() < fileReaders.size() ) {
						nextLine = fileReaders.get(fileNum).readLine();
					}
					
					if (currentFilePointers.size() == fileReaders.size() ) {
						nextLine = currentFilePointers.get(fileNum);
					}
					
					while (nextLine == currentWord ) {
						currentFreq++;
						nextLine = fileReaders.get(fileNum).readLine();
					}
					
					if (nextLine == null ) {
						fileReaders.get(fileNum).close();
						fileReaders.remove(fileNum);
					} 
					
					if (currentFilePointers.size() < fileNum + 1 ) {
						currentFilePointers.add(nextLine);
					}
					else {currentFilePointers.set(fileNum, nextLine); }
				}
				
				nextWord = findSmallestWord(currentFilePointers);
				
				if (!currentWord.equals("") || !currentWord.equals(nextWord) ) {
					topEntries.addToTopEntries(new Words(currentWord, currentFreq));
					currentFreq = 0;
				}
				
			}
		} catch (IOException ex) {
			for (BufferedReader br : fileReaders ) {
				br.close();
			}
			return false;
		}
		return true;		
	}
	
	private static String findSmallestWord(ArrayList<String> wordList) {
		String smallestWord = wordList.get(0);
		
		for (int i = 1; i < wordList.size(); i++ ) {
			if (wordList.get(i).compareTo(smallestWord) < 0 ) {
				smallestWord = wordList.get(i);
			}
		}		
		return smallestWord;
	}
	
	public void setMaxBytes(int max) {
		maxBytesPerFile = max;
	}
}
