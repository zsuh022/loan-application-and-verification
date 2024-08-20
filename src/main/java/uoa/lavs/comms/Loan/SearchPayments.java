package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.loan.LoadLoanPayments;
import uoa.lavs.models.Loan.Payments;

import java.util.ArrayList;
import java.util.List;

public class SearchPayments extends AbstractSearchable<Payments> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchPayments.class);

    @Override
    public Payments findById(Connection conn, String customerId) {
        throw new UnsupportedOperationException("Findbyid is not supported");
    }

    @Override
    public List<Payments> findAll(Connection conn, String loanID) {
        LoadLoanPayments loan = new LoadLoanPayments();
        loan.setLoanId(loanID);
        loan.setNumber(1);

        return processRequest(conn, loan, status -> {
            List<Payments> list = new ArrayList<>();
            for (int i = 1; i < loan.getPageCountFromServer() + 1; i++) {
                Payments value = new Payments(
                        loan.getCustomerIdFromServer(), loan.getCustomerNameFromServer(), loan.getPaymentCountFromServer(),
                        loan.getPaymentInterestFromServer(i), loan.getPaymentPrincipalFromServer(i), loan.getPaymentRemainingFromServer(i),
                        loan.getPaymentNumberFromServer(i), loan.getPaymentDateFromServer(i)
                );
                list.add(value);
            }
            logger.info("Loan Payments for customer ID {}, successfully loaded", loan.getCustomerIdFromServer());
            return list;
        }, status -> {
            return new ArrayList<>();
        });
    }
}
