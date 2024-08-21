package uoa.lavs.comms.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.comms.AbstractLoanTest;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.Customer.CustomerSummary;
import uoa.lavs.models.Loan.LoanSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InitialSearchTest extends AbstractLoanTest<LoanSummary> {

    private InitialSearch initialSearch;
    String id = null;

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        initialSearch = new InitialSearch();
        id = addLoan.add(conn, loan);
    }

    @Override
    protected void assertDetails(LoanSummary expected, LoanSummary actual) {

    }


    @Test
    protected void testFindAllSuccess() {
        List<LoanSummary> summaries = initialSearch.findAll(conn, customerId);
        assertEquals(1, summaries.size());
        for (LoanSummary e : summaries) {
            assertDetails(e, summaries.get(0));
        }
    }

    @Test
    protected void testFindAllFailure() {
        List<LoanSummary> summaries = initialSearch.findAll(mockConnection, id);
        assertTrue(summaries.isEmpty());
    }
}
