package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import uoa.lavs.comms.Loan.AddLoan;
import uoa.lavs.comms.Loan.SearchLoan;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.utility.LoanFactory;
import uoa.lavs.utility.LoanType;
import uoa.lavs.utility.PaymentFrequency;

import java.io.IOException;
import static uoa.lavs.logging.LocalLogManager.TEMPORARY_LOAN_ID_PREFIX;


public class AbstractLoanTest<R> extends AbstractCustomerTest<R> {

    protected final AddLoan addLoan = new AddLoan();
    protected final SearchLoan searchLoan = new SearchLoan();

    protected final Loan loan = new LoanFactory().getLoan(LoanType.Mortgage);

    protected String customerId = null;
    protected String customerId1 = null;

    @BeforeEach
    protected void setup() throws IOException {
        super.setup();

        customerId = addCustomer.add(conn, customer);
        customerId1 = addCustomer.add(conn, customer1);
        loan.setLoanId(TEMPORARY_LOAN_ID_PREFIX);
        loan.setCustomerID(customerId);
        loan.setCustomerName(customer.getName());
        loan.setPrincipal(10000.0);
        loan.setRateType(RateType.Fixed);
        loan.setRate(10.0);
        loan.setStartDate(java.time.LocalDate.of(2024, 2, 11));
        loan.setPeriod(5);
        loan.setCompoundingFrequency(Frequency.Yearly);
        loan.setPaymentFrequency(PaymentFrequency.Fortnightly);
        loan.setPaymentAmount(1000.0);
        loan.setStatus(LoanStatus.New);
        loan.setTerm(360);
    }

    @Override
    protected void assertDetails(R expected, R actual) {
    }
}
