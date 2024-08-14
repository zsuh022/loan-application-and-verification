package uoa.lavs.logging;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashMap;

import static uoa.lavs.logging.LocalLogManager.writeToLog;

public class LocalLogManagerTests {
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
        writeToLog(type, properties);
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
        assert ((JSONObject) log.get(0)).getString("name").equals(name);
        assert ((JSONObject) log.get(1)).getString("name").equals(name);
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
        boolean successful = LocalLogManager.flushLog();
        assert successful;

    }
}
