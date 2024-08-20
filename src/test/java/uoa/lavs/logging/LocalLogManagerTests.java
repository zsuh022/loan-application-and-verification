package uoa.lavs.logging;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.FindCustomerAdvanced;
import uoa.lavs.mainframe.simulator.NitriteConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static uoa.lavs.logging.LocalLogManager.*;

public class LocalLogManagerTests {

    @BeforeEach
    public void setup() {
        LocalLogManager.clearLog();
        File file = new File("log.json");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testWriteLog() throws IOException {
        int type = 1201;
        String title = "Mr";
        String name = "John Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String occupation = "Software Engineer";
        String citizenship = "New Zealand";
        String visa = "Work Visa";
        // create hashmap for a new customer entry
        HashMap<String, String> properties = new HashMap<>();
        properties.put("title", title);
        properties.put("name", name);
        properties.put("id", null);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        properties.put("dob", formatter.format(dateOfBirth));
        properties.put("occupation", occupation);
        properties.put("citizenship", citizenship);
        properties.put("visa", visa);
        // write to log
        writeToLog(type, properties);
        // should write to log.json
        // read log.json

        JSONArray log;
        File file = new File("log.json");
        int logCount;
            log = new JSONArray(Files.readString(file.toPath()));
            logCount = log.length();
        assert ((JSONObject) log.get(0)).getString("name").equals(name);
    }

    @Test
    public void testFlushLog() {
        // add a log entry
        int type = 1201;
        String title = "Mr";
        String name = "John Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String occupation = "Software Engineer";
        String citizenship = "New Zealand";
        String visa = "Work Visa";
        // create hashmap for a new customer entry
        HashMap<String, String> properties = new HashMap<>();
        properties.put("title", title);
        properties.put("name", name);
        properties.put("id", null);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        properties.put("dob", formatter.format(dateOfBirth));
        properties.put("occupation", occupation);
        properties.put("citizenship", citizenship);
        properties.put("visa", visa);
        // write to log
        writeToLog(type, properties);
        // should write to log.json
        // flush log
        assert flushLog(new NitriteConnection());
    }

    @Test
    public void testLogTempCustomerIds() {

        Connection connection = new NitriteConnection();

        // add a log entr
        int type = 1201;
        String customerId = TEMPORARY_CUSTOMER_ID_PREFIX+"1";
        String title = "Mr";
        String name = "John Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String occupation = "Software Engineer";
        String citizenship = "New Zealand";
        String visa = "Work Visa";
        // create hashmap for a new customer entry
        HashMap<String, String> properties = new HashMap<>();
        properties.put("id", customerId);
        properties.put("title", title);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        properties.put("dob", formatter.format(dateOfBirth));
        properties.put("occupation", occupation);
        properties.put("citizenship", citizenship);
        properties.put("visa", visa);

        HashMap<String, String> properties2 = new HashMap<>(properties);
        properties.put("name", name);
        properties2.put("name", "Doe John");
        // write to log
        writeToLog(type, properties);
        writeToLog(type, properties2);
        // should write to log.json
        // flush log
        assert flushLog(connection);
        // search for Doe John in database
        FindCustomerAdvanced search = new FindCustomerAdvanced();
        search.setSearchName("Doe John");
        search.send(connection);
        assert search.getCustomerCountFromServer() == 1;
    }

    @Test
    public void testLogTempIds() {
        // add a log entry
        int type = 1201;
        String customerId = TEMPORARY_CUSTOMER_ID_PREFIX+"1";
        String title = "Mr";
        String name = "John Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String occupation = "Software Engineer";
        String citizenship = "New Zealand";
        String visa = "Work Visa";
        // create hashmap for a new customer entry
        HashMap<String, String> properties = new HashMap<>();
        properties.put("id", customerId);
        properties.put("title", title);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        properties.put("dob", formatter.format(dateOfBirth));
        properties.put("occupation", occupation);
        properties.put("citizenship", citizenship);
        properties.put("visa", visa);
        properties.put("name", name);

        int Loantype = 2201;
        HashMap<String, String> loanProperties = new HashMap<>();
        loanProperties.put("compounding", "1");
        loanProperties.put("id", TEMPORARY_LOAN_ID_PREFIX+"1");
        loanProperties.put("customerId", customerId);
        loanProperties.put("principal", "1000");
        loanProperties.put("rate.value", "0.1");
        loanProperties.put("rate.type", "1");
        loanProperties.put("date", formatter.format(dateOfBirth));
        loanProperties.put("period", "1");
        loanProperties.put("term", "1");
        loanProperties.put("payment.amount", "100");
        loanProperties.put("payment.freq", "1");
        HashMap<String, String> loanProperties2 = new HashMap<>(loanProperties);
        loanProperties2.put("principal", "500");
        // write to log
        writeToLog(type, properties);
        writeToLog(Loantype, loanProperties);
        writeToLog(Loantype, loanProperties2);

        assert flushLog(new NitriteConnection());
    }

    @Test
    public void testFailedLoan() {
        // add a log entry
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.of(1990, 1, 1);
        int Loantype = 2201;
        HashMap<String, String> loanProperties = new HashMap<>();
        loanProperties.put("compounding", "1");
        loanProperties.put("id", TEMPORARY_LOAN_ID_PREFIX+"1");
        loanProperties.put("principal", "1000");
        loanProperties.put("rate.value", "0.1");
        loanProperties.put("rate.type", "1");
        loanProperties.put("date", formatter.format(date));
        loanProperties.put("period", "1");
        loanProperties.put("term", "1");
        loanProperties.put("payment.amount", "100");
        loanProperties.put("payment.freq", "1");
        // write to log
        writeToLog(Loantype, loanProperties);
        // fails because no customer
        assert !flushLog(new NitriteConnection());
    }

    // write log entry, delete log file, flush log, check if log file is created
    @Test
    public void testFlushLogWithDeletedFile() throws IOException {
        int type = 1201;
        String title = "Mr";
        String name = "John Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String occupation = "Software Engineer";
        String citizenship = "New Zealand";
        String visa = "Work Visa";
        // create hashmap for a new customer entry
        HashMap<String, String> properties = new HashMap<>();
        properties.put("title", title);
        properties.put("name", name);
        properties.put("id", null);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        properties.put("dob", formatter.format(dateOfBirth));
        properties.put("occupation", occupation);
        properties.put("citizenship", citizenship);
        properties.put("visa", visa);
        // write to log
        writeToLog(type, properties);
        // should write to log.json
        // delete log.json
        File file = new File("log.json");
        if (file.exists()) {
            file.delete();
        }
        // flush log
        assert flushLog(new NitriteConnection());
        // check if log.json is created
        assert file.exists();
    }
}
