package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import uoa.lavs.comms.Loan.AddLoan;
import uoa.lavs.comms.Loan.SearchLoan;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.Mortgage;
import uoa.lavs.utility.PaymentFrequency;

import java.io.IOException;


public class AbstractLoanTest<R> extends AbstractCustomerTest<R> {

    protected final AddLoan addLoan = new AddLoan();
    protected final SearchLoan searchLoan = new SearchLoan();

    protected final Loan loan = new Mortgage();

    @BeforeEach
    protected void setup() throws IOException {
        super.setup();

        loan.setLoanId("TEMP_LOAN_");
        loan.setCustomerID("1");
        loan.setCustomerName(customer.getName());
        loan.setPrincipal(10000.0);
        loan.setRateType(RateType.Fixed);
        loan.setRate(10.0);
        loan.setStartDate(java.time.LocalDate.of(2024, 2, 11));
        loan.setPeriod(5);
        loan.setCompoundingFrequency(Frequency.Yearly);
        loan.setPaymentFrequency(PaymentFrequency.Fortnightly);
        loan.setPaymentAmount(0.0);
        loan.setStatus(LoanStatus.Active);
        loan.setTerm(360);
    }

    @Override
    protected void assertDetails(R expected, R actual) {
    }
}
