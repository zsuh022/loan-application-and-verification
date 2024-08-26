package uoa.lavs.logging;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.MessageDescription;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.FindCustomer;
import uoa.lavs.mainframe.messages.customer.FindCustomerAdvanced;
import uoa.lavs.mainframe.messages.customer.UpdateCustomer;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static uoa.lavs.logging.LocalLogManager.*;

public class LocalLogManagerTests {

    @BeforeEach
    public void setup() {
        // delete lavs-data.db
        File db = new File("lavs-data.db");
        // clear log
        File file = new File("log.json");
        try {
            Files.deleteIfExists(file.toPath());
            Files.deleteIfExists(db.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalLogManager.reinitialise();
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
        properties.put("id", TEMPORARY_CUSTOMER_ID_PREFIX+"1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        properties.put("dob", formatter.format(dateOfBirth));
        properties.put("occupation", occupation);
        properties.put("citizenship", citizenship);
        properties.put("visa", visa);
        // write to log
        writeToLog(type, properties);
    }

    @AfterAll
    public static void tearDown() {
        // delete lavs-data.db
        File db = new File("lavs-data.db");
        // clear log
        File file = new File("log.json");
        try {
            Files.deleteIfExists(file.toPath());
            Files.deleteIfExists(db.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteLog() {
        // should write to log.json
        // read log.json
        JSONArray log;
        File file = new File("log.json");
        int logCount;
        try{
            log = new JSONArray(Files.readString(file.toPath()));
            logCount = log.length();
        } catch (Exception e) {
            // if file does not exist, create new log
            log = new JSONArray();
            logCount = 0;
            e.printStackTrace();
        }
        System.out.println(logCount);
        assert ((JSONObject) log.get(0)).getString("name").equals("John Doe");
    }

    @Test
    public void testFlushLog() {
        // flush log
        assert flushLog();
        assert !LocalLogManager.getSyncTimeProperty().getValue().equals("N/A");
    }

    @Test
    public void testLogTempCustomerIds() {
        // add a log entry
        int type = 1201;
        String customerId = TEMPORARY_CUSTOMER_ID_PREFIX+"1";
        String title = "Mr";
        String name = "Jerry";
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
        properties2.put("name", "Terry");
        // write to log
        writeToLog(type, properties);
        writeToLog(type, properties2);
        // should write to log.json
        // flush log
        assert flushLog();
        // search for Doe John in database
        FindCustomerAdvanced search = new FindCustomerAdvanced();
        search.setSearchName("Terry");
        search.send(Instance.getConnection());
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
        loanProperties.put("date", "01-01-2021");
        properties.put("date", formatter.format(dateOfBirth));
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

        assert flushLog();

    }

    @Test
    public void testMalformedLog(){
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
        properties.put("id", "543252625");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        properties.put("dob", formatter.format(dateOfBirth));
        properties.put("occupation", occupation);
        properties.put("citizenship", citizenship);
        properties.put("visa", visa);
        // write to log
        writeToLog(type, properties);
        // should write to log.json
        // flush log
        assert !flushLog();
    }

    @Test
    public void testCannotWrite(){
        // lock log.json
        try{
            RandomAccessFile logfile = new RandomAccessFile("log.json", "rw");
            FileChannel channel = logfile.getChannel();
            FileLock lock = channel.lock();
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
            //unlock log.json

            //read log.json
            JSONArray log = null;
            File file = new File("log.json");
            try{
                log = new JSONArray(Files.readString(file.toPath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert log.length() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNotEmpty(){
        LocalLogManager.reinitialise();
        assert !LocalLogManager.getEmptyProperty().getValue();
    }

    @Test
    public void createNewLoan(){
        int Loantype = 2201;
        String customerId = TEMPORARY_CUSTOMER_ID_PREFIX+"1";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate loanDate = LocalDate.of(2025, 1, 1);
        HashMap<String, String> loanProperties = new HashMap<>();
        loanProperties.put("compounding", "1");
        loanProperties.put("id", null);
        loanProperties.put("customerId", customerId);
        loanProperties.put("principal", "1000");
        loanProperties.put("rate.value", "0.1");
        loanProperties.put("rate.type", "1");
        loanProperties.put("date", loanDate.format(formatter));
        loanProperties.put("period", "1");
        loanProperties.put("term", "1");
        loanProperties.put("payment.amount", "100");
        loanProperties.put("payment.freq", "1");
        // write to log
        writeToLog(Loantype, loanProperties);
        assert flushLog();
    }
}
