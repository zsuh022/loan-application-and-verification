package uoa.lavs.comms.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractLoanTest;
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
}
