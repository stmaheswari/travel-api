package com.afkl.cases.df.domain;

import java.io.Serializable;
import java.util.List;

public class AirportList implements Serializable {
	private Page page;
	private List<Location> locations = null;

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	
}
