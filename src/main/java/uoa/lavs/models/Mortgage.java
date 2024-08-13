package uoa.lavs.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Mortgage extends Loan {

    @Override
    public ArrayList<String> getRepaymentSchedule() {
        return null;
    }

    @Override
    public void writeLoan(HashMap<String, String> map) {
        // TODO:
    }
}
