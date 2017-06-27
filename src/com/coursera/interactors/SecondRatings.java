package com.coursera.interactors;


import java.util.*;
import com.coursera.models.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings(String moviefile, String ratingsfile)
    {
    	FirstRatings fr = new FirstRatings();
    	this.myMovies = fr.loadMovies(moviefile);
    	this.myRaters = fr.loadRaters(ratingsfile);
    }
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public int getMovieSize()
    {
    	return myMovies.size();
    }
    
    public int getRaterSize()
    {
    	return myRaters.size();
    }
    
    private double getAverageByID(
        String id, int minimalRaters
    )
    {
    	double ans = 0;
    	double sum = 0;
    	int raters = 0;
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
    	double average;
    	for(Movie movie : myMovies)
    	{
    		average = getAverageByID(movie.getID(), minimalRaters);
    		if(0 != average)
    		{
    			Rating rating = new Rating(movie.getID(), average);
    			ratings.add(rating);
    		}
    	}
    	return ratings;
    }
    
    public String getTitle(String id)
    {
    	String title = "NO SUCH MOVIE WITH ID " + id;
    	for(Movie m : myMovies)
    	{
    		if(m.getID().equals(id))
    		{
    			title = m.getTitle();
    		}
    	}
    	return title;
    }
    
    public String getID(String title)
    {
    	String id = "NO SUCH TITLE.";
    	for(Movie m : myMovies)
    	{
    		if(m.getTitle().equals(title))
    		{
    			id = m.getID();
    		}
    	}
    	return id;
    }
}