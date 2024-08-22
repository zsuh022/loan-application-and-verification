package uoa.lavs.comms.Loan;

import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.*;
import uoa.lavs.mainframe.messages.loan.FindLoan;
import uoa.lavs.models.Customer.CustomerSummary;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.LoanSummary;

import java.util.ArrayList;
import java.util.List;

public class InitialSearch extends AbstractSearchable<LoanSummary> {

    public InitialSearch() {
    }

    @Override
    public List<LoanSummary> findAll(Connection conn, String id) {

        ArrayList<LoanSummary> summaries = new ArrayList<>();
        for(Loan loan : Cache.searchLoanCache(id)) {
            summaries.add(obfuscateLoan(loan));
        }
        try{
            FindLoan loan = new FindLoan();
            loan.setId(id);
            summaries.addAll(processRequest(conn, loan, status -> executeCommon(conn, loan), status -> new ArrayList<>()));
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

    private LoanSummary obfuscateLoan(Loan loan) {
        return new LoanSummary(loan.getId(), loan.getCustomerId(), loan.getCustomerName(), loan.getStatus().toString(), loan.getPrincipal());
    }
}
