package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.loan.LoadLoanCoborrowers;
import uoa.lavs.mainframe.messages.loan.LoadLoanSummary;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.LoanDetails;

import java.util.ArrayList;
import java.util.List;

public class SearchCoborrower extends AbstractSearchable<Coborrower> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchCoborrower.class);

    @Override
    public Coborrower findById(Connection conn, String loanID) {
        throw new UnsupportedOperationException("findAll is not supported for searching Coborrowers.");
    }

    @Override
    public List<Coborrower> findAll(Connection conn, String loanID) {
        LoadLoanCoborrowers load = new LoadLoanCoborrowers();
        load.setLoanId(loanID);
        load.setNumber(1);

        return processRequest(conn, load, status -> {
            List<Coborrower> list = new ArrayList<>();
            for (int i = 1; i < load.getCountFromServer() + 1; i++) {
                Coborrower newCob = new Coborrower();
                newCob.setId(load.getCoborrowerIdFromServer(i));
                newCob.setName(load.getCoborrowerNameFromServer(i));
                newCob.setNumber(i);
                list.add(newCob);
            }
            logger.info("Coborrowers retrieved for Loan ID {}, successfully loaded", loanID);
            return list;
        }, status -> {
            return new ArrayList<>();
        });
    }
}
