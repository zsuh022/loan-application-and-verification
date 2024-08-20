package uoa.lavs.comms.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import uoa.lavs.comms.AbstractLoanTest;
import uoa.lavs.mainframe.*;
import uoa.lavs.models.Customer.CustomerPhone;
import uoa.lavs.models.Loan.LoanDetails;
import uoa.lavs.utility.PaymentFrequency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoanDetailsTest extends AbstractLoanTest<LoanDetails> {

    String loanId = null;
    SearchLoanSummary sum = new SearchLoanSummary();

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        addCustomer.add(conn, customer);
        String id = addCustomer.add(conn, customer1);
        loanId = addLoan.add(conn, loan);
    }

    @Override
    protected void assertDetails(LoanDetails expected, LoanDetails actual) {
    }

    @Test
    protected void testDetailsSuccess() {
        LoanDetails detail = sum.findById(conn, loanId);
    }

    @Test
    protected void testDetailsFailure() {
        Status errorStatus = new Status(404, "Some problem", 123456);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        LoanDetails detail = sum.findById(mockConnection, loanId);
        assertNull(detail.getCustomerID());
    }

    @Test
    protected void testFindAll() {
        Executable executable = () -> {
            List<LoanDetails> result = sum.findAll(conn, customerId);
        };
        assertThrows(UnsupportedOperationException.class, executable, "findAll should throw UnsupportedOperationException");
    }
}
