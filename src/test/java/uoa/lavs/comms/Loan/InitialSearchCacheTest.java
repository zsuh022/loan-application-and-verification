package uoa.lavs.comms.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.comms.AbstractLoanTest;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.loan.FindLoan;
import uoa.lavs.models.Customer.CustomerSummary;
import uoa.lavs.models.Loan.LoanSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InitialSearchCacheTest extends AbstractLoanTest<LoanSummary> {

    private InitialSearch initialSearch;
    String id = null;

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        initialSearch = new InitialSearch();
        id = addLoan.add(conn, loan);
        loan.setLoanId(id);
        Cache.cacheLoan(loan);
    }

    @Override
    protected void assertDetails(LoanSummary expected, LoanSummary actual) {

    }


    @Test
    protected void testFindAllSuccess() {
        LoanSummary summary = initialSearch.obfuscateLoan(loan);
        LoanSummary actualSummary = initialSearch.findAll(conn, id).get(0);

        assertEquals(summary, actualSummary);

    }

    @Test
    protected void testFindAllFailure() {
        List<LoanSummary> summaries = initialSearch.findAll(mockConnection, String.valueOf(1));
        assertTrue(summaries.isEmpty());
    }

    @Test
    public void testFindAllWithException() {
        Connection mockConnection = mock(Connection.class);

        InitialSearch initialSearch = new InitialSearch();

        doThrow(new RuntimeException("Forced exception for testing")).when(mockConnection).send(Mockito.any());

        List<LoanSummary> summaries = initialSearch.findAll(mockConnection, "1");

        assertTrue(summaries.isEmpty());
    }





}
