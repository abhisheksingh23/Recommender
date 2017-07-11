package com.coursera.interactors;

import java.util.ArrayList;
import java.util.Collections;

import com.coursera.models.MovieDatabase;
import com.coursera.models.Rater;
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
	
	public static void printSimilarRatings() {
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "337"; //"71";
		
		//change being added 
		Rater rater = RaterDatabase.getRater(raterID);
		ArrayList<String> movieRated = rater.getItemsRated();
		// finish 
		
		int numSimilarRaters = 10;
		int minimalRaters = 3;
		ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
	    
		System.out.println(ratings.size() + " movies matched");
	    System.out.printf("%d %.2f %s\n", 0, ratings.get(0).getValue(), MovieDatabase.getTitle(ratings.get(0).getItem()));
	    for(int i=0; i< ratings.size(); i++) {
	    	String movieId = ratings.get(i).getItem();
	    	if (!movieRated.contains(movieId))
	    	{
	    		continue;
	    	}
	    	if (i<15)
	    	{
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
	    	}	
		}
	}
	
	public static void printSimilarRatingsByGenre() {
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "964";
		int numSimilarRaters = 20;
		int minimalRaters = 5;
		String genre = "Mystery";
	    Filter filterCriteria = new GenreFilter(genre);
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    System.out.printf("%d %.2f %s\n", 0, ratings.get(0).getValue(), MovieDatabase.getTitle(ratings.get(0).getItem()));
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15)
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
		}	
	}
	
	public static void printSimilarRatingsByDirector() {
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "120";
		int numSimilarRaters = 10;
		int minimalRaters = 2;
		String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; 
	    Filter filterCriteria = new DirectorsFilter(directors);
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    System.out.printf("%d %.2f %s\n", 0, ratings.get(0).getValue(), MovieDatabase.getTitle(ratings.get(0).getItem()));
	    for(int i=0; i< ratings.size(); i++) {
    	if (i<15)
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
		}		
	}
	
	public static void printSimilarRatingsByGenreAndMinutes() {
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "168";
		int numSimilarRaters = 10;
		int minimalRaters = 3;
		String genre = "Drama";
		int min = 80;
		int max = 160;
	    AllFilters filterCriteria = new AllFilters();
	    filterCriteria.addFilter(new GenreFilter(genre));
	    filterCriteria.addFilter(new MinutesFilter(min, max));
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15)
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
		}			
	}
	
	public static void printSimilarRatingsByYearAfterAndMinutes() {
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "314";
		int numSimilarRaters = 10;
		int minimalRaters = 5;
		int year = 1975;
		int min = 70;
		int max = 200;
	    AllFilters filterCriteria = new AllFilters();
	    filterCriteria.addFilter(new YearAfterFilter(year));
	    filterCriteria.addFilter(new MinutesFilter(min, max));
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15)
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
		}		
	}
	
	public static void main(String[] args) {
		printSimilarRatings();
		//printSimilarRatingsByGenre();
		//printSimilarRatingsByDirector();
		//printSimilarRatingsByGenreAndMinutes();
		//printSimilarRatingsByYearAfterAndMinutes();
	}
}
