package com.coursera.testing;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;

import com.coursera.models.*;

public class FirstRatings {
	public static ArrayList<Movie> loadMovies(String filename)
	{
		ArrayList<Movie> movies = new ArrayList<Movie>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord rec: parser) {
			Movie m = new Movie(
		                  rec.get("id"), rec.get("title"), 
		                  rec.get("year"), rec.get("genre"), 
		                  rec.get("director"),rec.get("country"), 
		                  rec.get("poster"), 
		                  Integer.parseInt(rec.get("minutes"))
		              );
			movies.add(m);
		}
		return movies;
	}
	
	public static ArrayList<Rater> loadRaters(String filename)
	{
		// Fields required to initialize the raters
		String raterId;
		String movieId;
		Integer rating;
		
		ArrayList<Rater> raters = new ArrayList<Rater>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		int index = 0;
		
		for(CSVRecord rec: parser)
		{
			raterId = rec.get("rater_id");
			movieId = rec.get("movie_id");
			rating = Integer.parseInt(rec.get("rating"));
			Rater rater;
			
			if (0 == index || !raters.get(index - 1).getID().equals(raterId)) 
			{
				rater = new Rater(raterId);
				rater.addRating(
				    movieId, 
				    rating
				);
				raters.add(index, rater); 
				index++;
			}
			else
			{
				rater = raters.get(index - 1);
				rater.addRating(
				    movieId, 
				    rating
				);
			}
		}
		return raters;
	}
	
	
	public static void testLoadMovies()
	{
		String filename = "data/ratedmovies_short.csv";
		String ratedmoviesfull = "data/ratedmoviesfull.csv";
		ArrayList<Movie> list = loadMovies(ratedmoviesfull);
		System.out.println("Total number of movies : " + list.size());
//		for(Movie m : list)
//		{
//			System.out.println(m);
//		}
		
		String filename_short = "data/ratedmovies_short.csv";
		list = loadMovies(ratedmoviesfull);
		String genre = "Comedy";
		int time = 150;
		long cnt = 0;
		long lengthy = 0;
		
		HashMap<String, Integer> directorToMovie = 
		   new HashMap<String, Integer>();
		
		for(Movie m : list)
		{
			if (m.getGenres().contains(genre))
			{
				cnt += 1;
			}
			if (m.getMinutes() > time)
			{
				lengthy += 1;
			}
			
			String director = m.getDirector();
			if (directorToMovie.containsKey(director))
			{
				directorToMovie.put(
				    m.getDirector(), directorToMovie.get(director) + 1
				);
			}
			else
			{
				directorToMovie.put(director, 1);
			}
		}
		int directorWithMaxMovie = Collections.max(directorToMovie.values());
		for(String director : directorToMovie.keySet())
		{
			if (directorToMovie.get(director).equals(directorWithMaxMovie))
			{
				System.out.println("Director : " + director + 
								   " has directed max movies: " + 
								   directorWithMaxMovie);
			}
		}
		System.out.println("Comedy movies are " + cnt);
		System.out.println("Lengthy movies "  + lengthy);
		
	}
	
	public static void testLoadRaters()
	{
		String filename = "data/ratings_short.csv";
		String filename2 = "data/ratings.csv";
		ArrayList<Rater> raters = loadRaters(filename2);
		System.out.println("The number of raters are : " + raters.size());
		String raterId = "193";
		String movieID = "1798709";
		int raterCount = 0;
		Set<String> moviesRated = new HashSet<String>();
		
		Integer maxRating = -1;
		
		for(Rater r : raters)
		{
//			System.out.println("The rater id: " + r.getID() + 
//							   " has  " + r.numRatings() + " ratings ");
			if(r.getID().equals(raterId))
			{
				System.out.println("RaterId " + raterId + " has " + r.numRatings() + " ratings");
			}
			
			if(r.numRatings() > maxRating)
			{
				maxRating = r.numRatings();
			}
			
			if(r.hasRating(movieID))
			{
				raterCount++;
			}
			
			ArrayList<String> ratedItems = r.getItemsRated();
			for (String item : ratedItems)
			{
				if(!moviesRated.contains(item))
				{
					moviesRated.add(item);
				}
			}
		}
		
		for (Rater r : raters)
		{
			if (r.numRatings() == maxRating)
			{
				System.out.println("Rater id " + r.getID() + " has maximum number of ratings");
			}
		}
		System.out.println("Max ratings are " + maxRating);
		System.out.println("MovieID " + movieID + " has rating " + raterCount);
		System.out.println("Number of movies rated " + moviesRated.size());
		
	}
	
	public static void main(String[] args) {
		testLoadMovies();
		testLoadRaters();
	}
}
