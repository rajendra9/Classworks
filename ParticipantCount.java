package com.htc.events.model;

public class ParticipantCount {

	private int totalCount;
	private int maleCount;
	private int femaleCount;
	
	public ParticipantCount(){}
	public ParticipantCount(int totalCount, int maleCount, int femaleCount) {
		super();
		this.totalCount = totalCount;
		this.maleCount = maleCount;
		this.femaleCount = femaleCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(int maleCount) {
		this.maleCount = maleCount;
	}
	public int getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(int femaleCount) {
		this.femaleCount = femaleCount;
	}
	
	
}
