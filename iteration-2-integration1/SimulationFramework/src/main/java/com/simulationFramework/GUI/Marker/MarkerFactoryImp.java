package com.simulationFramework.GUI.Marker;

public class MarkerFactoryImp implements MarkerFactory {

	private long id, groupId;
	private double lat, lng;
	private String icon, description, name;
	private long width, high;

	public MarkerFactoryImp(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
		this.id = -1;
		this.groupId = -1;
		this.icon = "";
		this.description = "";
		this.name = "";
	}

	public MarkerFactoryImp(long id, double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
		this.id = id;
		this.groupId = -1;
		this.icon = "";
		this.description = "";
		this.name = "";
	}

	public MarkerFactoryImp(long id, String icon, double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
		this.id = id;
		this.groupId = -1;
		this.icon = icon;
		this.description = "";
		this.name = "";
	}

	public long getId() {
		return id;
	}

	public long getGroupId() {
		return groupId;
	}

	public String getIcon() {
		return icon;
	}

	public String getDescription() {
		return description;
	}

	public double getLatitude() {
		return lat;
	}

	public double getLongitude() {
		return lng;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWidth() {
		return width;
	}

	public long getHigh() {
		return high;
	}

	public void setSize(long width, long high) {
		this.width = width;
		this.high = high;
	}

}
