package uoa.lavs.utility;

import org.junit.jupiter.api.Test;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.Mortgage;

import static org.junit.jupiter.api.Assertions.*;

class LoanFactoryTests {

    private final LoanFactory loanFactory = new LoanFactory();

    @Test
    void testGetLoan_Mortgage() {
        Loan loan = loanFactory.getLoan(LoanType.Mortgage);
        assertNotNull(loan);
        assertInstanceOf(Mortgage.class, loan);
    }
}
