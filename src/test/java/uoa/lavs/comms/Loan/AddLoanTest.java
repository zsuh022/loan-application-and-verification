package uoa.lavs.comms.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.comms.AbstractLoanTest;
import uoa.lavs.mainframe.*;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Loan;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AddLoanTest extends AbstractLoanTest<Loan> {

    String id = null;

    UpdateStatus statusUpdate = new UpdateStatus();

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        id = addLoan.add(conn, loan);
        loan.setLoanId(id);
        statusUpdate.add(conn, LoanStatus.Active, id);
    }

    @Override
    protected void assertDetails(Loan expected, Loan actual) {
        if (!expected.equals(actual)) {
            fail();
        }
    }

    @Test
    protected void testLoanSuccess() {
        Loan newLoan = searchLoan.findById(conn, id);
        assertDetails(loan, newLoan);
    }
}
