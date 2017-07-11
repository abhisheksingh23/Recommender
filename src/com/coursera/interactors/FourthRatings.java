package com.coursera.interactors;

import java.util.ArrayList;
import java.util.Collections;

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
    
    private double dotProduct(Rater me, Rater r)
    {
    	ArrayList<String> myMovies = me.getItemsRated();
    	double dotProduct = 0;
    	for(String movie : myMovies)
    	{
    		if(r.hasRating(movie))
    		{
    			double myRating = me.getRating(movie);
    			double rRating = me.getRating(movie);
    			myRating -= 5;
    			rRating -= 5;
    			dotProduct += myRating * rRating;
    		}
    	}
    	return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id)
    {
    	ArrayList<Rating> ratings = new ArrayList<Rating>();
    	Rater me = RaterDatabase.getRater(id);
    	for(Rater rater : RaterDatabase.getRaters())
    	{
    		if(!rater.getID().equals(id))
    		{
    			double dotProduct = dotProduct(me, rater);
    			if(dotProduct > 0)
    			{
    				ratings.add(new Rating(id, dotProduct));
    			}
    		}
    	}
    	Collections.sort(ratings, Collections.reverseOrder());
    	return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatings(
        String id, int numSimilarRaters, 
        int minimalRaters
    ) 
    {
    	ArrayList<Rating> res = getSimilarRatingsByFilter(
    							    id, numSimilarRaters,
    							    minimalRaters, new TrueFilter()
    							);
		return res;		
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(
        String id, int numSimilarRaters, 
        int minimalRaters, Filter filterCriteria
    ) 
    {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;	
    }
}
