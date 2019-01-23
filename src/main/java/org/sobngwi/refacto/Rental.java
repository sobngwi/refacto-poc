package org.sobngwi.refacto;

public class Rental
{
	public Rental (Movie movie, int daysRented) {
		this.movie 		= movie;
		this.daysRented = daysRented;
	}

	private Movie movie;
	private int daysRented;

	public String getMovieTitle() {
	    return movie.getTitle();
	}

	double determineAmount() {
		return movie.determineAmount(daysRented);
	}

	int determineFrequentRentalPoint(){
		return movie.determineFrequentRentalPoint(daysRented);
	}

}