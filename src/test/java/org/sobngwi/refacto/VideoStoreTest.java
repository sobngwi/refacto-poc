package org.sobngwi.refacto;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class VideoStoreTest
{

	private Statement statement;
	private Movie newReleaseMovie1,newReleaseMovie2;
	private Movie newReleaseChildren;
	private Movie regularMovie1, regularMovie2, regularMovie3;
	private String report;
	private String customerName = "Alain Narcisse";

	@Before
	public void setUp() {
		statement = new Statement(customerName);
		newReleaseMovie1 = new NewReleaseMovie("new release 1");
		newReleaseMovie2 = new NewReleaseMovie ("new release 2");
		newReleaseChildren = new ChildrenMovie ("children 1");
		regularMovie1 = new RegularMovie("Regular 1");
		regularMovie2 = new RegularMovie("Regular 2");
		regularMovie3 = new RegularMovie("Regular 3");
	}

	@Test
	public void testSingleNewReleaseStatementTotals () {
		statement.addRental (new Rental (newReleaseMovie1, 3));

		generateReport(statement.generate());

		assertThat(9.0, equalTo(statement.getTotalAmount()));
		assertThat(2, equalTo(statement.getFrequentRenterPoints()));
	}

	@Test
	public void testDualNewReleaseStatementTotals () {
		statement.addRental (new Rental (newReleaseMovie1, 3));
		statement.addRental (new Rental (newReleaseMovie2, 3));

		generateReport(statement.generate());

		assertThat(1, equalTo(regularMovie3.determineFrequentRentalPoint(2)));
		assertThat(18., equalTo(statement.getTotalAmount()));
		assertThat(4, equalTo(statement.getFrequentRenterPoints()));
	}

	@Test
	public void testSingleChildrenStatementTotals () {
		statement.addRental (new Rental (newReleaseChildren, 4));

		generateReport(statement.generate());
		
		assertThat(1, equalTo(regularMovie3.determineFrequentRentalPoint(1)));
		assertThat(3., equalTo(statement.getTotalAmount()));
		assertThat(1, equalTo(statement.getFrequentRenterPoints()));
	}

	@Test
	public void testMultipleRegularStatementTotals () {
		statement.addRental (new Rental (regularMovie1, 1));
		statement.addRental (new Rental (regularMovie2, 2));
		statement.addRental (new Rental (regularMovie3, 3));

		generateReport(statement.generate());

		assertThat(7.5, equalTo(statement.getTotalAmount()));
		assertThat(3, equalTo(statement.getFrequentRenterPoints()));
	}

	@Test
	public void testMultipleRegularStatementFormat () {
		statement.addRental (new Rental (regularMovie1, 1));
		statement.addRental (new Rental (regularMovie2, 2));
		statement.addRental (new Rental (regularMovie3, 3));

		report = statement.generate();

		assertThat(report, allOf(
				containsString("7.5"),
				containsString("3.5"),
				containsString("2.0")));

		assertThat(2.0, equalTo(regularMovie1.determineAmount(2)));
		assertThat(2.0, equalTo(regularMovie2.determineAmount(2)));
		assertThat(3.5, equalTo(regularMovie3.determineAmount(3)));
		assertThat(1, equalTo(regularMovie3.determineFrequentRentalPoint(1)));
		assertThat(7.5, equalTo(statement.getTotalAmount()));
		assertThat(3, equalTo(statement.getFrequentRenterPoints()));
	}

	private void generateReport(String report) {
		assertThat(report, containsString(customerName));
	}

}