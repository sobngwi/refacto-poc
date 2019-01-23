package org.sobngwi.refacto;

import java.util.ArrayList;
import java.util.List;

public class Statement
{

	private String customerName;
	private List<Rental> rentals = new ArrayList<>();
	private double totalAmount;
	private int frequentRenterPoints;
	private String statementText;

	public Statement(String customerName) {
		this.customerName = customerName;
	}
	
	public void addRental (Rental rental) {
		rentals.add(rental);
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public int getFrequentRenterPoints() {
		return frequentRenterPoints;
	}

	public String generate() {

		statementText = header();

		statementText += rentalLines();

		statementText += footer();

		return statementText;

	}

	private String header() {
		return String.format("Rental Record for %s\n", customerName);
	}

	private String rentalLines() {
		String rentalLines = "";
		for (Rental rental : rentals)
			rentalLines += rentalLine(rental);

		return rentalLines;
	}

	private String rentalLine(Rental rental) {
		String rentalLine ="" ;

		double rentalAmount = rental.determineAmount();
		frequentRenterPoints += rental.determineFrequentRentalPoint();
		rentalLine += formatRentalLine(rental, rentalAmount);
		totalAmount += rentalAmount;

		return rentalLine;
	}

	private String formatRentalLine(Rental rental, double rentalAmount) {
		return String.format("\t%s\t%s\n", rental.getMovieTitle(), rentalAmount);
	}

	private String footer() {
		return String.format("You owed %s\nYou earned %s " +
				"frequent renter points\n", totalAmount, frequentRenterPoints);
	}

}