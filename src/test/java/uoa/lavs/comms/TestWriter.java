package uoa.lavs.comms;

import uoa.lavs.mainframe.Connection;

import java.io.IOException;
import java.util.Map;

public class TestWriter extends AbstractWriter<Object> {

    @Override
    protected Map<String, String> extractLogProperties(Object entity, String customerID) {
        return null;
    }

    @Override
    public String add(Connection conn, Object entity) {
        return super.add(conn, entity);
    }

    @Override
    protected String add(Connection conn, Object entity, String customerID) {
        return super.add(conn, entity, customerID);
    }
}
