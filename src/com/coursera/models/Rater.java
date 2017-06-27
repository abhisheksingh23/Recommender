package com.coursera.models;

import java.util.ArrayList;

public interface Rater {
	public void addRating(String item, double rating);
	public boolean hasRating(String item);
	public double getRating(String item);
	public ArrayList<String> getItemsRated();
	public String getID();
	public int numRatings();	
}
