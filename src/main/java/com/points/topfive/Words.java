package com.points.topfive;

public class Words implements Comparable<Words> {
 	
	private String text;
	private int frequency;
	
	public Words (String text, int frequency) {
		if (frequency < 0 ) {
			frequency = 0;
		}
		
		this.text = text;
		this.frequency = frequency;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public String getText() {
		return text;
	}
	
	@Override
	public int compareTo(Words otherWord) {
		if (frequency < otherWord.getFrequency()) {
			return -1;
		}
		if (frequency > otherWord.getFrequency()) {
			return 1;
		}
		return 0;
	}
	
}
