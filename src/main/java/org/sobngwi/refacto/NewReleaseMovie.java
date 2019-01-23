package org.sobngwi.refacto;

public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    double determineAmount(int daysRented) {
                return  daysRented * 3;
    }

    @Override
    int determineFrequentRentalPoint(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
