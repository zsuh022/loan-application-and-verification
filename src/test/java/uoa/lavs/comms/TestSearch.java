package uoa.lavs.comms;

import uoa.lavs.mainframe.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestSearch extends AbstractSearchable<Object> {

    @Override
    public Object findById(Connection conn, String customerId) {
        return super.findById(conn, customerId);
    }

    @Override
    public Object findById(Connection conn, String customerId, int index, int number) {
        return super.findById(conn, customerId, index, number);
    }

    @Override
    public List<Object> findAll(Connection conn, String customerId) {
        return new ArrayList<>();
    }


}
