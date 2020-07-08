package com.afkl.cases.df.utils;

/**
 * Creating travel exception to handle the service exception
 * @author Maheswari
 */
public class TravelException extends Exception {
    String error;

    public TravelException(String error) {
        this.error = error;
    }
    public String toString(){
        return ("TravelException Occurred: "+ this.error) ;
    }
}
