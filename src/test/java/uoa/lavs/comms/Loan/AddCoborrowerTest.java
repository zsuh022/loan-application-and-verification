package uoa.lavs.comms.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import uoa.lavs.comms.AbstractLoanTest;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Customer.CustomerAddress;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class AddCoborrowerTest extends AbstractLoanTest<Coborrower> {

    AddCoborrower addCoborrower = new AddCoborrower();
    SearchCoborrower searchCoborrower = new SearchCoborrower();
    Coborrower coborrower = new Coborrower();
    String loanId = null;

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        loanId = addLoan.add(conn, loan);

        coborrower.setId(customerId1);
        coborrower.setName(customer1.getName());
        coborrower.setNumber(null);

        loan.addCoborrower(coborrower);

    }

    @Override
    protected void assertDetails(Coborrower expected, Coborrower actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    protected void testCoborrowerSuccess() {
        addCoborrower.add(conn, coborrower, loanId);
        List<Coborrower> coborrowersFromDb = searchCoborrower.findAll(conn, loanId);

        List<Coborrower> expectedCoborrowers = loan.getCoborrowerList();

        for (Coborrower expectedCoborrower : expectedCoborrowers) {
            boolean matchFound = false;

            for (Coborrower dbCoborrower : coborrowersFromDb) {
                if (Objects.equals(expectedCoborrower.getId(), dbCoborrower.getId())) {
                    assertDetails(expectedCoborrower, dbCoborrower);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Expected coborrower not found: " + expectedCoborrower.getName());
            }
        }

        for (Coborrower dbCoborrower : coborrowersFromDb) {
            boolean matchFound = false;

            for (Coborrower expectedCoborrower : expectedCoborrowers) {
                if (dbCoborrower.getId().equals(expectedCoborrower.getId())) {
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Unexpected coborrower found: " + dbCoborrower.getName());
            }
        }
    }

    @Test
    void testAddCoborrowerFailureAll() {


        addCoborrower.add(mockConnection, coborrower, loanId);

        List<Coborrower> list = searchCoborrower.findAll(mockConnection, customerId);
        assertEquals(0, list.size());
    }

    @Test
    protected void testUnsupportedMethod() {
        Executable executable = () -> {
            Coborrower result = searchCoborrower.findById(conn, "1");
        };

        assertThrows(UnsupportedOperationException.class, executable, "findAll should throw UnsupportedOperationException");
    }
}
