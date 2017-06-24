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
}
