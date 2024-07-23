package uoa.lavs.mainframe.simulator;

import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Request;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.messages.All;
import uoa.lavs.mainframe.messages.customer.FindCustomer;

import static org.junit.jupiter.api.Assertions.*;

class DataParserTests {
    @Test
    public void convertResponseFromData() {
        // Arrange
        String data = "code=\"1001\",error=\"0\",msg=,in-id=\"123456-789\",out-count=\"1\",out-name=\"2\",out-dob=\"3\",out-id=\"4\"";

        // Act
        Response response = DataParser.convertResponseFromData(data, 123L);

        // Assert
        assertAll(
                () -> assertEquals(123L, response.getStatus().getTransactionId()),
                () -> assertEquals(0, response.getStatus().getErrorCode()),
                () -> assertNull(response.getStatus().getErrorMessage()),
                () -> assertEquals("1", response.getValue("count")),
                () -> assertEquals("2", response.getValue("name")),
                () -> assertEquals("3", response.getValue("dob")),
                () -> assertEquals("4", response.getValue("id"))
        );
    }

    @Test
    public void convertResponseHandlesNull() {
        // Arrange
        String data = "code=\"1001\",error=\"0\",msg=,in-id=\"123456-789\",out-count=,out-name=,out-dob=,out-id=";

        // Act
        Response response = DataParser.convertResponseFromData(data, 123L);

        // Assert
        assertAll(
                () -> assertEquals(123L, response.getStatus().getTransactionId()),
                () -> assertEquals(0, response.getStatus().getErrorCode()),
                () -> assertNull(response.getStatus().getErrorMessage()),
                () -> assertNull(response.getValue("count")),
                () -> assertNull(response.getValue("name")),
                () -> assertNull(response.getValue("dob")),
                () -> assertNull(response.getValue("id"))
        );
    }

    @Test
    public void convertResponseHandlesEncodedData() {
        // Arrange
        String data = "code=\"1001\",error=\"0\",msg=,in-id=\"123456-789\",out-count=\"\\\\\",out-name=\"\\\"\",out-dob=\"\\n\",out-id=\"\\r\"";

        // Act
        Response response = DataParser.convertResponseFromData(data, 123L);

        // Assert
        assertAll(
                () -> assertEquals(123L, response.getStatus().getTransactionId()),
                () -> assertEquals(0, response.getStatus().getErrorCode()),
                () -> assertNull(response.getStatus().getErrorMessage()),
                () -> assertEquals("\\", response.getValue("count")),
                () -> assertEquals("\"", response.getValue("name")),
                () -> assertEquals("\n", response.getValue("dob")),
                () -> assertEquals("\r", response.getValue("id"))
        );
    }

    @Test
    public void convertToDataGeneratesValidString() {
        // Arrange
        Request request = new Request(All.FindCustomer);
        request.setValue("id", "123456-789");

        // Act
        String actual = DataParser.convertToData(request);

        // Assert
        String expected = "code=\"1001\",error=\"0\",msg=,in-id=\"123456-789\",out-count=\"\",out-name=\"\",out-dob=\"\",out-id=\"\"";
        assertEquals(expected, actual);
    }

    @Test
    public void convertToDataHandlesNull() {
        // Arrange
        Request request = new Request(All.FindCustomer);

        // Act
        String actual = DataParser.convertToData(request);

        // Assert
        String expected = "code=\"1001\",error=\"0\",msg=,in-id=,out-count=\"\",out-name=\"\",out-dob=\"\",out-id=\"\"";
        assertEquals(expected, actual);
    }

    @Test
    public void convertToDataEncodesSpecialCharacters() {
        // Arrange
        Request request = new Request(All.FindCustomer);
        request.setValue("id", "quote=\",slash=\\,newline=\r\n");

        // Act
        String actual = DataParser.convertToData(request);

        // Assert
        String expected = "code=\"1001\",error=\"0\",msg=,in-id=\"quote=\\\",slash=\\\\,newline=\\r\\n\",out-count=\"\",out-name=\"\",out-dob=\"\",out-id=\"\"";
        assertEquals(expected, actual);
    }

}