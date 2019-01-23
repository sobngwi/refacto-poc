package org.sobngwi.refacto;

public class ChildrenMovie extends Movie {
    public ChildrenMovie(String title) {
        super(title);
    }

    @Override
    double determineAmount(int daysRented) {
          return      (daysRented > 3) ?
                    1.5 + (daysRented - 3) * 1.5 : 1.5;
    }

    @Override
    int determineFrequentRentalPoint(int daysRented) {

        return 1;

    }
}
