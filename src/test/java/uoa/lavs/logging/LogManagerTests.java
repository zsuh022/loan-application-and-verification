package uoa.lavs.logging;

import org.junit.Test;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.messages.customer.LoadCustomer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static uoa.lavs.logging.LogManager.flushLog;
import static uoa.lavs.logging.LogManager.writeToLog;
import static uoa.lavs.mainframe.messages.All.getMessageDescription;

public class LogManagerTests {
    @Test
    public void testLogManager() {
        int type = 1201;
        String customerId = "1";
        String title = "Mr";
        String name = "John Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String occupation = "Software Engineer";
        String citizenship = "New Zealand";
        String visa = "Work Visa";
        // create hashmap for a new customer entry
        HashMap<String, String> properties = new HashMap<>();
        properties.put("customerId", customerId);
        properties.put("title", title);
        properties.put("name", name);
        properties.put("dateOfBirth", dateOfBirth.toString());
        properties.put("occupation", occupation);
        properties.put("citizenship", citizenship);
        properties.put("visa", visa);
        // write to log
        writeToLog(type, properties);
        // should write to log.json
    }

    @Test
    public void testFlushLog() {
        // add a log entry
        int type = 1201;
        String customerId = "1";
        String title = "Mr";
        String name = "John Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String occupation = "Software Engineer";
        String citizenship = "New Zealand";
        String visa = "Work Visa";
        // create hashmap for a new customer entry
        HashMap<String, String> properties = new HashMap<>();
        properties.put("customerId", customerId);
        properties.put("title", title);
        properties.put("name", name);
        properties.put("dateOfBirth", dateOfBirth.toString());
        properties.put("occupation", occupation);
        properties.put("citizenship", citizenship);
        properties.put("visa", visa);
        // write to log
        writeToLog(type, properties);
        // should write to log.json
        // flush log
        ArrayList<Response> reponses = LogManager.flushLog();
        // should send log entry to mainframe
        //print all names
        for (Response response : reponses) {
            String responseId = response.getValue(LoadCustomer.Fields.CUSTOMER_ID);
            System.out.println(responseId);
            String responseName = response.getValue(LoadCustomer.Fields.NAME);
            System.out.println(responseName);
        }
    }
}
