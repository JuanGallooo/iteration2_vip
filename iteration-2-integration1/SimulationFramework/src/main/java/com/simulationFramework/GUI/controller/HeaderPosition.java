package com.simulationFramework.GUI.controller;

import lombok.Data;

public class HeaderPosition {

	
	private String nameHeader;
	
	private int positionHeader;

	public HeaderPosition(String nameHeader, int positionHeader) {
		super();
		this.nameHeader = nameHeader;
		this.positionHeader = positionHeader;
	}

	public String getNameHeader() {
		return nameHeader;
	}

	public void setNameHeader(String nameHeader) {
		this.nameHeader = nameHeader;
	}

	public int getPositionHeader() {
		return positionHeader;
	}

	public void setPositionHeader(int positionHeader) {
		this.positionHeader = positionHeader;
	}
	
	@Override
	public String toString() {  
		return nameHeader;
	}
	
}
