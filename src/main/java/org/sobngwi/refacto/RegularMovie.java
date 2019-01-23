package org.sobngwi.refacto;

public class RegularMovie extends Movie {
    public RegularMovie(String title) {
        super(title);
    }

    @Override
    double determineAmount(int daysRented) {

           return (daysRented > 2) ? 2 + (daysRented - 2) * 1.5 : 2;
    }

    @Override
    int determineFrequentRentalPoint(int daysRented) {

        return  1;
    }
}
