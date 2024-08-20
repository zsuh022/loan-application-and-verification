package uoa.lavs.comms.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.comms.AbstractLoanTest;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Loan;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AddLoanTest extends AbstractLoanTest<Loan> {


    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        addLoan.add(conn, loan);
    }

    @Override
    protected void assertDetails(Loan expected, Loan actual) {
    }

    @Test
    protected void testLoanSuccess() {
        Loan newLoan = searchLoan.findById(conn, "1-01");
        assertDetails(loan, newLoan);
    }
}
