package com.simulationFramework.GUI.Marker;

public interface MarkerFactory {
	public long getId();
	public long getGroupId();
	public String getName();
	public String getIcon();
	public long getWidth();
	public long getHigh();
	public void setSize(long width, long high);
	public String getDescription();
	public double getLatitude();
	public double getLongitude();
	public void setIcon(String icon);
	public void setDescription(String description);
	public void setName(String name);
}
