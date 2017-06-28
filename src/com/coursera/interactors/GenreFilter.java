package com.coursera.interactors;

import com.coursera.models.MovieDatabase;

public class GenreFilter implements Filter {
	private String myGenre;
	
	public GenreFilter(String genre)
	{
		this.myGenre = genre;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).contains(myGenre);
	}

}
