package uoa.lavs.models.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Frequency;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoanDetailsTests {

    private LoanDetails loanDetails;

    @BeforeEach
    void setUp() {
        loanDetails = new LoanDetails();
    }

    @Test
    void testGetFrequencyString_Weekly() {
        loanDetails.frequency = Frequency.Weekly;
        assertEquals("Weekly", loanDetails.getFrequencyString());
    }

    @Test
    void testGetFrequencyString_Fortnightly() {
        loanDetails.frequency = Frequency.Fortnightly;
        assertEquals("Fortnightly", loanDetails.getFrequencyString());
    }

    @Test
    void testGetFrequencyString_Monthly() {
        loanDetails.frequency = Frequency.Monthly;
        assertEquals("Monthly", loanDetails.getFrequencyString());
    }
}
