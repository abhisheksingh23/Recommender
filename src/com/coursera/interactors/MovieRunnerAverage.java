package com.coursera.interactors;

import java.util.ArrayList;
import java.util.Collections;

import com.coursera.models.Rating;

public class MovieRunnerAverage {
	
	public void printAverageRatings()
	{
		SecondRatings sr = new SecondRatings(
							   "data/ratedmoviesfull.csv",
						       "data/ratings.csv"
						   );
		System.out.println(sr.getMovieSize());
		System.out.println(sr.getRaterSize());
		int minimalRaters = 12;
		ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
		for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
	    }
	}
	
	public void getAverageRatingOneMovie() {
		SecondRatings sr = new SecondRatings(
							   "data/ratedmoviesfull.csv",
						       "data/ratings.csv"
						   );
		String movieTitle = "Vacation";
		String movieID = sr.getID(movieTitle);
		int minimalRaters = 0;
		ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
		for(Rating r: ratings) {
			if(r.getItem().equals(movieID))
				System.out.println("The average rating for the movie \"" + movieTitle + "\" is " + r.getValue());
		}
	}
}
