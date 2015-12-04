package com.points.topfive;

import java.util.PriorityQueue;

import com.points.topfive.Words;

public class TopEntries {
	private final int MAX_TOP_ENTRIES;
	private PriorityQueue<Words> topEntries;
	
	public TopEntries (PriorityQueue<Words> topEntries, int maxTopEntries) {
		this.topEntries = topEntries;
		MAX_TOP_ENTRIES = maxTopEntries;
		
		if (topEntries == null || topEntries.size() > MAX_TOP_ENTRIES) {
			this.topEntries = new PriorityQueue<>(MAX_TOP_ENTRIES);
		}
	}
	
	public boolean addToTopEntries(Words word) {
		if (word == null) {
			return false;
		}
		
		if (topEntries.size() < MAX_TOP_ENTRIES) {
			topEntries.add(word);
			return true;
		}
		
		if (topEntries.peek().compareTo(word) < 0) {
			topEntries.poll();
			topEntries.add(word);
			return true;
		}
		
		return false;
	}
	
	public PriorityQueue<Words> getTopEntries() {
		return topEntries;				
	}
	
	public String getTopEntriesString() {
		if (topEntries.isEmpty()) {
			return (new Words[0]).toString();
		}
		return ((Words[]) topEntries.toArray()).toString();				
	}
	
	
	
	

}
