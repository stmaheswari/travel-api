package com.afkl.cases.df.domain;

import java.io.Serializable;

public class FareDetails implements Serializable {
private Fare fare;
private Location origin;
private Location destination;
public Fare getFare() {
	return fare;
}
public void setFare(Fare fare) {
	this.fare = fare;
}
public Location getOrigin() {
	return origin;
}
public void setOrigin(Location origin) {
	this.origin = origin;
}
public Location getDestination() {
	return destination;
}
public void setDestination(Location destination) {
	this.destination = destination;
}

}
