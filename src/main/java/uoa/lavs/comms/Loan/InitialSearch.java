package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.loan.FindLoan;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.LoanSummary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class InitialSearch extends AbstractSearchable<LoanSummary> {
    // Log4J2
    private static final Logger logger = LogManager.getLogger(uoa.lavs.comms.Customer.InitialSearch.class);

    public InitialSearch() {
    }

    @Override
    public List<LoanSummary> findAll(Connection conn, String id) {
        HashSet<String> foundIDs = new HashSet<>();
        ArrayList<LoanSummary> summaries = new ArrayList<>();
        for(Loan loan : Cache.searchLoanCache(id)) {
            foundIDs.add(loan.getId());
            summaries.add(obfuscateLoan(loan));
            logger.info("Found in cache");
        }
        try{
            FindLoan loan = new FindLoan();
            loan.setId(id);
            for(LoanSummary summary : (List<LoanSummary>) processRequest(conn, loan, status -> executeCommon(conn, loan), status -> new ArrayList<>())) {
                if(!foundIDs.contains(summary.getLoanID())) {
                    summaries.add(summary);
                    foundIDs.add(summary.getLoanID());
                }
            }
        } catch (Exception e) {
            // do nothing
        }
        return summaries;
    }


    private List<LoanSummary> executeCommon(Connection conn, FindLoan loan) {
        List<LoanSummary> summaries = new ArrayList<>();
        int length = loan.getCountFromServer();
        for (int i = 1; i < length + 1; i++) {
            LoanSummary summary = new LoanSummary(
                    loan.getLoanIdFromServer(i), loan.getCustomerIdFromServer(), loan.getCustomerNameFromServer(), loan.getStatusFromServer(i), loan.getPrincipalFromServer(i)
            );
            summaries.add(summary);
        }
        return summaries;
    }

    LoanSummary obfuscateLoan(Loan loan) {
        return new LoanSummary(loan.getId(), loan.getCustomerId(), loan.getCustomerName(), loan.getStatus().toString(), loan.getPrincipal());
    }
}
