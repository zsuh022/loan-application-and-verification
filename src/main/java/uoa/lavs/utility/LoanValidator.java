package uoa.lavs.utility;

import java.util.Map;

public class LoanValidator {

    public boolean validateLoan(Map<String, String> loanValuesMap) {

        if (loanValuesMap.get("customerId") == null || loanValuesMap.get("customerId").isEmpty()) {
            return false;
        }

        // check customer id valid

        if (loanValuesMap.get("principal") == null || loanValuesMap.get("principal").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("rate") == null || loanValuesMap.get("rate").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("isFloating") == null || loanValuesMap.get("isFloating").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("isFixed") == null || loanValuesMap.get("isFixed").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("startDate") == null || loanValuesMap.get("startDate").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("period") == null || loanValuesMap.get("period").isEmpty()) {
            return false;
        }

        int compoundingCount = 0;
        if (loanValuesMap.get("compoundingWeekly").equals("true")) {
            compoundingCount++;
        }
        if (loanValuesMap.get("compoundingMonthly").equals("true")) {
            compoundingCount++;
        }
        if (loanValuesMap.get("compoundingAnnually").equals("true")) {
            compoundingCount++;
        }

        if (compoundingCount != 1) {
            return false;
        }

        int frequencyCount = 0;
        if (loanValuesMap.get("frequencyWeekly").equals("true")) {
            frequencyCount++;
        }
        if (loanValuesMap.get("frequencyFortnightly").equals("true")) {
            frequencyCount++;
        }
        if (loanValuesMap.get("frequencyMonthly").equals("true")) {
            frequencyCount++;
        }

        if (frequencyCount != 1) {
            return false;
        }

        if (loanValuesMap.get("amount") == null || loanValuesMap.get("amount").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("isInterestOnly") == null || loanValuesMap.get("isInterestOnly").isEmpty()) {
            return false;
        }

        // check coborrower id valid

        return true;
    }
}
