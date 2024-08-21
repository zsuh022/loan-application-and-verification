package uoa.lavs.comms.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import uoa.lavs.comms.AbstractLoanTest;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Payments;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoanPaymentSearch extends AbstractLoanTest<Payments> {

    String loanId = null;
    SearchPayments sum = new SearchPayments();

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        addCustomer.add(conn, customer);
        String id = addCustomer.add(conn, customer1);
        loanId = addLoan.add(conn, loan);
    }

    @Override
    protected void assertDetails(Payments expected, Payments actual) {
    }

    @Test
    protected void testPaymentSuccess() {
        List<Payments> list = sum.findAll(conn, loanId);

        for (Payments e : list) {
            assertEquals("John Doe", e.getCustomerName());
        }

    }

    @Test
    protected void testPaymentFail() {
        List<Payments> list = sum.findAll(mockConnection, loanId);

        assertEquals(0, list.size());

    }

    @Test
    protected void testUnsupportedMethod() {
        Executable executable = () -> {
            Payments result = sum.findById(conn, "1");
        };

        assertThrows(UnsupportedOperationException.class, executable, "findAll should throw UnsupportedOperationException");
    }


}
