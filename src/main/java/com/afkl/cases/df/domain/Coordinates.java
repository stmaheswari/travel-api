package com.afkl.cases.df.domain;

import java.io.Serializable;


public class Coordinates implements Serializable{

    private double latitude, longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
    
    

}