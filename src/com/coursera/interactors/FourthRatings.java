package com.coursera.interactors;

import java.util.ArrayList;

import com.coursera.models.MovieDatabase;
import com.coursera.models.Rater;
import com.coursera.models.RaterDatabase;
import com.coursera.models.Rating;

public class FourthRatings {
    
    private double getAverageByID(
        String id, int minimalRaters
    )
    {
    	double ans = 0;
    	double sum = 0;
    	int raters = 0;
    	ArrayList<Rater> myRaters = RaterDatabase.getRaters();
    	for (Rater r : myRaters)
    	{
    		if(r.hasRating(id))
    		{
    			sum += r.getRating(id);
    			raters++;
    		}
    	}
    	if(raters >= minimalRaters)
    	{
    		ans = sum / raters;
    	}
    	return ans;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
    	ArrayList<Rating> ratings = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	double average;
    	for(String movieId : movies)
    	{
    		average = getAverageByID(movieId, minimalRaters);
    		if (0 != average)
    		{
    			ratings.add(new Rating(movieId, average));
    		}
    	}
    	return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(
	    int minimalRaters,
	    Filter filterCriteria
	)
	{
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		double average;
		
    	for(String movieId : movies)
    	{
    		average = getAverageByID(movieId, minimalRaters);
    		if (0 != average)
    		{
    			ratings.add(new Rating(movieId, average));
    		}
    	}
    	return ratings;
	}
}
