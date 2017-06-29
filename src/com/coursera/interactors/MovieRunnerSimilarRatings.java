package com.coursera.interactors;

import java.util.ArrayList;
import java.util.Collections;

import com.coursera.models.MovieDatabase;
import com.coursera.models.RaterDatabase;
import com.coursera.models.Rating;

public class MovieRunnerSimilarRatings {

	public static void printAverageRatings()
	{
		FourthRatings fr = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		//MovieDatabase.initialize("ratedmovies_short.csv");
		System.out.println("Number ratings " + RaterDatabase.size());
		System.out.println("Number of movies in the database " + MovieDatabase.size());
		int minimalRaters = 35;
		ArrayList<Rating> ratings = fr.getAverageRatings(minimalRaters);
		Collections.sort(ratings);
		System.out.println("Rated movies returned " + ratings.size());
		for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
	    }
	}
	
	public static void printAverageRatingsByYearAfterAndGenre() {
		FourthRatings fr = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    int minimalRaters = 8;
	    int year = 1990;
	    String genre = "Drama";
	    AllFilters filterCriteria = new AllFilters();
	    filterCriteria.addFilter(new YearAfterFilter(year));
	    filterCriteria.addFilter(new GenreFilter(genre));
	    ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	//System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getGenres(r.getItem()));
	    }			
	}
}
